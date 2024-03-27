package db;

import java.io.FileNotFoundException;
import static db.File.readFile;

public class Sales {

    public static String[][] readSales() throws FileNotFoundException {
        String path = "catalogue/GameStart_Vendas.csv";

        return readFile(path);
    }

    public static String[] getGames() throws FileNotFoundException {
        String[][] informationSales = readSales();
        String[] arrayGames = new String[informationSales.length];
        boolean[] gameIsDuplicate = new boolean[arrayGames.length];
        int count = 0;

        /*Created array with all the games*/
        for(int i = 0; i < informationSales.length;i++){
            arrayGames[i] = informationSales[i][4];
        }

        /*Created array to describe whether a game is a duplicate or not using true for duplicates and false for unique*/
        for(int i = 0; i < arrayGames.length; i++){
            boolean found = false;

            for(int j = 0; j < i; j++){
                if(arrayGames[i].equalsIgnoreCase(arrayGames[j])){
                    found = true;
                    break;
                }
            }
            gameIsDuplicate[i] = found;
        }

        /*Count to use as the size of the games array without duplicates*/
        for(int i = 0; i < gameIsDuplicate.length; i++){
            if(gameIsDuplicate[i] == false){
                count++;
            }
        }

        int counter = 0;
        String[] arrayOfNonRepeatedGames = new String[count];

        for(int i = 0; i < gameIsDuplicate.length; i++){
            if(gameIsDuplicate[i] == false){
                arrayOfNonRepeatedGames[counter] = arrayGames[i];
            }
            counter++;
        }

        return arrayOfNonRepeatedGames;
    }

}
