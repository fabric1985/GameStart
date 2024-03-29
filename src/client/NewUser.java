package client;

import java.util.Scanner;

import static client.MenuClient.menuClient;

public class NewUser {
    public static void newUser() {
        Scanner input = new Scanner(System.in);
        String decision = "";

        while(!decision.equalsIgnoreCase("n") ){

            System.out.println("=======================================================");
            System.out.println("|                    INSERT CLIENT                    |");
            System.out.println("=======================================================");
            System.out.println();
            System.out.println("Insert Name: ");
            Scanner inputName = new Scanner(System.in);
            String name = input.next();
            System.out.println("Insert Contact: ");
            Scanner inputContact = new Scanner(System.in);
            String contact = input.next();
            System.out.println("Insert Email: ");
            Scanner inputEmail = new Scanner(System.in);
            String email = input.next();
            System.out.println("Name :"+name);
            System.out.println("Contact :"+contact);
            System.out.println("Email :"+email);
            System.out.println("Registration created successfully");
            System.out.println("Would you like continue to insert a new client? (yes/'y' or not/'n')");
            Scanner inputDecision = new Scanner(System.in);
            decision = input.next();
        }
        menuClient();




    }


}
