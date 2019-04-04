package service;

import model.Account;
import model.User;

import java.math.BigDecimal;
import java.util.logging.Logger;

public class UserAccountsTransfer {

    private final static Logger LOG = Logger.getLogger(Logger.class.getName());

    public void transferMoneyBetweenUserAccounts(User user){
        int numberUserAccounts = AccountUser.getUserAccountsList(user).size();
        boolean isConditionForMoneyTransfer = false;
        if (numberUserAccounts == 0){
            LOG.info( user.getUserName() + " does not have any bank account." );
        } else {
            if (numberUserAccounts == 1){
                LOG.info( "You have one bank account. Please create another bank account of the same type.");
            } else {
                isConditionForMoneyTransfer = true;
            }
        }
        if (isConditionForMoneyTransfer == true) {
            LOG.info( "All user bank accounts are:" );
            DetailsBankAccount.showDetailsUserBankAccount(user);

            LOG.info( "Type the number of account (from):" );
            int optionFrom = IOService.getInstance().readInteger();

            BigDecimal balanceFirstAccount = AccountUser.getUserAccountsList(user).get(optionFrom-1).getBalance();

            int indexOfFirstAccount = AccountUser.getUserAccountsList(user).get(optionFrom-1).getId();


            BigDecimal validateBalanceOfPayment = validateBalanceAccount(balanceFirstAccount);

            LOG.info( "Type the number of account (to transfer money):" );
            int optionTo = IOService.getInstance().readInteger();

            BigDecimal balanceSecondAccount = AccountUser.getUserAccountsList(user).get(optionTo-1).getBalance();
            int indexOfSecondAccount = AccountUser.getUserAccountsList(user).get(optionTo-1).getId();

            BigDecimal toAccount = validateBalanceOfPayment.add(balanceSecondAccount);
            BigDecimal fromAccount= balanceFirstAccount.subtract(validateBalanceOfPayment);

            for (Account account : user.getAccounts()) {
                if (account.getId() == indexOfFirstAccount){
                    account.setBalance(fromAccount);
                }
                if (account.getId() == indexOfSecondAccount){
                    account.setBalance(toAccount);
                }
            }
        }
    }

    public BigDecimal validateBalanceAccount(BigDecimal balance){
        boolean isBalanceNotOk = false;
        LOG.info( "Type the balance: " );
        BigDecimal balanceFrom = BigDecimal.valueOf(IOService.getInstance().readInteger());
        while (isBalanceNotOk == false){
            if (balance.compareTo( balanceFrom ) < 0){
                LOG.info( "Type a new balance: " );
                balanceFrom = BigDecimal.valueOf(IOService.getInstance().readInteger());
            } else {
                isBalanceNotOk = true;
            }
        }
        return balanceFrom;
    }

}