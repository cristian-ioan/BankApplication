package service;

import file.AccountReader;
import model.Account;
import model.User;

import java.util.logging.Logger;

public class DetailsBankAccount {

    private final static Logger LOG = Logger.getLogger(Logger.class.getName());

    public void showDetailsUserBankAccount(User user) {
        boolean isUserFound = false;
        for (Account account : AccountReader.getInstance().getAccountList()){
            if (user.getUserName().equals(account.getUsername())) {
                isUserFound = true;
                String bankAccountUser = String.valueOf(account.getAccountNumber());
                String balanceOfUserBankAccount = String.valueOf(account.getBalance());
                String currencyType = String.valueOf(account.getCurrency());
                LOG.info("account: " + bankAccountUser + " balance: " + balanceOfUserBankAccount
                        + " currency: " + currencyType);
            }
        }
        if (isUserFound == false) {
            LOG.info( user.getUserName() + " does not have any bank account." );
        }
    }
}
