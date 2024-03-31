package db;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class File {

    public static int countLines(String path) throws FileNotFoundException {

        Scanner readFile = new Scanner(new java.io.File(path));
        int count = 0;

        while (readFile.hasNextLine()) {
            readFile.nextLine();
            count++;
        }

        return count;

    }


    public static String[][] readFile(String path) throws FileNotFoundException {

        int lines = countLines(path);

        Scanner readFile = new Scanner(new java.io.File(path));
        String header = readFile.nextLine();

        int cols = header.split(";").length;

        String[][] matrixFileInformation = new String[lines-1][cols];
        String line;
        int counter = 0;

        while (readFile.hasNextLine()) {
            line = readFile.nextLine();
            String[] lineItems = line.split(";");

            for(int k = 0; k < cols; k++) {
                matrixFileInformation[counter][k] = lineItems[k];
            }
            counter++;

        }

        return matrixFileInformation;
    }


    public static void menuGraphicCatalog(){

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
                    try {
                        readFileGraphicCatalog("catalogue/CatalogoGrafico/callOfDuty.txt");
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 2 :
                    System.out.println("Fifa");
                    try {
                        readFileGraphicCatalog("catalogue/CatalogoGrafico/fifa.txt");
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 3:
                    System.out.println("Hollow Knight");
                    try {
                        readFileGraphicCatalog("catalogue/CatalogoGrafico/hollowKnight.txt");
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 4:
                    System.out.println("Mortal Kombat");
                    try {
                        readFileGraphicCatalog("catalogue/CatalogoGrafico/mortalKombat.txt");
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 5:
                    System.out.println("Overcooked");
                    try {
                        readFileGraphicCatalog("catalogue/CatalogoGrafico/overcooked.txt");
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 6:
                    System.out.println("Witcher 3: Wild Hunt");
                    try {
                        readFileGraphicCatalog("catalogue/CatalogoGrafico/witcher3.txt");
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 7:
                    System.out.println("Minecraft");
                    try {
                        readFileGraphicCatalog("catalogue/CatalogoGrafico/minecraft.txt");
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
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

    public static void readFileGraphicCatalog(String path) throws FileNotFoundException {

        String line;
        Scanner readFile = new Scanner(new java.io.File(path));
        System.out.println();

        while (readFile.hasNextLine()) {
            line = readFile.nextLine();
            System.out.println(line);

        }
        System.out.println();
    }

}
