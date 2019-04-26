package service;

import model.Account;

import java.util.List;
import java.util.logging.Logger;

public class DetailsBankAccount {

    private final static Logger LOG = Logger.getLogger(Logger.class.getName());

    /**
     * Displays user's accounts.
     */
    public static void showDetailsUserBankAccount(List<Account> account) {

        if (account.isEmpty()) {
            LOG.warning( "You don't have any bank account." );
        } else {
            int currentNumber = 0;
            for (Account iteratorAccount : account) {
                currentNumber++;
                String accountNumber = iteratorAccount.getAccount_Number();
                String accountType = iteratorAccount.getAccount_Type();
                String balanceOfBankAccount = String.valueOf(iteratorAccount.getBalance());
                String createTime = String.valueOf( iteratorAccount.getCreatedTime() );
                String updatedTime = String.valueOf( iteratorAccount.getUpdatedTime() );

                System.out.println( "current number: " + currentNumber + " | account number: " + accountNumber +
                        " | account type: " + accountType + " | balance: " + balanceOfBankAccount +
                        " | created time: " + createTime + " | updated time: " + updatedTime );
            }
        }

    }
}
