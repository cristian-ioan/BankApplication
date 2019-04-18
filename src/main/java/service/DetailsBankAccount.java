package service;

import model.Account;
import model.User;

import java.util.logging.Logger;

/**
 * Displays user's accounts.
 *
 * @param LOG logger
 *
 * @author Cristian-Lucian IOAN
 * @version 1.0
 * @since   2019-03-26
 */
public class DetailsBankAccount {

    private final static Logger LOG = Logger.getLogger(Logger.class.getName());

    /**
     *
     */
    public static void showDetailsUserBankAccount(User user) {

        if (user.getAccounts().isEmpty()) {
            LOG.warning( user.getUserName() + " does not have any bank account." );
        } else {
            int currentNumber = 0;
            for (Account account : user.getAccounts()) {
                currentNumber++;
                String bankAccountUser = String.valueOf( account.getAccountNumber());
                String balanceOfUserBankAccount = String.valueOf( account.getBalance() );
                String currencyType = String.valueOf( account.getCurrency() );
                LOG.info( "current number: " + currentNumber + " account: " +
                        bankAccountUser + " balance: " + balanceOfUserBankAccount +
                        " currency: " + currencyType );
            }
        }

    }
}