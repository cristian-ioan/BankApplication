package menu;

import file.ReadFromFileUsers;
import util.LoginLogoutUser;

import java.io.IOException;
import java.util.logging.Logger;

public class MainMenu {

    private LoginLogoutUser loginLogoutUser;
    private ReadFromFileUsers readFromFileUser = ReadFromFileUsers.getInstance();
    private final static Logger loggerMenu = Logger.getLogger(Logger.class.getName());

    public MainMenu() throws IOException {
        loginLogoutUser = new LoginLogoutUser();
        readFromFileUser.initializeMapUserPassword();
    }

    public void showMainMenu() {
        loggerMenu.info("Welcome to Bank Application!");
        loginLogoutUser.run();
    }

}
