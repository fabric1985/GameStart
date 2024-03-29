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
        System.out.println();
        System.out.println("List of customers who purchased the game: ");
        System.out.println();

        for (int i = 0; i < salesInformation.length; i++) {
            double price = Double.parseDouble(salesInformation[i][5]);
            if(price == moreExpensive){
                int clientID = Integer.parseInt(salesInformation[i][1]);

                String[] client = getClientByID(clientID);
                System.out.println("Name: "+client[1]);
            }
        }
    }

    public static void theBestCustomer() throws FileNotFoundException {
        String[][] informationSales = readSales();
        String[][] informationClients = readClients();

        String [] listOfClients = new String[informationClients.length];
        double [] valuesTotalByClients = new double[informationClients.length];
        double sum = 0.0;
        int count = 0;

        /*This loop puts the clients in an array*/
        for (int i = 0; i < informationClients.length; i++) {
            listOfClients[i] = informationClients[i][0];
        }

        /*This loop goes through the list of all customers*/
        while(count < listOfClients.length){

            /*This loop through all lines of the file informationSales*/
            for (int i = 0 ; i < informationSales.length; i++){
                if(informationSales[i][1].equalsIgnoreCase(listOfClients[count])){
                    sum += Double.parseDouble(informationSales[i][5]);
                }
            }
            valuesTotalByClients[count] = sum;

            count++;
            sum = 0.0;
        }

        /*Here we find the customer who bought the most games and their name*/
        Double biggest = 0.0;
        String theBestClient = "";

        for(int i = 0; i < valuesTotalByClients.length;i++){
            if(biggest < valuesTotalByClients[i]){
                biggest = valuesTotalByClients[i];
                theBestClient = listOfClients[i];

            }
        }

        String[] client = getClientByID(Integer.parseInt(theBestClient));
        System.out.println("Name: "+ client[1]+"|\nPhone: "+client[2]+"|\nEmail: "+client[3]+"|\nAmount: "+biggest);

        System.out.println();
        System.out.println("List of purchased game: ");
        System.out.println();
        for (int i = 0 ; i < informationSales.length; i++){
            if(informationSales[i][1].equalsIgnoreCase(theBestClient)){
                System.out.println(informationSales[i][4]);
            }
        }

    }

    public static void bestCategory() throws FileNotFoundException {

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

        Double biggest = 0.0;
        String theBestCategory = "";

        for(int i = 0; i < valuesTotalMarginOfCategories.length;i++){
            if(biggest < valuesTotalMarginOfCategories[i]){
                biggest = valuesTotalMarginOfCategories[i];
                theBestCategory = totalForCategories[i];

            }
        }

        System.out.println(theBestCategory);
        System.out.println(biggest);
    }

    public static void searchForGameSold() throws FileNotFoundException {
        String[][] informationSales = readSales();
        String[] nameGame = new String[1];
        boolean found = false;

        Scanner input = new Scanner(System.in);
        System.out.println("Enter a name of game you would like to search: ");
        nameGame[0] = input.nextLine();

        System.out.println();
        System.out.println("Customers who purchased the game: "+nameGame[0]);
        System.out.println();
        for (int i = 0; i < informationSales.length; i++) {
            if(nameGame[0].equalsIgnoreCase(informationSales[i][4])){
                found = true;
                String[] client = getClientByID(Integer.parseInt(informationSales[i][1]));
                System.out.println("Name: "+ client[1]+"|\nPhone: "+client[2]+"|\nEmail: "+client[3]);
                System.out.println();
            }
        }
        if(found == false){
            System.out.println("Game not exist in the file");
        }


    }

    public static void top5Games() throws FileNotFoundException {
        String[][] gamesSorted = sortGamesByProfit();

        int iterator = 1;
        for(int i = gamesSorted.length-1; i > gamesSorted.length-6; i--){
            System.out.println(iterator+") - "+gamesSorted[i][0]+" : "+gamesSorted[i][1]);
            iterator++;
        }
    }

    public static void bottom5Games() throws FileNotFoundException {
        String[][] gamesSorted = sortGamesByProfit();

        for (int i = 0; i < 5; i++){
            System.out.println((i+1)+") - "+gamesSorted[i][0]+" : "+gamesSorted[i][1]);
        }

    }
}
