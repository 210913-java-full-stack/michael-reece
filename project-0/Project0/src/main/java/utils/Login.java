package utils;

import DAOs.UserDAO;
import exceptions.AccountDoesNotExistException;
import exceptions.IncorrectPasswordException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Login {
    static String user;
    static String password;
    static Scanner sc = new Scanner(System.in);
    //
    public static String LoginUser()
    {
        System.out.println("======LOG IN======\nEnter username:");
        user = sc.nextLine();
        System.out.println("Enter password:");
        password = sc.nextLine();
        try{
            Connection conn = ConnectionManager.getConnection();
            UserDAO dao = new UserDAO(conn);

            if(dao.verifyLogin(user,password) != null)
            {
                return user;
            }

        } catch (SQLException | IOException | IncorrectPasswordException | AccountDoesNotExistException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return null;
    }
}
