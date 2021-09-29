package DAOs;

import datastructures.MyArrayList;
import exceptions.AccountDoesNotExistException;
import exceptions.IncorrectPasswordException;
import exceptions.InvalidAccountTypeException;
import exceptions.UserAlreadyExistsException;
import models.Account;
import models.User;

import java.sql.SQLException;

public interface bankCrud<T> {
    //will need a connection

    //get current BankAccount ID
    public int getAccountId() throws SQLException;

        //get accounts by username
    public MyArrayList<Account> getAccountsByUser(String username) throws SQLException;

    //create bank account
    public void newBankAccount(String account_type, String username) throws SQLException, InvalidAccountTypeException;

    //deposit funds into account by ID
    public boolean depositFunds(int account_id, double deposit_amount) throws SQLException;

    //check that an account belongs to a username
    public boolean accountBelongsById(int account_id, String username) throws SQLException;

    //withdraw funds from account by ID
    public boolean withdrawFunds(int account_id, double withdraw_amount) throws SQLException;

    //do we have enough funds?
    public boolean validFundsForWithdraw(int account_id, double withdraw_amount) throws SQLException;
}
