package service;

import dao.UserDao;
import model.User;
import view.*;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.logging.Logger;

public class UserService {

    private UserDao userDao = new UserDao();
    private User user;
    private Optional<User> resultOfUserVerification;
    private ConsoleAccount consoleAccount = ConsoleAccount.getInstance();
    private ConsoleMenu consoleMenu = ConsoleMenu.getInstance();
    private IOService ioService = IOService.getInstance();

    private final static Logger LOG = Logger.getLogger(Logger.class.getName());

    public void goAhead() throws IOException, WrongUserNamePasswordException {
        if (user == null) {
            handleUser();
        } else {
            consoleAccount.showUserBankAccountConsole(user);
        }
        goAhead();
    }

    /**
     * Verifies if the username and password entered by you is correct or you can logout.
     *
     * @param resultOfUserVerification contains the user that was searched
     * @param ioService instance of IOService class
     * @param consoleMenu instance of ConsoleMenu class
     * @param option the value read from console
     * @param badOption suppose that a char was typed instead of a digit
     * @param LOG logger
     * @throws IOException on input error
     * @throws WrongUserNamePasswordException a custom exception that throws an error message for a wrong user/password
     */
    public void handleUser() throws IOException, WrongUserNamePasswordException {
        user = null;
        boolean badOption = false;
        long userId = 0;
        LOG.info( "Welcome to Bank Application!" );

        do {
            try {
                consoleMenu.showLoginConsole();
                int option = ioService.readInteger();
                switch (option) {
                    case 1:
                        LOG.info( "Enter user name: " );
                        String userName = ioService.readLine();
                        LOG.info( "Enter password: " );
                        String userPassword = ioService.readLine();
                        user = new User(1000000000L,userName, userPassword);
                        resultOfUserVerification = userDao.verifyUserPassword(user);
                        badOption = true;
                        break;
                    case 2:
                        System.exit( 0 );
                        break;
                    default:
                        LOG.warning( "Not a valid option." );
                        break;
                }
            } catch (InputMismatchException e) {
                LOG.warning( "Incorrect entry. Please input only integer." );
            }
        } while (badOption == false);

        displaySearchResultForUser();

    }

    /**
     * Displays the search result for an user. If it is true then we will continue. Otherwise, it displays that
     * the user and password that was entered is not correct.
     *
     * @param resultOfUserVerification contains the user that was searched
     * @param LOG logger
     * @throws IOException on input error
     * @throws WrongUserNamePasswordException a custom exception that throws an error message for a wrong user/password
     */
    public void displaySearchResultForUser() throws IOException, WrongUserNamePasswordException {

        try {
            if (resultOfUserVerification.isPresent()) {
                LOG.info("Welcome " + resultOfUserVerification.get().getUserName() + " !");
                consoleAccount.showUserBankAccountConsole(user);
                resultOfUserVerification = null;
                user = null;
            } else {
                throw new WrongUserNamePasswordException( "Wrong username/password" );
            }
        } catch (WrongUserNamePasswordException error){
            LOG.warning( "Exception occurred: " + error );
            handleUser();
        }
    }

}
