package utils;

import DAOs.BankDAO;
import DAOs.UserDAO;
import datastructures.MyArrayList;
import exceptions.AccountDoesNotExistException;
import exceptions.IncorrectPasswordException;
import models.Account;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class DepositFunds {

    static double deposit_amount;
    static int account_number;

    public static boolean deposit(String username)
    {
        Scanner sc = new Scanner(System.in);
        //get relevant information from the user
        System.out.println("======DEPOSIT FUNDS======");
        System.out.println("Enter the account number you want to deposit into: ");
        account_number = Integer.parseInt(sc.nextLine());
        try{
            Connection conn = ConnectionManager.getConnection();
            //create dao instance to perform deposit
            BankDAO dao = new BankDAO(conn);

            if(dao.accountBelongsById(account_number,username))
            {
                System.out.println("Enter the amount to deposit: ");
                deposit_amount = Double.parseDouble(sc.nextLine());
            }
            else
            {
                deposit(username);
            }

            if(dao.depositFunds(account_number,deposit_amount))
            {
                System.out.println(deposit_amount + " deposited into account number " + account_number);
                AccountList.AccountList(username);
            }

        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean depositWithList(String username)
    {
        System.out.println("======ACCOUNT LIST======");
        try{
            //we're getting the account list to print out
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
        System.out.println("======DEPOSIT FUNDS======");
        System.out.println("Enter the account number you want to deposit into: ");
        account_number = Integer.parseInt(sc.nextLine());
        try{
            Connection conn = ConnectionManager.getConnection();
            //create dao instance to perform deposit
            BankDAO dao = new BankDAO(conn);

            if(dao.accountBelongsById(account_number,username))
            {
                //if the account belongs to the user then ask how much they'd like to deposit
                System.out.println("Enter the amount to deposit: ");
                deposit_amount = Double.parseDouble(sc.nextLine());
            }
            else
            {
                //if accountBelongsById fails it means the user entered an account # that isn't theirs
                //I send them back up to the top of the deposit function to try again.
                depositWithList(username);
            }

            if(dao.depositFunds(account_number,deposit_amount))
            {
                //By this point we've checked the account number and gotten the amount.
                //We get into here by successfully depositing money into an account
                System.out.println(deposit_amount + " deposited into account number " + account_number);
                AccountList.AccountList(username);
            }

        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

}
