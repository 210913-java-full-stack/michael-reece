package DAOs;

import datastructures.MyArrayList;

import java.sql.SQLException;

public interface bankCrud<T> {
    //will need a connection

    //get current BankAccount ID
    public int getAccountId() throws SQLException;

    //get accounts by username
    public MyArrayList<T> getAccountsByUser(String username) throws SQLException;

    //create bank account
    public void newBankAccount(String account_type, String username) throws SQLException;

    //deposit funds into account by ID
    public boolean depositFunds(int account_id, double deposit_amount) throws SQLException;

    //check that an account belongs to a username
    public boolean accountBelongsById(int account_id, String username) throws SQLException;

    //withdraw funds from account by ID
    public boolean withdrawFunds(int account_id, double withdraw_amount) throws SQLException;

    //do we have enough funds?
    public boolean validFundsForWithdraw(int account_id, double withdraw_amount) throws SQLException;

    //transfer funds between two of a user's accounts
    public boolean fundsBetweenAccounts(int account1, int account2, double amount) throws SQLException;
}
