package client;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static admin.FunctionsAdmin.fileConsultation;
import static client.FunctionsClient.*;

public class MenuClient {
    public static void menuClient() {

        Scanner input = new Scanner(System.in);
        int decision;

        do{
            System.out.println("=======================================================");
            System.out.println("|                   MENU CLIENT                       |");
            System.out.println("=======================================================");

            System.out.println("Choose the desired function: ");
            System.out.println();
            System.out.println("1 - Look for a space to park your car ");
            System.out.println("2 - Access the games catalog ");
            System.out.println("3 - Graphic catalog");
            System.out.println("4 - Publishers catalog");
            System.out.println("5 - Category catalog");
            System.out.println("6 - Latest game");
            System.out.println("7 - Exit");
            decision = input.nextInt();

            switch (decision){
                case 1:
                    System.out.println("Look for a space to park your car");
                    try {
                        triangularNumber();
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 2:
                    System.out.println("Access the games catalog");
                    try {
                        gamesCatalog();
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 3:
                    System.out.println("Graphic catalog");
                    break;
                case 4:
                    System.out.println("Publishers catalog");
                    break;
                case 5:
                    System.out.println("Category catalog");
                    break;
                case 6:
                    System.out.println("Latest game");
                    break;
                case 7:
                    System.out.println("See you soon!");
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }while(decision!=7);


    }
}
