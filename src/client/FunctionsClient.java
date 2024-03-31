package client;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import static db.Categories.readCategories;
import static db.Clients.readClients;
import static db.File.menuGraphicCatalog;
import static db.File.readFileGraphicCatalog;
import static db.Sales.*;

public class FunctionsClient {

    public static void gamesCatalog() throws FileNotFoundException {
        String[][] games = getGames();
        System.out.println("=======================================================");
        System.out.println("|                 Catalog Games                       |");
        System.out.println("=======================================================");

        for (int i = 0; i < games.length; i++){
            System.out.println("* "+games[i][0]);
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

    public static void graphicCatalog() throws FileNotFoundException {

        menuGraphicCatalog();
    }

    public static void printPublisherCatalog() throws FileNotFoundException {
        String[][] games = getGames();
        String[][] categories = readCategories();

        Scanner input = new Scanner(System.in);
        String publisher;

        System.out.println("Search for games available by the publisher: ");
        publisher = input.nextLine();

        System.out.println("**** " + publisher + " ****");

        for(int i = 0; i < categories.length; i++){
            String toPrint = "";

            for(int k = 0; k < games.length; k++){
                if(games[k][1].equalsIgnoreCase(publisher) && games[k][2].equalsIgnoreCase(categories[i][0])){
                    toPrint += games[k][0] +"\n";
                }
            }

            if (toPrint != "") {
                System.out.println("__"+categories[i][0]+"__");
                System.out.println(toPrint);
            }
        }
    }

    public static void printCategoryCatalog() throws FileNotFoundException {
        String[][] games = getGames();
        String[] publishers = getPublishers();

        Scanner input = new Scanner(System.in);
        String category;

        System.out.println("Search for games available by the category: ");
        category = input.nextLine();

        System.out.println("**** " + category + " ****");

        for(int i = 0; i < publishers.length; i++){
            String toPrint = "";

            for(int k = 0; k < games.length; k++){
                if(games[k][1].equalsIgnoreCase(publishers[i]) && games[k][2].equalsIgnoreCase(category)){
                    toPrint += games[k][0] +"\n";
                }
            }

            if (toPrint != "") {
                System.out.println("__"+publishers[i]+"__");
                System.out.println(toPrint);
            }
        }
    }

    public static void latestGameSold() throws FileNotFoundException {
        String[][] sales = readSales();
        String[] arrayGames = new String[sales.length];

        /*Created array with all the games*/
        for(int i = 0; i < sales.length;i++){
            arrayGames[i] = sales[i][4];
        }

        int index = 0;

        for(int i = 0; i < arrayGames.length; i++){
            boolean duplicate = false;

            for(int j = 0; j < i; j++){
                if(arrayGames[i].equalsIgnoreCase(arrayGames[j])){
                    duplicate = true;
                    break;
                }
            }

            if (duplicate == false) {
                index = i;
            }
        }

        System.out.println(arrayGames[index]);
    }

}
