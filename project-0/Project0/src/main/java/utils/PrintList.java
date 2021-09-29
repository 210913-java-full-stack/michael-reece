package utils;

import models.Account;

public class PrintList {

    public static void printMyList(Account account)
    {
        String printing = account.getAccount_id() + " | " + account.getAccount_type() + " | ";
        System.out.print(printing);
        System.out.printf("$%.2f.%n", account.getAccount_balance());
    }

}
