import java.text.DecimalFormat;

public class Methods {
    private double[][] matrizLetters;
    private double[] matrizNumbers;
    private double error;


    public static double[] gauss(double[][] matrizLetters, double[] matrizNumbers) {
        double nuloNum = 1e-5;

        for (int p = 0; p < matrizNumbers.length; p++) {

            int max = p;
            for (int i = p + 1; i < matrizNumbers.length; i++) {
                if (Math.abs(matrizLetters[i][p]) > Math.abs(matrizLetters[max][p])) {
                    max = i;
                }
            }
            double[] temp = matrizLetters[p];
            matrizLetters[p] = matrizLetters[max];
            matrizLetters[max] = temp;
            double t = matrizNumbers[p];
            matrizNumbers[p] = matrizNumbers[max];
            matrizNumbers[max] = t;

            if (Math.abs(matrizLetters[p][p]) <= nuloNum) {
                throw new ArithmeticException("Determinante é 0");
            }

            for (int i = p + 1; i < matrizNumbers.length; i++) {
                double alpha = matrizLetters[i][p] / matrizLetters[p][p];
                matrizNumbers[i] -= alpha * matrizNumbers[p];
                for (int j = p; j < matrizNumbers.length; j++) {
                    matrizLetters[i][j] -= alpha * matrizLetters[p][j];
                }
            }
        }

        double[] x = new double[matrizNumbers.length];
        for (int i = matrizNumbers.length - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < matrizNumbers.length; j++) {
                sum += matrizLetters[i][j] * x[j];
            }
            x[i] = (matrizNumbers[i] - sum) / matrizLetters[i][i];
        }
        printResultSE(x, 1);
        return x;
    }

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

    public static double[] interpolarizacaoPolinomial(double[] xNumbers, double[] yNumbers) {
        int n = yNumbers.length;
        double[] equationResult = new double[n];
        double[][] matrizLetter = new double[n][n];


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double xValue = xNumbers[i];
                matrizLetter[i][j] = Math.pow(xValue, j);
            }
        }

        equationResult = gauss(matrizLetter, yNumbers);

        return equationResult;
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

    public static void printResultSE(double[] matriz, int interactions) {
        StringBuilder sbr = new StringBuilder();
        DecimalFormat df = new DecimalFormat("0.00000");


        sbr.append("Interaction: ").append(interactions).append(" |");
        for (int i = 0; i < matriz.length; i++) {
            sbr.append(" x").append(i + 1).append(" = ").append(df.format(matriz[i])).append(" |");
        }

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
