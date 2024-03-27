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


}
