package client;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static db.Categories.readCategories;
import static db.Clients.readClients;
import static db.Sales.getGames;
import static db.Sales.readSales;

public class FunctionsClient {

    public static void gamesCatalog() throws FileNotFoundException {
        String [] games = getGames();
        System.out.println("=======================================================");
        System.out.println("|                 Catalog Games                       |");
        System.out.println("=======================================================");

        for (int i = 0; i < games.length; i++){
            System.out.println("* "+games[i]);
        }

    }

    public static void triangularNumber() throws FileNotFoundException {

        int sum = 0;
        int count = 1;

        while(sum < 121){
            sum += count;
            count ++;
            if(sum % 5 == 0){
                System.out.println(sum);
            }
        }

    }

    public static void graphicCatalog(){

        Scanner input = new Scanner(System.in);
        int decision;

        do{
            System.out.println("=======================================================");
            System.out.println("|                  GRAPHIC CATALOG                    |");
            System.out.println("=======================================================");
            System.out.println("Choose the game with graphic art you would like to see: ");
            System.out.println();
            System.out.println("1 - Call of Duty ");
            System.out.println("2 - Fifa ");
            System.out.println("3 - Hollow Knight");
            System.out.println("4 - Mortal Kombat");
            System.out.println("5 - Overcooked");
            System.out.println("6 - Witcher 3: Wild Hunt");
            System.out.println("7 - Minecraft");
            System.out.println("8 - Exit");
            decision = input.nextInt();

            switch (decision){
                case 1:
                    System.out.println("Call of Duty");
                    break;
                case 2 :

                    System.out.println("Fifa");
                    break;
                case 3:
                    System.out.println("Hollow Knight");
                    break;
                case 4:
                    System.out.println("Mortal Kombat");
                    break;
                case 5:
                    System.out.println("Overcooked");
                    break;
                case 6:
                    System.out.println("Witcher 3: Wild Hunt");
                    break;
                case 7:
                    System.out.println("Minecraft");
                    break;
                case 8:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }while(decision!=8);






    }

}
