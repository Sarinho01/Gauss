import entities.Methods;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Methods user = new Methods();

        while (true) {
            try {
                UI.printMenu();
                int option = input.nextInt();
                switch (option) {
                    case 0:
                        return;
                    case 1:
                        UI.gaussMethod(input, user);
                        break;
                    case 2:
                        UI.interpolacao(input, user);
                        break;
                    default:
                        System.out.println("Opção inválida");
                        break;
                }
            }catch (InputMismatchException e){
                System.out.println("Valor inválido.");
                input.next();
            }
        }

    }
}
