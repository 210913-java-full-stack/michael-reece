package DAOs;

import datastructures.MyArrayList;
import exceptions.AccountDoesNotExistException;
import exceptions.IncorrectPasswordException;
import exceptions.InvalidAccountTypeException;
import exceptions.UserAlreadyExistsException;
import models.Account;
import models.User;

import java.sql.SQLException;

public interface userCrud<T> {
    //will need a connection

    //get current BankAccount ID
    public int getAccountId() throws SQLException;

    //create user account
        //save new user info to database
    public void newAccount(User newUser) throws SQLException, UserAlreadyExistsException;

        //read user info from database to login
    public T verifyLogin(String username, String password) throws SQLException, IncorrectPasswordException, AccountDoesNotExistException;
}
