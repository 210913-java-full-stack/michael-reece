package exceptions;

public class AccountDoesNotExistException extends Exception{

    public AccountDoesNotExistException()
    {
        super("Account does not exist with that username. Please register or try again with a different username.");
    }

    public AccountDoesNotExistException(String message)
    {
        super(message);
    }

}
