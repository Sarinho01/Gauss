import java.util.Scanner;

public class UI {
    public static void printMenu() {
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

    public static void setMatriz(GaussMethods user , Scanner input) {



        System.out.println("Digite a quantidade de váriaveis: ");
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

    public static double[] changeMatrizNumbers(int numberOfVariable , Scanner input) {
        double[] matrizNumber = new double[numberOfVariable];

        for (int i = 0; i < numberOfVariable; i++) {
            System.out.print("Entre o número da equação " + (i + 1) + ": ");
            matrizNumber[i] = input.nextDouble();
        }

        return matrizNumber;
    }

    public static void setError(GaussMethods user, Scanner input) {
        System.out.println("Digite o valor do erro: ");
        user.setError(input.nextDouble());
    }
}
