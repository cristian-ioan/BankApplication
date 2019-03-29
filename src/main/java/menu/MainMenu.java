package menu;

import file.AccountReader;
import file.UserReader;
import service.UserLogin;

import java.io.IOException;
import java.util.logging.Logger;

public class MainMenu {

    private UserReader userReader = UserReader.getInstance();
    private AccountReader accountReader = AccountReader.getInstance();
    private UserLogin userLogin = UserLogin.getInstance();

    private final static Logger LOG = Logger.getLogger(Logger.class.getName());

    public MainMenu() throws IOException {
        userReader.initializeMapUserPassword();
        accountReader.initializeAccountList();
    }

    public void showMainMenu() throws IOException {
        LOG.info("Welcome to Bank Application!");
        userLogin.run();
    }

}