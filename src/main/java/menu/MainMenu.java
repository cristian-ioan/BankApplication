package menu;

import service.WrongUserNamePasswordException;
import storage.UserInitializationImpl;
import service.UserLogin;

import java.io.IOException;
import java.util.logging.Logger;

public class MainMenu {

    private UserLogin userLogin = UserLogin.getInstance();
    private UserInitializationImpl userInitializationImpl = UserInitializationImpl.getInstance();
    private final static Logger LOG = Logger.getLogger(Logger.class.getName());

    /**
     * Initializes the map of the users.
     *
     * @param userInitializationImpl used to initialize the map of users
     * @throws IOException on input error
     */
    public MainMenu() throws IOException {
        userInitializationImpl.initializeMapUserPassword();
    }

    /**
     * Starts application with user's login.
     *
     * @param userLogin instance the UserLogin class
     * @param LOG logger
     * @throws IOException on input error
     * @throws WrongUserNamePasswordException a custom exception that throws an error message for a wrong user/password
     */
    public void showMainMenu() throws IOException, WrongUserNamePasswordException {
        LOG.info("Welcome to Bank Application!");
        userLogin.goAhead();
    }

}