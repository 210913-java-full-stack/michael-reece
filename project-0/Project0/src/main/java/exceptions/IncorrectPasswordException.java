package exceptions;

public class IncorrectPasswordException extends Exception{
    public IncorrectPasswordException() {
        super("Incorrect username and password combination. Try again.");
    }

    public IncorrectPasswordException(String message) {
        super(message);
    }
}
