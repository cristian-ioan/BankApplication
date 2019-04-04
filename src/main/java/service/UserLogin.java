package service;

import menu.ConsoleMenu;
import menu.ConsoleAccount;
import model.Account;
import model.User;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

public class UserLogin {

    private User user;
    private Optional<User> userNameOfAuthenticatedUser;
    private IOService ioService = IOService.getInstance();
    private ConsoleMenu consoleMenu = ConsoleMenu.getInstance();
    private ConsoleAccount consoleAccount = ConsoleAccount.getInstance();
    private static final Logger LOG = Logger.getLogger(Logger.class.getName());
    private UserValidate userValidate = new UserValidate();

    private static UserLogin instance;
    private UserLogin(){}

    public static synchronized UserLogin getInstance(){
        if (instance == null){
            instance = new UserLogin();
        }
        return instance;
    }

    public void run() throws IOException {
        if (user == null) {
            loginUser();
        } else {
            consoleAccount.showUserBankAccountConsole(user);
        }
        run();
    }

    public void loginUser() throws IOException {
        user = null;
        consoleMenu.showLoginConsole();
        int option = ioService.readInteger();


        switch (option) {
            case 1:
                LOG.info("Enter user name: ");
                String userName = ioService.readLine();
                LOG.info("Enter password: ");
                String userPassword = ioService.readLine();
                user = new User(userName, userPassword);
                userNameOfAuthenticatedUser = userValidate.searchUser(user);
                break;
            case 2:
                System.exit(0);
                break;
            default:
                LOG.warning("Not a valid option");
                loginUser();
                break;
        }

        displaySearchResultForUser();

    }

    public void displaySearchResultForUser() throws IOException {

        if (userNameOfAuthenticatedUser.isPresent()) {
            LOG.info("Welcome " + userNameOfAuthenticatedUser.get().getUserName() + " !");
            buildAccountList(user);
            consoleAccount.showUserBankAccountConsole(user);
            userNameOfAuthenticatedUser = null;
        } else {
            LOG.warning("Wrong username/password");
            loginUser();
        }
    }

    private void buildAccountList(User user) {
        for (Account account : AccountUser.getUserAccountsList(user)) {
            user.getAccounts().add(account);
        }
    }




}