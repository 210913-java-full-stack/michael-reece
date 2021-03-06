package utils;

import DAOs.BankDAO;
import datastructures.MyArrayList;
import models.Account;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class WithdrawFunds {

    static double withdraw_amount;
    static int account_number;

    public static void withdrawWithList(String username)
    {
        //print account list
        PrintAccountList.printAccountList(username);

        Scanner sc = new Scanner(System.in);
        //get relevant information from the user
        System.out.println("======WITHDRAW FUNDS======");
        System.out.println("Enter the account number you want to withdraw from: ");
        account_number = Integer.parseInt(sc.nextLine());
        try{
            Connection conn = ConnectionManager.getConnection();
            //create dao instance to perform withdraw
            BankDAO dao = new BankDAO(conn);

            //if the account belongs to the user
            if(dao.accountBelongsById(account_number,username))
            {
                //get the amount to withdraw
                System.out.println("Enter the amount to withdraw: ");
                withdraw_amount = Double.parseDouble(sc.nextLine());
            }
            else
            {
                //if the account doesn't belong to them, send them back to try again
                withdrawWithList(username);
            }

            //okay, send in withdraw amount
            if(dao.withdrawFunds(account_number,withdraw_amount))
            {
                //successfully withdrawn, let the user know via a printout
                System.out.printf("$%.2f withdrawn from account number " + account_number + "\n", withdraw_amount);
                //then list accounts in case we want to do more
                AccountListMenu.viewMenu(username);
            }
            else
            {
                withdrawWithList(username);
            }

        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
