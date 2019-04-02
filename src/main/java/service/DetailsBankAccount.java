package service;

import menu.ConsoleAccount;
import model.Account;
import model.User;

import java.util.logging.Logger;

public class DetailsBankAccount {

    private final static Logger LOG = Logger.getLogger(Logger.class.getName());

    public void showDetailsUserBankAccount(User user) {

        if (ConsoleAccount.getInstance().getUserAccountsList( user ).isEmpty()) {
            LOG.info( user.getUserName() + " does not have any bank account." );
        } else {
            for (Account account : ConsoleAccount.getInstance().getUserAccountsList( user )) {
                String bankAccountUser = String.valueOf( account.getAccountNumber() );
                String balanceOfUserBankAccount = String.valueOf( account.getBalance() );
                String currencyType = String.valueOf( account.getCurrency() );
                LOG.info( "account: " + bankAccountUser + " balance: " +
                        balanceOfUserBankAccount + " currency: " + currencyType );
            }
        }

    }
}