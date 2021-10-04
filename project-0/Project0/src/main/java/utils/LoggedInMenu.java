package utils;

import java.util.Scanner;

public class LoggedInMenu {

    public static void viewLoggedInMenu(String currentUser)
    {
        //once the user is logged in, we can run this menu getting the username of the current logged in customer
        Scanner sc = new Scanner(System.in);
        Boolean running = true;
        while(running)
        {
            System.out.println("======LOGGED IN======\nEnter Selection:\n1)Create new bank account.\n2)Deposit funds." +
                    "\n3)Withdraw funds.\n4)Transfer funds between accounts\n5)Look at all accounts\nQ)Quit");
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
                    //Transfer funds between accounts
                    TransferBetweenAccounts.transferFunds(currentUser);
                case "5":
                    //Look at all accounts
                    AccountListMenu.viewMenu(currentUser);
                    running = false;
                case "Q":
                case "q":
                    System.exit(0);
                default:
                    System.out.println("Invalid input! Please type one of the numbers from the list.");
            }
        }
    }
}
