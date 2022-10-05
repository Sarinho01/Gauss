import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        GaussMethods user = new GaussMethods();

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


                switch (optionValue) {
                    case 0:
                        return;
                    case 2:
                        GaussMethods.gaussJacobi(user.getMatrizLetters(), user.getMatrizNumbers(), user.getError());
                        break;
                    case 3:
                        GaussMethods.gaussSeidel(user.getMatrizLetters(), user.getMatrizNumbers(), user.getError());
                        break;
                    case 4:
                        System.out.println("JACOBI: ");
                        GaussMethods.gaussJacobi(user.getMatrizLetters(), user.getMatrizNumbers(), user.getError());
                        System.out.println("SEIDEL: ");
                        GaussMethods.gaussSeidel(user.getMatrizLetters(), user.getMatrizNumbers(), user.getError());
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
            }catch (GaussException e){
                System.out.println(e.getMessage());
            }catch (Exception e){
                System.out.println("input inválido");
            }
        }

    }
}
