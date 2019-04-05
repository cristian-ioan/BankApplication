package menu;

import model.User;
import service.*;

import java.io.IOException;
import java.util.logging.Logger;

public class ConsoleAccount {

    private static ConsoleAccount instance;
    private ConsoleAccount(){}

    public static synchronized ConsoleAccount getInstance(){
        if (instance == null){
            instance = new ConsoleAccount();
        }
        return instance;
    }

    private AccountCreate accountCreate = new AccountCreate();
    private UserAccountsTransfer userAccountsTransfer = new UserAccountsTransfer();
    private static final Logger LOG = Logger.getLogger(Logger.class.getName());

    public void showUserBankAccountConsole(User user) throws IOException {
        ConsoleMenu.getInstance().showAccountConsole();
        int option = IOService.getInstance().readInteger();

        switch (option) {
            case 1:
                createOrDisplayUserBankAccount(user);
                break;
            case 2:
                LOG.info(user.getUserName() + " you are successfully logged out!");
                UserLogin.getInstance().loginUser();
                break;
            default:
                LOG.warning("Not a valid option");
                showUserBankAccountConsole(user);
                break;
        }
    }

    public void createOrDisplayUserBankAccount(User user) throws IOException {
        ConsoleMenu.getInstance().showUserBankAccountConsole(user);
        int option = IOService.getInstance().readInteger();

        switch (option) {
            case 1:
                LOG.info("Create a new account");
                accountCreate.createUserBankAccount(user);
                showUserBankAccountConsole(user);
                break;
            case 2:
                DetailsBankAccount.showDetailsUserBankAccount(user);
                showUserBankAccountConsole(user);
                break;
            case 3:
                userAccountsTransfer.transferMoneyBetweenUserAccounts(user);
                break;
            default:
                LOG.warning("Not a valid option");
                createOrDisplayUserBankAccount(user);
                break;
        }
    }

}