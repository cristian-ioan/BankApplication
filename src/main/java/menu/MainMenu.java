package menu;

import model.UserAccountCollections;
import service.UserLogin;

import java.io.IOException;
import java.util.logging.Logger;

public class MainMenu {

    private UserLogin userLogin = UserLogin.getInstance();
    private UserAccountCollections userAccountCollections = UserAccountCollections.getInstance();

    private static final Logger LOG = Logger.getLogger(Logger.class.getName());

    public MainMenu() throws IOException {
        userAccountCollections.initializeMapUserPassword();
        userAccountCollections.initializeAccountList();
    }

    public void showMainMenu() throws IOException {
        LOG.info("Welcome to Bank Application!");
        userLogin.run();
    }

}