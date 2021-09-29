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

    public static boolean withdraw(String username)
    {
        Scanner sc = new Scanner(System.in);
        //get relevant information from the user
        System.out.println("======WITHDRAW FUNDS======");
        System.out.println("Enter the account number you want to withdraw from: ");
        account_number = Integer.parseInt(sc.nextLine());
        try{
            Connection conn = ConnectionManager.getConnection();
            //create dao instance to perform deposit
            BankDAO dao = new BankDAO(conn);

            if(dao.accountBelongsById(account_number,username))
            {
                System.out.println("Enter the amount to withdraw: ");
                withdraw_amount = Double.parseDouble(sc.nextLine());
            }
            else
            {
                withdraw(username);
            }

            if(dao.withdrawFunds(account_number,withdraw_amount))
            {
                System.out.println(withdraw_amount + " withdrawn from account number " + account_number);
                AccountList.AccountList(username);
            }

        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean withdrawWithList(String username)
    {
        System.out.println("======ACCOUNT LIST======");
        try{
            Connection conn = ConnectionManager.getConnection();
            BankDAO dao = new BankDAO(conn);
            MyArrayList<Account> accountList = new MyArrayList<>();
            accountList = dao.getAccountsByUser(username);

            for(int i = 0; i<accountList.size();i++)
            {
                PrintList.printMyList(accountList.get(i));
            }

        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }

        Scanner sc = new Scanner(System.in);
        //get relevant information from the user
        System.out.println("======WITHDRAW FUNDS======");
        System.out.println("Enter the account number you want to withdraw from: ");
        account_number = Integer.parseInt(sc.nextLine());
        try{
            Connection conn = ConnectionManager.getConnection();
            //create dao instance to perform deposit
            BankDAO dao = new BankDAO(conn);

            if(dao.accountBelongsById(account_number,username))
            {
                System.out.println("Enter the amount to withdraw: ");
                withdraw_amount = Double.parseDouble(sc.nextLine());
            }
            else
            {
                withdrawWithList(username);
            }

            if(dao.withdrawFunds(account_number,withdraw_amount))
            {
                System.out.println(withdraw_amount + " withdrawn from account number " + account_number);
                AccountList.AccountList(username);
            }

        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
