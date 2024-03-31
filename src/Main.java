import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static admin.FunctionsAdmin.loginAdmin;
import static client.MenuClient.menuClient;
import static client.NewUser.*;
import static db.File.readFileGraphicCatalog;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int decision;

        do{
            System.out.println("=======================================================");
            System.out.println("|             WELCOME TO GAME START                   |");
            System.out.println("=======================================================");

            System.out.println("Choose the type of login you would like to do: ");
            System.out.println();
            System.out.println("1 - User ");
            System.out.println("2 - Admin ");
            System.out.println("3 - Exit");

            try{
                decision = input.nextInt();
            } catch (InputMismatchException e) {
                decision = -1;
                input.nextLine();
                System.out.println("Invalid option. Please enter a number.");
                continue;
            }

            switch (decision){
                case 1:
                    System.out.println("=======================================================");
                    System.out.println("|                       LOGIN                         |");
                    System.out.println("=======================================================");
                    System.out.println();
                    System.out.println("Choose the option:");
                    System.out.println("1 - Already have a registration ");
                    System.out.println("2 - New user ");
                    Scanner userLogin = new Scanner(System.in);
                    int option = input.nextInt();
                    if(option==2){
                        newUser();
                        break;
                    }
                    menuClient();

                    break;
                case 2:
                    System.out.println("=======================================================");
                    System.out.println("|                       LOGIN                         |");
                    System.out.println("=======================================================");
                    System.out.println("Enter your Username: ");
                    Scanner user = new Scanner(System.in);
                    String username = input.next();
                    Scanner pass = new Scanner(System.in);
                    System.out.println("Enter your Password: ");
                    String password = input.next();
                    try {
                        loginAdmin(username,password);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 3:
                    try {
                        readFileGraphicCatalog("catalogue/GameStart_Copyright.txt");
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }

        }while(decision != 3);

    }
}