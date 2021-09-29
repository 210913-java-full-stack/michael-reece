import utils.*;

import java.util.Scanner;

public class Driver {
    public static void main(String[] args){
        //scanner object bound to system.in listening for input from the user
        Scanner sc = new Scanner(System.in);
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
                        //System.out.println("Account created successfully! Please log in.");
                        continue;
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
                        break;
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
            running = false;
        }
        //Once the user is logged in, we have a new logged in menu
        running = true;
        while(running)
        {
            System.out.println("======LOGGED IN======\nEnter Selection:\n\n1)Create new bank account.\n2)Deposit funds.\n3)Withdraw funds.\n4)Look at all accounts");
            String input = sc.nextLine();
            switch(input)
            {
                case "1":
                    //Create a new bank account.
                    if(NewBankAccount.newBankAccount(currentUser))
                    {
                        System.out.println("Made new account!");
                    }
                    else
                    {
                        System.out.println("Unable to create account.");
                    }
                    break;
                case "2":
                    //Deposit funds
                    DepositFunds.depositWithList(currentUser);
                    break;
                case "3":
                    //Withdraw funds
                    WithdrawFunds.withdrawWithList(currentUser);
                    break;
                case "4":
                    //Look at all accounts
                    AccountList.AccountList(currentUser);
                    running = false;
                default:
                    System.out.println("Invalid input! Please type one of the numbers from the list.");
            }
        }
    }
}
