package menu;

import storage.UserInitializationImpl;
import service.UserLogin;

import java.io.IOException;
import java.util.logging.Logger;

public class MainMenu {

    private UserLogin userLogin = UserLogin.getInstance();
    private UserInitializationImpl userInitializationImpl = UserInitializationImpl.getInstance();

    private static final Logger LOG = Logger.getLogger(Logger.class.getName());

    public MainMenu() throws IOException {
        userInitializationImpl.initializeMapUserPassword();
    }

    public void showMainMenu() throws IOException {
        LOG.info("Welcome to Bank Application!");
        userLogin.goAhead();
    }

}