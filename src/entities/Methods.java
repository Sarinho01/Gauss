package entities;

import exceptions.GaussException;

import java.text.DecimalFormat;

public class Methods {
    private double[][] matrizLetters;
    private double[] matrizNumbers;
    private double[] interpolation;
    private double error;

    public static double[] gauss(double[][] matrizLetters, double[] matrizNumbers) {
        double[] result = new double[matrizNumbers.length];

        for (int i = 1; i < matrizLetters.length; i++) {
            double multNumber;
            int actualLine = 0;
            for (int j = 0; j < i; j++) {
                multNumber = matrizLetters[i][j]/matrizLetters[actualLine][j];
                for (int k = j; k < matrizLetters.length; k++) {
                    matrizLetters[i][k] = matrizLetters[actualLine][k]*multNumber - matrizLetters[i][k];
                }
                matrizNumbers[i] = matrizNumbers[actualLine]*multNumber - matrizNumbers[i];
                actualLine++;
            }
        }
        for (int i = 0; i < matrizLetters.length; i++) {
            double numberValue = matrizNumbers[matrizNumbers.length-1-i];
            for (int j = 0; j < i; j++) {
                numberValue -= matrizLetters[matrizLetters.length-1-i][matrizLetters.length-1-j]*result[result.length-1-j];
            }
            numberValue /= matrizLetters[matrizLetters.length-1-i][matrizLetters.length-1-i];
            result[result.length-1-i] = numberValue;
        }
        return result;
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
        double[] equationResult;
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

    public static void printResultI(double[] matriz) {
        StringBuilder sbr = new StringBuilder();
        DecimalFormat df = new DecimalFormat("0.00000");
        double value = matriz[0];
        if (value != 0) sbr.append("(").append(value).append(") ");
        for (int i = 1; i < matriz.length; i++) {
            value = matriz[i];
            if (value != 0) {
                sbr.append("+ (").append(df.format(value));
                sbr.append("x^").append(i).append(") ");
            }
        }
        System.out.println(sbr);

    }

    public double[] getInterpolation() {
        return interpolation;
    }

    public void setInterpolation(double[] interpolation) {
        this.interpolation = interpolation;
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
