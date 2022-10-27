import java.util.Scanner;

public class UI {
    public static void printMenu() {
        System.out.print("""
                1 - Resolver sistemas
                2 - Interpolação
                0 - Fechar o programa
                Opção escolhida:\040""");
    }

    public static void printMenuGauss() {
        System.out.print("""
                1 - Usar método de Gauss
                2 - Usar método de Gauss-Jacobi
                3 - Usar método de Gauss-Seidel
                4 - Utilizar os 3 métodos e comparar depois
                5 - Mudar a matriz
                6 - Mudar o erro
                0 - Fechar o programa
                Opção escolhida:\040""");
    }

    public static void printMenuPolarizacao() {
        System.out.print("""
                1 - Mostrar equação
                2 - Mudar os pontos
                3 - Achar o valor de Y usando X
                0 - Voltar
                Opção escolhida:\040""");
    }

    public static void gaussMethod(Scanner input, Methods user) {
        try {
            UI.setMatriz(user, input);
            UI.setError(user, input);
        } catch (Exception e) {
            return;
        }

        while (true) {
            try {
                UI.printMenuGauss();
                int optionValue = input.nextInt();
                double[] result;

                switch (optionValue) {
                    case 0:
                        return;
                    case 1:
                        result = Methods.gauss(user.getMatrizLetters(), user.getMatrizNumbers());
                        Methods.printResultSE(result, 1);
                        break;
                    case 2:
                        Methods.gaussJacobi(user.getMatrizLetters(), user.getMatrizNumbers(), user.getError());
                        break;
                    case 3:
                        Methods.gaussSeidel(user.getMatrizLetters(), user.getMatrizNumbers(), user.getError());
                        break;
                    case 4:
                        System.out.println("GAUSS: ");
                        result = Methods.gauss(user.getMatrizLetters(), user.getMatrizNumbers());
                        Methods.printResultSE(result, 1);
                        System.out.println("JACOBI: ");
                        Methods.gaussJacobi(user.getMatrizLetters(), user.getMatrizNumbers(), user.getError());
                        System.out.println("SEIDEL: ");
                        Methods.gaussSeidel(user.getMatrizLetters(), user.getMatrizNumbers(), user.getError());
                        break;
                    case 5:
                        UI.setMatriz(user, input);
                        break;
                    case 6:
                        UI.setError(user, input);
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            } catch (GaussException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void interpolacao(Scanner input, Methods user) {
        try {
            UI.setPoints(user, input);
        } catch (Exception e) {
            System.out.println("Valor inválido");
        }

        while (true) {
            try {
                UI.printMenuPolarizacao();
                int option = input.nextInt();
                switch (option) {
                    case 0:
                        return;
                    case 1:
                        Methods.printResultI(user.getInterpolation());
                        break;
                    case 2:
                        UI.setPoints(user, input);
                        break;
                    case 3:
                        UI.valueYInX(user, input);
                    default:
                        System.out.println("Opção inválida");
                        break;
                }
            }catch (Exception e){
                System.out.println("Valor inválido");

            }

        }
    }

    public static void valueYInX(Methods user, Scanner input) {
        System.out.print("Digite o valor de x: ");
        double xValue = input.nextDouble();
        double result = 0;
        double[] interpolationEquation = user.getInterpolation();
        for (int i = 0; i < interpolationEquation.length; i++) {
            result += interpolationEquation[i] * Math.pow(xValue, i);
        }
        System.out.println(result);
    }

    public static void setPoints(Methods user, Scanner input) {
        System.out.print("Digite quantos pontos x/y vai ter a tabela: ");
        int pointSize = input.nextInt();
        double[] xNumbers = new double[pointSize];
        double[] yNumbers = new double[pointSize];
        for (int i = 0; i < pointSize; i++) {
            System.out.print("Digite o valor do ponto x" + (i + 1) + ": ");
            xNumbers[i] = input.nextDouble();
            System.out.print("Digite o valor do ponto y" + (i + 1) + ": ");
            yNumbers[i] = input.nextDouble();

        }
        double[] result = Methods.interpolarizacaoPolinomial(xNumbers, yNumbers);
        user.setInterpolation(result);
        System.out.println("Pontos colocados com sucesso!");
    }

    public static void setMatriz(Methods user, Scanner input) {


        System.out.print("Digite a quantidade de váriaveis: ");
        int numberOfVariable = input.nextInt();

        user.setMatrizLetters(UI.changeMatrizLetters(numberOfVariable, input));
        user.setMatrizNumbers(UI.changeMatrizNumbers(numberOfVariable, input));
    }

    public static double[][] changeMatrizLetters(int numberOfVariable, Scanner input) {
        double[][] matriz = new double[numberOfVariable][numberOfVariable];

        for (int i = 0; i < numberOfVariable; i++) {
            System.out.println("Equação " + (i + 1) + ": ");
            for (int j = 0; j < numberOfVariable; j++) {
                System.out.print("Valor de X" + (j + 1) + ": ");
                matriz[i][j] = input.nextDouble();
            }
        }

        return matriz;
    }

    public static double[] changeMatrizNumbers(int numberOfVariable, Scanner input) {
        double[] matrizNumber = new double[numberOfVariable];

        for (int i = 0; i < numberOfVariable; i++) {
            System.out.print("Entre o número da equação " + (i + 1) + ": ");
            matrizNumber[i] = input.nextDouble();
        }

        return matrizNumber;
    }

    public static void setError(Methods user, Scanner input) {
        System.out.println("Digite o valor do erro: ");
        user.setError(input.nextDouble());
    }
}
