package menu;

import storage.UserAccountInitialization;
import service.UserLogin;

import java.io.IOException;
import java.util.logging.Logger;

public class MainMenu {

    private UserLogin userLogin = UserLogin.getInstance();
    private UserAccountInitialization userAccountInitialization = UserAccountInitialization.getInstance();

    private static final Logger LOG = Logger.getLogger(Logger.class.getName());

    public MainMenu() throws IOException {
        userAccountInitialization.initializeMapUserPassword();
        userAccountInitialization.initializeAccountList();
    }

    public void showMainMenu() throws IOException {
        LOG.info("Welcome to Bank Application!");
        userLogin.run();
    }

}