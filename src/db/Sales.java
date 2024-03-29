package db;

import java.io.FileNotFoundException;

import static db.Categories.readCategories;
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
                counter++;
            }
        }

        return arrayOfNonRepeatedGames;
    }

    public static String[][] sortGamesByProfit() throws FileNotFoundException {

        String[][] categories = readCategories();
        String[][] gamesSales = readSales();
        String[] games = getGames();
        Double[] valuesByGame = new Double[games.length];
        String[][] sortGames = new String[games.length][2];
        double sum = 0.0;

        for(int i = 0; i < valuesByGame.length; i++){
            valuesByGame[i] = 0.0;
        }

        for(int i = 0; i < games.length; i++ ){
            for(int k = 0; k < gamesSales.length; k++){
                if(games[i].equalsIgnoreCase(gamesSales[k][4])){
                    for(int j = 0; j < categories.length; j++){
                        if(gamesSales[k][3].equalsIgnoreCase(categories[j][0])){
                            double percentage = Double.parseDouble(categories[j][1])/100;
                            sum += Double.parseDouble(gamesSales[i][5]) * percentage;
                        }
                    }
                }
            }

            valuesByGame[i] = sum;
            sum = 0.0;
        }

        for(int j = 0; j < valuesByGame.length - 1; j++){
            for(int k = 0; k < valuesByGame.length - 1; k++){
                if(valuesByGame[k]>valuesByGame[k+1]){
                    double temp = valuesByGame[k];
                    valuesByGame[k] = valuesByGame[k+1];
                    valuesByGame[k+1] = temp;

                    String temporary = games[k];
                    games[k] = games[k+1];
                    games[k+1] = temporary;

                }
            }
        }

        for(int i = 0; i < valuesByGame.length; i++){
            sortGames[i][0] = games[i];
            sortGames[i][1] = Double.toString(valuesByGame[i]);
        }

        return sortGames;
    }


}
