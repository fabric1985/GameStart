package db;

import java.io.FileNotFoundException;
import static db.File.readFile;

public class Sales {

    public static String[][] readSales() throws FileNotFoundException {
        String path = "catalogue/GameStart_Vendas.csv";

        return readFile(path);
    }
}
