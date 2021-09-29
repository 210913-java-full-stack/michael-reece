package DAOs;

import datastructures.MyArrayList;
import exceptions.AccountDoesNotExistException;
import exceptions.IncorrectPasswordException;
import exceptions.InvalidAccountTypeException;
import exceptions.UserAlreadyExistsException;
import models.Account;
import models.User;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankDAO implements bankCrud {

    private Connection conn;
    public int newestAccountId;

    public BankDAO(Connection conn)
    {
        this.conn = conn;
    }


    @Override
    public int getAccountId() throws SQLException {
        //We are grabbing the newest account ID that was used by the auto increment in the table.
        String sql = "SELECT * FROM user_accounts";
        PreparedStatement findAccNumStmt = conn.prepareStatement(sql);
        ResultSet rs = findAccNumStmt.executeQuery();

        while(rs.next())
        {
            newestAccountId = rs.getInt("account_id");
        }

        return newestAccountId;
    }

    @Override
    public MyArrayList<Account> getAccountsByUser(String username) throws SQLException {
        //We are getting all of the bank accounts belonging to whoever is logged in, passed in by username.
        String getAccountsSql = "SELECT * FROM accounts a JOIN user_accounts ua ON a.account_id = ua.account_id WHERE username = ?";
        PreparedStatement getAccountsStmt = conn.prepareStatement(getAccountsSql);
        getAccountsStmt.setString(1,username);
        ResultSet rs = getAccountsStmt.executeQuery();

        MyArrayList<Account> accountList = new MyArrayList<>();

        while(rs.next())
        {
            Account newAccount = new Account(rs.getInt("account_id"),rs.getString("account_type"),rs.getDouble("balance"));
            accountList.add(newAccount);
        }

        return accountList;
    }
    @Override
    public void newBankAccount(String account_type, String username) throws SQLException, InvalidAccountTypeException {
        getAccountId();

        String insertStatement = "INSERT INTO user_accounts (username, account_id) VALUES (?,?)";
        PreparedStatement preparedInsertStmt = conn.prepareStatement(insertStatement);
        preparedInsertStmt.setString(1,username);
        newestAccountId++;
        preparedInsertStmt.setInt(2,newestAccountId);
        preparedInsertStmt.executeUpdate();

        String acctInsertStmt = "INSERT INTO accounts (account_id,account_type, balance) VALUES (?,?,?)";
        PreparedStatement preparedAccountStmt = conn.prepareStatement(acctInsertStmt);
        preparedAccountStmt.setInt(1,newestAccountId);
        preparedAccountStmt.setString(2,account_type);
        preparedAccountStmt.setInt(3,0);
        preparedAccountStmt.executeUpdate();

    }

    @Override
    public boolean depositFunds(int account_id, double deposit_amount) throws SQLException {
        //Now deposit whatever amount into that account
        String depositSql = "UPDATE accounts SET balance = (balance + ?) WHERE account_id = ?";
        PreparedStatement depositStmt = conn.prepareStatement(depositSql);
        System.out.println(deposit_amount);
        depositStmt.setDouble(1,deposit_amount);
        depositStmt.setInt(2,account_id);
        depositStmt.executeUpdate();

        return true;
    }

    @Override
    public boolean accountBelongsById(int account_id, String username) throws SQLException {
        //double check that the account belongs to whoever is logged in for both deposit and withdrawal
        String accountCheck = "SELECT * FROM user_accounts WHERE (username = ?) AND (account_id = ?)";
        PreparedStatement preparedCheckStmt = conn.prepareStatement(accountCheck);
        preparedCheckStmt.setString(1,username);
        preparedCheckStmt.setInt(2,account_id);
        ResultSet rs = preparedCheckStmt.executeQuery();

        if(!rs.next())
        {
            System.out.println("Inputted account number not associated with your account. Please try again.");
            return false;
        }
        else
        {
            return true;
        }
    }

    @Override
    public boolean withdrawFunds(int account_id, double withdraw_amount) throws SQLException {
        //make sure we have enough funds
        if(!validFundsForWithdraw(account_id,withdraw_amount))
        {
            System.out.println("Not enough funds. Withdraw unsuccessful.");
            return false;
        }

        String withdrawSQL = "UPDATE accounts SET balance = (balance - ?) WHERE account_id = ?";
        PreparedStatement withdrawStmt = conn.prepareStatement(withdrawSQL);
        withdrawStmt.setDouble(1,withdraw_amount);
        withdrawStmt.setInt(2,account_id);
        withdrawStmt.executeUpdate();
        return true;
    }

    @Override
    public boolean validFundsForWithdraw(int account_id, double withdraw_amount) throws SQLException {
        String validateSQL = "SELECT balance FROM accounts WHERE account_id = ?";
        PreparedStatement validateStmt = conn.prepareStatement(validateSQL);
        validateStmt.setInt(1,account_id);
        ResultSet rs = validateStmt.executeQuery();
        while(rs.next())
        {
            double currentBalance = rs.getDouble("balance");
            if(currentBalance >= withdraw_amount)
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        return false;
    }


}
