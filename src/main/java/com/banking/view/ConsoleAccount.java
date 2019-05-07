package com.banking.view;

import com.banking.exception.BalanceException;
import com.banking.exception.DetailsAccountException;
import com.banking.exception.WrongUserNamePasswordException;
import com.banking.model.User;
import com.banking.service.*;
import com.banking.util.DetailsBankAccount;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.logging.Logger;

public class ConsoleAccount {

    private static ConsoleAccount instance;
    private AccountsTransfer accountsTransfer = new AccountsTransfer();
    private AccountService accountService = new AccountService();
    private ConsoleAccount(){}

    public static synchronized ConsoleAccount getInstance(){
        if (instance == null){
            instance = new ConsoleAccount();
        }
        return instance;
    }

    private final static Logger LOG = Logger.getLogger(Logger.class.getName());

    /**
     * Displays the bank account's console for an logged user.
     */
    public void showUserBankAccountConsole(User user) throws IOException, WrongUserNamePasswordException,
            BalanceException, DetailsAccountException {

        boolean isBadOption = false;

        do {
            try {
                ConsoleMenu.getInstance().showAccountConsole();
                int option = IOService.getInstance().readInteger();
                switch (option) {
                    case 1:
                        createOrDisplayOrTransferUserBankAccount(user);
                        isBadOption = true;
                        break;
                    case 2:
                        LOG.info(user.getUserName() + " you are successfully logged out!");
                        isBadOption = true;
                        break;
                    default:
                        LOG.warning("Not a valid option.");
                        break;
                }
            } catch (InputMismatchException e){
                LOG.warning( "Incorrect entry. Please input only integer." );
            }
        } while (!isBadOption);

    }

    /**
     * Displays the actions than logged user can do for his bank accounts.
     */
    public void createOrDisplayOrTransferUserBankAccount(User user) throws IOException,
            WrongUserNamePasswordException, BalanceException, DetailsAccountException {

        boolean isBadOption = false;
        int option = 0;

        do{
            try {
                ConsoleMenu.getInstance().showUserBankAccountConsole();
                option = IOService.getInstance().readInteger();

                switch (option) {
                    case 1:
                        LOG.info("Create a new account");
                        accountService.createUserBankAccount(user);
                        showUserBankAccountConsole(user);
                        isBadOption = true;
                        break;
                    case 2:
                        DetailsBankAccount.showDetailsUserBankAccount(accountService.findAllAccountsById(user.getId()));
                        showUserBankAccountConsole(user);
                        isBadOption = true;
                        break;
                    case 3:
                        accountsTransfer.verifyPayment(accountService.findAllAccountsById(user.getId()));
                        showUserBankAccountConsole(user);
                        isBadOption = true;
                        break;
                    default:
                        LOG.warning("Not a valid option.");
                        break;
                }
            } catch (InputMismatchException e){
                LOG.warning( "Incorrect entry. Please input only integer." );
            }
        } while (!isBadOption);

    }

}