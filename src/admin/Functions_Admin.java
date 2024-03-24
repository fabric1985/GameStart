package admin;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import static admin.Menu_Admin.*;

public class Functions_Admin {
    /*----> Function that validates the administrator login <----*/
    public static void loginAdmin(String user, String password) throws FileNotFoundException {

        String path = "catalogue/GameStart_Admins.csv";
        Scanner readFile = new Scanner(new File(path));

        while (readFile.hasNextLine()) {
            String line = readFile.nextLine();
            String[] itemsOfLine = line.split(";");
            if (user.equals(itemsOfLine[0]) && password.equals(itemsOfLine[1])) {
                System.out.println("Login successfully");
                System.out.println();
                menuAdmin();
                break;
            } else {
                System.out.println("Invalid Login");
            }
        }
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
                        informationFile("catalogue/GameStart_Vendas.csv");
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
                        informationFile("catalogue/GameStart_Clientes.csv");
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
                        informationFile("catalogue/GameStart_Categorias.csv");
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

    /*----> Function that counts the line number of the file to be
            able to manipulate it in a multidimensional array<----*/
    public static int countLines(String path) throws FileNotFoundException {

        Scanner readFile = new Scanner(new File(path));
        int count = 0;
        while (readFile.hasNextLine()) {
            String line = readFile.nextLine();
            count++;
        }
        return count;

    }

    /*----> Function that manipulates the file and converts it to a
            multidimensional array<----*/
    public static String[][] informationFile(String path) throws FileNotFoundException {

        int lines = countLines(path);

        Scanner readFile = new Scanner(new File(path));
        String header = readFile.nextLine();

        int cols = header.split(",").length;

        String[][] matrixFileInformation = new String[lines-1][cols];
        String line;
        int counter = 0;

        while (readFile.hasNextLine()) {
            line = readFile.nextLine();
            String[] lineItems = line.split(",");

            for(int k = 0; k < cols; k++) {
                matrixFileInformation[counter][k] = lineItems[k];
                System.out.println(matrixFileInformation[counter][k]);
            }
            counter++;
        }

       /* for(int i = 0; i < matrixFileInformation.length;i++){
            System.out.println(matrixFileInformation[i][0]+" | "+matrixFileInformation[i][1]+" | "+matrixFileInformation[i][2]+" | "+matrixFileInformation[i][3]+" | "+matrixFileInformation[i][4]+" | "+matrixFileInformation[i][5]);
        }*/
        return matrixFileInformation;
    }

    public static void salesInformation(String path) throws FileNotFoundException {

        String[][] information = informationFile(path);

        int count = 0;
        double sum = 0.0;

        for(int i = 0 ; i < information.length; i++){
            sum += Double.parseDouble(information[i][information[0].length - 1]);
            count ++;
        }

        System.out.println(count+" sales were made.");
        System.out.println("The total sales value was $"+sum);

    }

}
