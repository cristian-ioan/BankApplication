package menu;

import model.User;
import service.*;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.logging.Logger;

/**
 * Creates a singleton for the console account.
 *
 * @param instance of type ConsoleAccount
 * @param accountCreate instantiates the AccountCreate class
 * @param userAccountsTransfer instantiates the UserAccountsTransfer class
 * @param LOG logger
 *
 * @author Cristian-Lucian IOAN
 * @version 1.0
 * @since   2019-03-21
 */
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
    private final static Logger LOG = Logger.getLogger(Logger.class.getName());

    /**
     * Displays the bank account console for an logged user.
     *
     * @param option the value read from console
     * @param badOption suppose that a char was typed instead of a digit
     */
    public void showUserBankAccountConsole(User user) throws IOException, WrongUserNamePasswordException {

        boolean badOption = false;

        do {
            try {
                ConsoleMenu.getInstance().showAccountConsole();
                int option = IOService.getInstance().readInteger();
                switch (option) {
                    case 1:
                        createOrDisplayOrTransferUserBankAccount(user);
                        badOption = true;
                        break;
                    case 2:
                        LOG.info(user.getUserName() + " you are successfully logged out!");
                        UserLogin.getInstance().clearAccountsList();
                        UserLogin.getInstance().loginUser();
                        badOption = true;
                        break;
                    default:
                        LOG.warning("Not a valid option.");
                        break;
                }
            } catch (InputMismatchException e){
                LOG.warning( "Incorrect entry. Please input only integer." );
            }
        } while (badOption == false);

    }

    /**
     * Displays the actions then logged user can do for his bank accounts.
     *
     * @param option the value read from console
     * @param badOption suppose that a char was typed instead of a digit
     */
    public void createOrDisplayOrTransferUserBankAccount(User user) throws IOException,
            WrongUserNamePasswordException {

        boolean badOption = false;

        do{
            try {
                ConsoleMenu.getInstance().showUserBankAccountConsole(user);
                int option = IOService.getInstance().readInteger();

                switch (option) {
                    case 1:
                        LOG.info("Create a new account");
                        accountCreate.createUserBankAccount(user);
                        showUserBankAccountConsole(user);
                        badOption = true;
                        break;
                    case 2:
                        DetailsBankAccount.showDetailsUserBankAccount(user);
                        showUserBankAccountConsole(user);
                        badOption = true;
                        break;
                    case 3:
                        userAccountsTransfer.validateNumberAndCurrencyTypeOfUserBankAccounts(user);
                        badOption = true;
                        break;
                    default:
                        LOG.warning("Not a valid option.");
                        break;
                }
            } catch (InputMismatchException e){
                LOG.warning( "Incorrect entry. Please input only integer." );
            }
        } while (badOption == false);

    }

}