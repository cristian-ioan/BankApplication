package service;

import menu.ConsoleMenu;
import menu.ConsoleAccount;
import model.Account;
import model.User;
import storage.AccountInitilizationImpl;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Checks if a user exists in the users file.
 *
 * @param user instantiates the User class
 * @param userNameOfAuthenticatedUser contains the user that was searched in the map of users
 * @param accountInitilizationImpl instantiates the AccountInitilizationImpl class
 * @param ioService instantiates the IOService class
 * @param consoleMenu instantiates the ConsoleMenu class
 * @param userValidate instantiates the UserValidate class
 * @param LOG logger
 * @throws IOException on input error
 * @throws WrongUserNamePasswordException a custom exception that throws an error message for a wrong user/password
 *
 * @author  Cristian-Lucian IOAN
 * @version 1.0
 * @since   2019-03-21
 */


public class UserLogin {

    private User user;
    private Optional<User> userNameOfAuthenticatedUser;
    private AccountInitilizationImpl accountInitilizationImpl = new AccountInitilizationImpl();
    private IOService ioService = IOService.getInstance();
    private ConsoleMenu consoleMenu = ConsoleMenu.getInstance();
    private ConsoleAccount consoleAccount = ConsoleAccount.getInstance();
    private UserValidate userValidate = new UserValidate();
    private final static Logger LOG = Logger.getLogger(Logger.class.getName());

    private static UserLogin instance;
    private UserLogin(){}

    public static synchronized UserLogin getInstance(){
        if (instance == null){
            instance = new UserLogin();
        }
        return instance;
    }

    public void goAhead() throws IOException, WrongUserNamePasswordException {
        if (user == null) {
            loginUser();
        } else {
            consoleAccount.showUserBankAccountConsole(user);
        }
        goAhead();
    }

    /**
     * Verifies if the username and password entered by you is correct.
     *
     * @param option the value read from console
     */
    public void loginUser() throws IOException, WrongUserNamePasswordException {
        user = null;
        consoleMenu.showLoginConsole();
        int option = ioService.readInteger();


        switch (option) {
            case 1:
                LOG.info("Enter user name: ");
                String userName = ioService.readLine();
                LOG.info("Enter password: ");
                String userPassword = ioService.readLine();
                user = new User(userName, userPassword);
                userNameOfAuthenticatedUser = userValidate.searchUser(user);
                break;
            case 2:
                System.exit(0);
                break;
            default:
                LOG.warning("Not a valid option");
                loginUser();
                break;
        }

        displaySearchResultForUser();

    }

    /**
     * Displays the search result for an user. If it is true then we will continue. Otherwise, it displays that
     * the user and password that was entered is not correct.
     */
    public void displaySearchResultForUser() throws IOException, WrongUserNamePasswordException {

        try {
            if (userNameOfAuthenticatedUser.isPresent()) {
                LOG.info("Welcome " + userNameOfAuthenticatedUser.get().getUserName() + " !");
                buildAccountList(user);
                consoleAccount.showUserBankAccountConsole(user);
                userNameOfAuthenticatedUser = null;
            } else {
                throw new WrongUserNamePasswordException( "Wrong username/password" );
            }
        } catch (WrongUserNamePasswordException error){
            LOG.warning( "Exception occurred: " + error );
            loginUser();
        }
    }

    /**
     * Builds the list of accounts for the logged user.
     */
    private void buildAccountList(User user) throws IOException {
        for(Account account : accountInitilizationImpl.initializeAccountList(user)){
            user.getAccounts().add(account);
        }
    }

    /**
     * Clears the list of accounts for the logged user.
     */
    public void clearAccountsList(){
        accountInitilizationImpl.getAccountList().clear();
        user.getAccounts().clear();
    }

}