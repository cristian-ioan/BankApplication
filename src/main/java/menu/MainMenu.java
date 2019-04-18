package menu;

import service.WrongUserNamePasswordException;
import storage.UserInitializationImpl;
import service.UserLogin;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * This class initializes the map of the users.
 *
 * @param userLogin instantiates the UserLogin class
 * @param userInitializationImpl used to initialize the map of users
 * @param LOG logger
 * @throws IOException on input error
 * @throws WrongUserNamePasswordException a custom exception that throws an error message for a wrong user/password
 *
 *
 * @author  Cristian-Lucian IOAN
 * @version 1.0
 * @since   2019-03-21
 */

public class MainMenu {

    private UserLogin userLogin = UserLogin.getInstance();
    private UserInitializationImpl userInitializationImpl = UserInitializationImpl.getInstance();

    private final static Logger LOG = Logger.getLogger(Logger.class.getName());

    public MainMenu() throws IOException {
        userInitializationImpl.initializeMapUserPassword();
    }

    public void showMainMenu() throws IOException, WrongUserNamePasswordException {
        LOG.info("Welcome to Bank Application!");
        userLogin.goAhead();
    }

}