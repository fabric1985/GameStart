package client;

import java.util.InputMismatchException;
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
            String name = inputName.next();
            System.out.println("Insert Contact: ");
            Scanner inputContact = new Scanner(System.in);
            int contact;
            try{
                contact = inputContact.nextInt();
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.println("Invalid option. Please enter only numbers.");
                continue;
            }
            System.out.println("Insert Email: ");
            Scanner inputEmail = new Scanner(System.in);
            String email = inputEmail .next();
            System.out.println("Name :"+name);
            System.out.println("Contact :"+contact);
            System.out.println("Email :"+email);
            System.out.println("Registration created successfully");
            System.out.println("Would you like continue to insert a new client? (yes/'y' or not/'n')");
            Scanner inputDecision = new Scanner(System.in);
            decision = inputDecision.next();
            if(!decision.equalsIgnoreCase("y") && !decision.equalsIgnoreCase("n")){
                System.out.println("Invalid option, Try again");
            }

        }
        menuClient();




    }


}
