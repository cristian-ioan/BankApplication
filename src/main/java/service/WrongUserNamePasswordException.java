package service;

/**
 * Creates a custom exception for wrong username/password at login.
 *
 * @author Cristian-Lucian IOAN
 * @version 1.0
 * @since   2019-04-08
 */
public class WrongUserNamePasswordException extends Exception {

    public WrongUserNamePasswordException(String errorMessage) {
        super(errorMessage);
    }

}
