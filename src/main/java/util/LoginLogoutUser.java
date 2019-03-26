package util;

import menu.LoginLogoutMenu;
import model.User;
import service.IOService;

import java.util.Optional;
import java.util.logging.Logger;

public class LoginLogoutUser {

    private IOService ioService;
    private LoginLogoutMenu loginLogoutMenu;
    private UserPasswordValidation userPasswordValidation;
    private User user;
    private Optional<User> userNameOfAuthenticatedUser;
    private final static Logger logger = Logger.getLogger(Logger.class.getName());

    public LoginLogoutUser(){
        ioService = new IOService();
        loginLogoutMenu = new LoginLogoutMenu();
        userPasswordValidation = new UserPasswordValidation();
    }

    public void run() {
        if (user == null) {
            LoginUser();
        } else {
            LogoutUser();
        }
        run();
    }

    private void LoginUser() {
        loginLogoutMenu.showLoginConsole();
        int option = ioService.readInteger();

        switch (option) {
            case 1:
                logger.info("Enter user name: ");
                String username = ioService.readLine();
                logger.info("Enter password: ");
                String password = ioService.readLine();
                user = new User(username, password);
                userNameOfAuthenticatedUser = userPasswordValidation.verifyLogin(user);
                break;
            case 0:
                System.exit(0);
                break;
            default:
                logger.warning("Not a valid option");
                LoginUser();
                break;
        }

        if (userNameOfAuthenticatedUser.isPresent()) {
            logger.info("Welcome " + userNameOfAuthenticatedUser.get().getUsername() + " !");
            userNameOfAuthenticatedUser = null;
        } else {
            logger.warning("Wrong username/password");
            LoginUser();
        }
    }

    private void LogoutUser() {
        loginLogoutMenu.showLogoutConsole();
        int option = ioService.readInteger();

        switch (option) {
            case 1:
                logger.info(user.getUsername() + " you are successfully logged out!");
                user = null;
                break;
            case 0:
                System.exit(0);
                break;
            default:
                logger.warning("Not a valid option");
                LogoutUser();
                break;
        }
    }

}
