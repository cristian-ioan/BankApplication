package exception;

public class WrongUserNamePasswordException extends Exception {

    /**
     * Creates a custom exception for wrong username/password at login.
     */
    public WrongUserNamePasswordException(String errorMessage) {
        super(errorMessage);
    }

}
