package menu;

import file.ReadFromAccountsFile;
import file.ReadFromUsersFile;
import util.UserLogin;

import java.io.IOException;
import java.util.logging.Logger;

public class MainMenu {

    private UserLogin userLogin;
    private ReadFromUsersFile readFromUsersFile = ReadFromUsersFile.getInstance();
    private ReadFromAccountsFile readFromAccountsFile = ReadFromAccountsFile.getInstance();
    private final static Logger loggerMenu = Logger.getLogger(Logger.class.getName());

    public MainMenu() throws IOException {
        userLogin = new UserLogin();
        readFromUsersFile.initializeMapUserPassword();
        readFromAccountsFile.initializeAccountList();
    }

    public void showMainMenu() throws IOException {
        loggerMenu.info("Welcome to Bank Application!");
        userLogin.run();
    }

}
