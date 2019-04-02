package menu;

import file.FileReader;
import service.UserLogin;

import java.io.IOException;
import java.util.logging.Logger;

public class MainMenu {

    private FileReader fileReader = FileReader.getInstance();
    private UserLogin userLogin = UserLogin.getInstance();

    private static final Logger LOG = Logger.getLogger(Logger.class.getName());

    public MainMenu() throws IOException {
        fileReader.initializeMapUserPassword();
        fileReader.initializeAccountList();
    }

    public void showMainMenu() throws IOException {
        LOG.info("Welcome to Bank Application!");
        userLogin.run();
    }

}