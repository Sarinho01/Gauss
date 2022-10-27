import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Methods user = new Methods();

        try {
            UI.setMatriz(user, input);
            UI.setError(user, input);
        }catch (Exception e){
            return;
        }

        while(true) {
            try {
                UI.printMenu();
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
                    case 7:
                        UI.interpolacao(input);
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            }catch (GaussException e){
                System.out.println(e.getMessage());
            }catch (Exception e){
               e.printStackTrace();
            }
        }

    }
}
