package exceptions;

public class InvalidAccountTypeException extends Exception{
    public InvalidAccountTypeException() {
        super("Please create either a 'Savings' or 'Checking' account.");
    }

    public InvalidAccountTypeException(String message) {
        super(message);
    }
}
