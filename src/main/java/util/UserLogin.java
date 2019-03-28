package util;

import constant.Currency;
import file.ReadFromAccountsFile;
import file.WriteIntoAccountsFile;
import menu.ConsoleMenu;
import model.Account;
import model.User;
import service.IOService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.logging.Logger;

public class UserLogin {

    private IOService ioService;
    private ConsoleMenu consoleMenu;
    private UserPasswordValidation userPasswordValidation;
    private User user;
    private Account newAccount;
    private String addLineIntoAccountsFile;
    private WriteIntoAccountsFile writeIntoAccountsFile;
    private Optional<User> userNameOfAuthenticatedUser;
    private final static Logger logger = Logger.getLogger(Logger.class.getName());


    public UserLogin(){
        ioService = new IOService();
        consoleMenu = new ConsoleMenu();
        userPasswordValidation = new UserPasswordValidation();
        writeIntoAccountsFile = new WriteIntoAccountsFile();
    }

    public void run() throws IOException {
        if (user == null) {
            loginUser();
        } else {
            showUserBankAccountConsole();
        }
        this.run();
    }

    private void loginUser() throws IOException {
        consoleMenu.showLoginConsole();
        int option = ioService.readInteger();

        switch (option) {
            case 1:
                logger.info("Enter user name: ");
                String userName = ioService.readLine();
                logger.info("Enter password: ");
                String userPassword = ioService.readLine();
                user = new User(userName, userPassword);
                userNameOfAuthenticatedUser = userPasswordValidation.verifyLogin(user);
                break;
            case 2:
                System.exit(0);
                break;
            default:
                logger.warning("Not a valid option");
                loginUser();
                break;
        }

        if (userNameOfAuthenticatedUser.isPresent()) {
            logger.info("Welcome " + userNameOfAuthenticatedUser.get().getUserName() + " !");
            showUserBankAccountConsole();
            userNameOfAuthenticatedUser = null;
        } else {
            logger.warning("Wrong username/password");
            loginUser();
        }
    }

    public void showUserBankAccountConsole() throws IOException {
        consoleMenu.showAccountConsole();
        int option = ioService.readInteger();

        switch (option) {
            case 1:
                createOrDisplayUserBankAccount();
                break;
            case 2:
                logger.info(user.getUserName() + " you are successfully logged out!");
                user = null;
                break;
            default:
                logger.warning("Not a valid option");
                showUserBankAccountConsole();
                break;
        }
    }

    private void createOrDisplayUserBankAccount() throws IOException {
        consoleMenu.showUserBankAccountConsole(user);
        int option = ioService.readInteger();

        switch (option) {
            case 1:
                logger.info("Create a new account");
                createUserBankAccount();
                showUserBankAccountConsole();
                break;
            case 2:
                showDetailsUserBankAccount();
                showUserBankAccountConsole();
                break;
            default:
                logger.warning("Not a valid option");
                createOrDisplayUserBankAccount();
                break;
        }
    }

    private void createUserBankAccount() throws IOException {

        logger.info( "Enter account number: " );
        String userBankAccount = ioService.readLine();
        while (userBankAccount.length() != 24 || !userBankAccount.substring(0, 2).equals("RO")) {
            logger.warning( "The length of account number must have 24 characters or " +
                    "the account number should start with " + "RO." + " Please try again." );
            logger.info( "Enter account number: " );
            userBankAccount = ioService.readLine();
        }

        logger.info("Enter balance: ");
        BigDecimal balanceOfUserBankAccount = new BigDecimal(ioService.readInteger());

        logger.info("Enter currency [RON/EURO]: ");
        String currencyTypeOfUserBankAccount = ioService.readLine();
        while (!currencyTypeOfUserBankAccount.substring(0, 3).equals(String.valueOf(Currency.RON)) &&
                !currencyTypeOfUserBankAccount.substring(0, 4).equals(String.valueOf(Currency.EURO ))){
            logger.warning("The currency must be 'RON' or 'EURO'!");
            logger.info("Enter currency [RON/EURO]: ");
            currencyTypeOfUserBankAccount = ioService.readLine();
        }

        newAccount = new Account(user.getUserName(),
                userBankAccount, balanceOfUserBankAccount, Currency.valueOf(currencyTypeOfUserBankAccount));
        ReadFromAccountsFile.getInstance().getAccountList().add((newAccount));
        addLineIntoAccountsFile = "\n" + user.getUserName() + " " + userBankAccount + " "
                + balanceOfUserBankAccount.toString() + " " + currencyTypeOfUserBankAccount;
        writeIntoAccountsFile.writeStringToFile(addLineIntoAccountsFile);
        logger.info("The bank account for " + user.getUserName() + " was successfully created!");
        addLineIntoAccountsFile = null;
        newAccount = null;
    }

    private void showDetailsUserBankAccount() {
        boolean isUserFound = false;
        for (Account account : ReadFromAccountsFile.getInstance().getAccountList()){
            if (user.getUserName().equals(account.getUsername())) {
                isUserFound = true;
                String bankAccountUser = String.valueOf(account.getAccountNumber());
                String balanceOfUserBankAccount = String.valueOf(account.getBalance());
                String currencyType = String.valueOf(account.getCurrency());
                logger.info("account: " + bankAccountUser + " balance: " + balanceOfUserBankAccount
                        + " currency: " + currencyType);
            }
        }
        if (isUserFound == false) {
            logger.info( user.getUserName() + " does not have any bank account." );
        }
    }

}