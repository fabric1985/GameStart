package admin;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static admin.Functions_Admin.*;

public class Menu_Admin {
    public static void menuAdmin(){

        Scanner input = new Scanner(System.in);
        int decision;

        do{
            System.out.println("=======================================================");
            System.out.println("|                   MENU ADMIN                        |");
            System.out.println("=======================================================");

            System.out.println("Choose the desired function: ");
            System.out.println();
            System.out.println("1 - File Consultation ");
            System.out.println("2 - Sales Amount ");
            System.out.println("3 - Total Profit");
            System.out.println("4 - Customer Search");
            System.out.println("5 - Most Expensive Game");
            System.out.println("6 - Best Customers");
            System.out.println("7 - Best Category");
            System.out.println("8 - Search for Sales");
            System.out.println("9 - Top 5 Games");
            System.out.println("10 - Bottom 5 Games");
            System.out.println("11 - Exit");
            decision = input.nextInt();

            switch (decision){
                case 1:
                    System.out.println("File Consultation");
                    fileConsultation();
                    break;
                case 2 :
                    System.out.println("Sales Amount");
                    try {
                        salesInformation("catalogue/GameStart_Vendas.csv");
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 3:
                    System.out.println("Total Profit");
                    try {
                        totalProfit("catalogue/GameStart_Vendas.csv", "catalogue/GameStart_Categorias.csv");
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 4:
                    System.out.println("Customer Search");
                    break;
                case 5:
                    System.out.println("Most Expensive Game");
                    break;
                case 6:
                    System.out.println("Best Customers");
                    break;
                case 7:
                    System.out.println("Best Category");
                    break;
                case 8:
                    System.out.println("Search for Sales");
                    break;
                case 9:
                    System.out.println("Top 5 Games");
                    break;
                case 10:
                    System.out.println("Bottom 5 Games");
                    break;
                case 11:
                    System.out.println("See you soon!");
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }while(decision!=11);


    }
}
