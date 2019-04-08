package service;

public class WrongUserNamePasswordException extends Exception {

    public WrongUserNamePasswordException(String errorMessage) {
        super(errorMessage);
    }

}
