package db;

import java.io.FileNotFoundException;

import static db.File.readFile;

public class Clients {

    public static String[][] readClients() throws FileNotFoundException {

        String path = "catalogue/GameStart_Clientes.csv";

        return readFile(path);
    }

    public static String[] getClientByID(int search) throws FileNotFoundException {

        String[][] clientsInformation = readClients();
        String[] client = new String[clientsInformation[0].length];

        for(int i = 0; i < clientsInformation.length; i++) {
            int id = Integer.parseInt(clientsInformation[i][0]);

            if(search == id){
                client = clientsInformation[i];
            }
        }

        return client;
    }
}


