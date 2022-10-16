import java.text.DecimalFormat;

public class GaussMethods {
    private double[][] matrizLetters;
    private double[] matrizNumbers;
    private double error;


    public static double[] gaussJacobi(double[][] matriz, double[] matrizNumbers, double E) throws GaussException {
        double actualError;
        double[] matrizPreviousResult = new double[matriz[0].length];
        double[] matrizResult;
        int numberInteractions = 0;


        do {
            matrizResult = new double[matriz[0].length];
            for (int i = 0; i < matrizResult.length; i++) {
                matrizResult[i] = matrizNumbers[i];
                for (int j = 0; j < matriz.length; j++) {
                    if (j != i) {
                        matrizResult[i] -= matriz[i][j] * matrizPreviousResult[j];
                    }
                }
                matrizResult[i] /= matriz[i][i];
            }
            double maxValueActualMBefore = 0;
            for (int i = 0; i < matrizResult.length; i++) {
                double valueAMB = Math.abs(matrizResult[i] - matrizPreviousResult[i]);

                matrizPreviousResult[i] = matrizResult[i];

                if (valueAMB > maxValueActualMBefore) maxValueActualMBefore = valueAMB;

            }
            actualError = maxValueActualMBefore;
            numberInteractions += 1;
            printResult(matrizResult, numberInteractions, actualError);
            if (numberInteractions == 100)
                throw new GaussException("Número de interações máximo atingido");
        } while (actualError > E);

        return matrizResult;
    }

    public static double[] gaussSeidel(double[][] matriz, double[] matrizNumbers, double E) throws GaussException {
        double actualError;
        double[] matrizPreviousResult = new double[matriz[0].length];
        double[] matrizResult = new double[matriz[0].length];
        int numberInteractions = 0;


        do {
            for (int i = 0; i < matrizResult.length; i++) {
                matrizResult[i] = matrizNumbers[i];
                for (int j = 0; j < matriz.length; j++) {
                    if (j != i) {
                        matrizResult[i] -= matriz[i][j] * matrizResult[j];
                    }
                }
                matrizResult[i] /= matriz[i][i];
            }
            double maxValueActualMBefore = 0;
            for (int i = 0; i < matrizResult.length; i++) {
                double valueAMB = Math.abs(matrizResult[i] - matrizPreviousResult[i]);
                matrizPreviousResult[i] = matrizResult[i];

                if (valueAMB > maxValueActualMBefore) maxValueActualMBefore = valueAMB;

            }
            actualError = maxValueActualMBefore;
            numberInteractions += 1;
            printResult(matrizResult, numberInteractions, actualError);
            if (numberInteractions == 100)
                throw new GaussException("Número de interações máximo atingido");
        } while (actualError > E);

        return matrizResult;
    }

    public static void printResult(double[] matriz, int interactions, double actualError) {
        StringBuilder sbr = new StringBuilder();
        DecimalFormat df = new DecimalFormat("0.00000");


        sbr.append("Interaction: ").append(interactions).append(" |");
        for (int i = 0; i < matriz.length; i++) {
            sbr.append(" x").append(i + 1).append(" = ").append(df.format(matriz[i])).append(" |");
        }
        sbr.append(" erro atual: ").append(df.format(actualError));
        System.out.println(sbr);
    }

    public double[][] getMatrizLetters() {
        return matrizLetters;
    }

    public void setMatrizLetters(double[][] matrizLetters) {
        this.matrizLetters = matrizLetters;
    }

    public double[] getMatrizNumbers() {
        return matrizNumbers;
    }

    public void setMatrizNumbers(double[] matrizNumbers) {
        this.matrizNumbers = matrizNumbers;
    }

    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = error;
    }
}
