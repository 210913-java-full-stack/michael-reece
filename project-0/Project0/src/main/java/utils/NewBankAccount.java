package utils;

import DAOs.BankDAO;
import DAOs.UserDAO;
import exceptions.InvalidAccountTypeException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class NewBankAccount {
    static String account_type;
    static int balance;
    static int newAccountID;
    static Scanner sc = new Scanner(System.in);

    public static boolean newBankAccount(String username)
    {
        //Print out the menu and collect information from the user
        System.out.println("======NEW ACCOUNT======\nEnter account type (Savings or Checking):");
        account_type = sc.nextLine();

        try{
            //send information to make a new account. Each account is given a # automatically and a bal of 0
            Connection conn = ConnectionManager.getConnection();
            BankDAO bankDAO = new BankDAO(conn);
            bankDAO.newBankAccount(account_type, username);
            return true;

        } catch (SQLException | IOException | InvalidAccountTypeException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
