package utils;

import DAOs.BankDAO;
import datastructures.MyArrayList;
import models.Account;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class AccountList {

    static Scanner sc = new Scanner(System.in);

    public static void AccountList(String username)
    {
        System.out.println("======ACCOUNT LIST======\n");

        try{
            //We are getting the accounts in an arraylist in order to print out for the user.
            Connection conn = ConnectionManager.getConnection();
            BankDAO dao = new BankDAO(conn);
            MyArrayList<Account> accountList = new MyArrayList<>();
            accountList = dao.getAccountsByUser(username);

            for(int i = 0; i<accountList.size();i++)
            {
                //call printmylist to format it.
                PrintList.printMyList(accountList.get(i));
            }

        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("========================");
        //after showing them their accounts, printout asking them if they want to deposit or withdraw from their accounts
        System.out.println("What would you like to do?\n1) Deposit Funds\n2) Withdraw funds\nQ) Quit");
        String input = sc.nextLine();
        switch(input)
        {
            case "1":
                DepositFunds.deposit(username);
                break;
            case "2":
                WithdrawFunds.withdraw(username);
                break;
            case "Q":
            case "q":
                break;
        }
    }
}
