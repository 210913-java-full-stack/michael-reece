package utils;

import DAOs.BankDAO;
import datastructures.MyArrayList;
import models.Account;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class AccountListMenu {

    static Scanner sc = new Scanner(System.in);

    public static void viewMenu(String username)
    {
        //call the printout
        PrintAccountList.printAccountList(username);
        //after showing them their accounts, printout asking them if they want to deposit or withdraw from their accounts
        System.out.println("What would you like to do?\n1) Deposit Funds\n2) Withdraw funds\n3) Transfer funds between accounts\nQ) Quit");
        String input = sc.nextLine();
        switch(input)
        {
            case "1":
                DepositFunds.depositWithList(username);
                break;
            case "2":
                WithdrawFunds.withdrawWithList(username);
                break;
            case "3":
                TransferBetweenAccounts.transferFunds(username);
            case "Q":
            case "q":
                LoggedInMenu.viewLoggedInMenu(username);
        }
    }
}
