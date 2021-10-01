package utils;

import java.util.Scanner;

public class MainMenu {

    public static void viewMenu()
    {
        //Initiate scanner to get new input
        Scanner sc = new Scanner(System.in);
        //We want to hold the current username that's logged in once that happens.
        String currentUser = "";

        boolean running = true;
        while(running)
        {
            System.out.println("======MAIN MENU======\nEnter Selection:\n\n1)Register for an account.\n2)Log in to your account.");
            String input = sc.nextLine();
            switch(input)
            {
                case "1":
                    //Register the user.
                    if(Register.RegisterUser())
                    {

                    }
                    else
                    {
                        System.out.println("Account was not created. Please try again.");
                        Register.RegisterUser();
                    }
                case "2":
                    currentUser = Login.LoginUser();
                    if(currentUser != null)
                    {
                        System.out.println("Successful login. Welcome, " + currentUser);
                        //log in was successful
                        LoggedInMenu.viewLoggedInMenu(currentUser);
                    }
                    else
                    {
                        System.out.println("Log in failed.");
                        Login.LoginUser();
                    }
                default:
                    System.out.println("Invalid input! Please type one of the numbers from the list.");
                    continue;
            }
        }
    }
}
