package db;

import java.io.FileNotFoundException;
import static db.File.readFile;

public class Categories {

    public static String[][] readCategories() throws FileNotFoundException {
        String path = "catalogue/GameStart_Categorias.csv";

        return readFile(path);
    }
}
