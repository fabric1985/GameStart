package admin;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static admin.MenuAdmin.*;
import static db.Categories.*;
import static db.Clients.*;
import static db.Sales.*;

public class FunctionsAdmin {
    /*----> Function that validates the administrator login <----*/
    public static void loginAdmin(String user, String password) throws FileNotFoundException {

        String path = "catalogue/GameStart_Admins.csv";
        Scanner readFile = new Scanner(new File(path));

        while (readFile.hasNextLine()) {
            String line = readFile.nextLine();
            String[] itemsOfLine = line.split(";");
            for (int i = 0; i < itemsOfLine.length; i++){
                if(user.equals(itemsOfLine[i]) && password.equals(itemsOfLine[1])) {
                System.out.println("Login successfully");
                System.out.println();
                menuAdmin();
                break;
            }
            }
        }
        System.out.println("Invalid Login");
    }
    /*----> Function that has the menu with file consultation options <----*/
    public static void fileConsultation(){
        Scanner input = new Scanner(System.in);
        int decision;

        do{
            System.out.println("=======================================================");
            System.out.println("|                FILE CONSULTATION                    |");
            System.out.println("=======================================================");

            System.out.println("Choose what you would like to consult: ");
            System.out.println();
            System.out.println("1 - Sales ");
            System.out.println("2 - Clients ");
            System.out.println("3 - Category");
            System.out.println("4 - Exit");
            decision = input.nextInt();

            switch (decision){
                case 1:
                    System.out.println("=======================================================");
                    System.out.println("|                SALES INFORMATION                    |");
                    System.out.println("=======================================================");
                    System.out.println();
                    try {
                        printInformationFile(readSales());
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 2 :
                    System.out.println("=======================================================");
                    System.out.println("|                CLIENTS INFORMATION                  |");
                    System.out.println("=======================================================");
                    System.out.println();
                    try {
                        printInformationFile(readClients());
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 3:
                    System.out.println("=======================================================");
                    System.out.println("|                    CATEGORIES                       |");
                    System.out.println("=======================================================");
                    System.out.println();
                    try {
                        printInformationFile(readCategories());
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 4:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }while(decision!=4);

    }



    /*----> Function that only prints the file <----*/
    public static void printInformationFile(String[][] matrixFileInformation){

        for (int i = 0; i < matrixFileInformation.length; i++) {
            for (int k = 0; k < matrixFileInformation[0].length; k++) {
                System.out.print(matrixFileInformation[i][k] + "\t|\t");
            }
            System.out.println();
        }

    }

    public static void salesInformation() throws FileNotFoundException {

        String[][] information = readSales();

        int count = 0;
        double sum = 0.0;

        for(int i = 0 ; i < information.length; i++){
            sum += Double.parseDouble(information[i][information[0].length - 1]);
            count ++;
        }

        System.out.println();
        System.out.println(count+" sales were made.");
        System.out.println("The total sales value was $"+sum);
    }

    public static void totalProfit() throws FileNotFoundException {

        String[][] informationSales = readSales();
        String[][] informationCategories = readCategories();

        String [] totalForCategories = new String[informationCategories.length];
        double [] valuesTotalMarginOfCategories = new double[informationCategories.length];
        double sum = 0.0;
        int count = 0;


        /*This loop puts the categories in an array*/
        for (int i = 0; i < informationCategories.length; i++) {
            totalForCategories[i] = informationCategories[i][0];
        }

        while(count < totalForCategories.length){

            for (int i = 0 ; i < informationSales.length; i++){
                if(informationSales[i][3].equalsIgnoreCase(totalForCategories[count])){
                    sum += Double.parseDouble(informationSales[i][5]);
                }
            }
            double margin = Double.parseDouble(informationCategories[count][1])/100;
            valuesTotalMarginOfCategories[count] = sum * margin;

            count++;
            sum = 0.0;
        }

        /*The dates of  array totalForCategories and valuesTotalMarginOfCategories, are connected by index*/
        for(int i = 0; i < totalForCategories.length; i++){
            System.out.println("Categoria: "+totalForCategories[i]+"| VALOR: "+ valuesTotalMarginOfCategories[i]);
            sum += valuesTotalMarginOfCategories[i];

        }
        System.out.println("=======================================================");
        System.out.println("The total profit based on the appropriate percentages was $"+sum);
        System.out.println("=======================================================");

    }

    public static void customerSearch() throws FileNotFoundException {

        Scanner input = new Scanner(System.in);
        int search;

        System.out.println("Enter the number for search by idClient: ");
        search = input.nextInt();

        String[] client = getClientByID(search);

        if (client[0] == null) {
            System.out.println("Could not find client with that ID");
        } else {
            System.out.println("ID: "+ client[0] + "\t|\t");
            System.out.println("Name: "+client[1] + "\t|\t");
            System.out.println("Cellphone:: "+client[2] + "\t|\t");
            System.out.println("Email: "+client[3] + "\t|\t");
        }
    }

    public static void mostExpensiveGame() throws FileNotFoundException {
        String[][] salesInformation = readSales();
        double moreExpensive = 0.0;
        String nameGameMoreExpensive = "";

        for (int i = 0; i < salesInformation.length; i++) {
            double price = Double.parseDouble(salesInformation[i][5]);
            if(price > moreExpensive){
                moreExpensive = price;
                nameGameMoreExpensive = salesInformation[i][4];
            }
        }

        System.out.println("The most expensive is: "+nameGameMoreExpensive);

        for (int i = 0; i < salesInformation.length; i++) {
            double price = Double.parseDouble(salesInformation[i][5]);
            if(price == moreExpensive){
                int clientID = Integer.parseInt(salesInformation[i][1]);

                String[] client = getClientByID(clientID);
                System.out.println("Name: "+client[1]);
            }
        }
    }

}
