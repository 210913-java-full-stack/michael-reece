package exceptions;

public class UserAlreadyExistsException extends Exception{
    public UserAlreadyExistsException(String message)
    {
        super(message);
    }

    public UserAlreadyExistsException()
    {
        super("Username already exists. Please select another or log in if that is you.");
    }
}
