package service;

import model.Account;
import model.User;
import utils.Currency;

import java.math.BigDecimal;
import java.util.logging.Logger;

public class UserAccountsTransfer {

    private final static Logger LOG = Logger.getLogger(Logger.class.getName());

    public void transferMoneyBetweenUserAccounts(User user){

        int numberUserAccounts = user.getAccounts().size();
        boolean isConditionForPayment = false;

        if (numberUserAccounts == 0){
            LOG.info( user.getUserName() + " does not have any bank account." );
        } else {
            if (numberUserAccounts == 1) {
                LOG.info( "You have one bank account. Please create another bank account of the same type: "
                        + user.getAccounts().get( 0 ).getCurrency() );
            } else {
                if (numberUserAccounts == 2) {
                    Currency currencyTypeForFirstAccount = user.getAccounts().get(0).getCurrency();
                    Currency currencyTypeForSecondAccount = user.getAccounts().get(1).getCurrency();
                    if (!currencyTypeForFirstAccount.equals(currencyTypeForSecondAccount)) {
                        LOG.info("You do not have accounts of the same currency type. Create another one!");
                    }
                } else {
                    isConditionForPayment = true;
                }
            }
        }

        if (isConditionForPayment == true) {
            int optionFrom;
            int indexOfFirstAccount;

            LOG.info( "All user bank accounts are:" );
            DetailsBankAccount.showDetailsUserBankAccount( user );
            LOG.info( "Type the number of account (from) - it must be between 1 and " + numberUserAccounts + ": " );
            optionFrom = IOService.getInstance().readInteger();

            indexOfFirstAccount = user.getAccounts().get( optionFrom - 1 ).getId();
            Currency currencyFirstAccount = user.getAccounts().get( optionFrom - 1 ).getCurrency();

            boolean continuePayment = false;
            for (Account account : user.getAccounts()) {
                if (continuePayment == false){
                    if ((indexOfFirstAccount != account.getId()) && (currencyFirstAccount.equals(account.getCurrency()))) {
                        continuePayment = true;
                    } else {
                        continuePayment = false;
                    }
                }
            }

            if (continuePayment == true) {
                makeTransfer(user, optionFrom, indexOfFirstAccount, currencyFirstAccount);
            } else {
                LOG.info( "You have a single " + currencyFirstAccount + " account. In order to make transfers " +
                        "between accounts, you must create another account of the same currency type." );
            }
        }
    }

    public void makeTransfer(User user, int optionFrom,int indexOfFirstAccount, Currency currencyFirstAccount) {

        BigDecimal balanceFirstAccount = user.getAccounts().get( optionFrom - 1 ).getBalance();

        BigDecimal validateBalanceOfPayment = validateBalanceAccount( balanceFirstAccount );

        BigDecimal balanceSecondAccount;
        int indexOfSecondAccount;
        int optionTo;
        boolean isConditionForPayment = false;
        do {
            optionTo = validateNumberAccount( optionFrom );
            balanceSecondAccount = user.getAccounts().get( optionTo - 1 ).getBalance();
            indexOfSecondAccount = user.getAccounts().get( optionTo -1 ).getId();
            Currency currencySecondAccount = user.getAccounts().get( optionTo - 1).getCurrency();
            if(currencyFirstAccount.equals( currencySecondAccount )){
                isConditionForPayment = true;
            } else {
                LOG.info( "Accounts must have the same currency type." );
            }
        } while (isConditionForPayment == false);

        BigDecimal toAccount = validateBalanceOfPayment.add( balanceSecondAccount );
        BigDecimal fromAccount = balanceFirstAccount.subtract( validateBalanceOfPayment );

        for (Account account : user.getAccounts()) {
            if (account.getId() == indexOfFirstAccount) {
                account.setBalance( fromAccount );
            }
            if (account.getId() == indexOfSecondAccount) {
                account.setBalance( toAccount );
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

    public int validateNumberAccount(int optionFrom){
        boolean isNumberDiferrent = false;
        LOG.info( "Type the number of account (to transfer money):" );
        int optionTo = IOService.getInstance().readInteger();
        while (isNumberDiferrent == false){
            if (optionFrom == optionTo){
                LOG.info( "You cannot make money transfers within the same account. Please type another " +
                        "number of account (to transfer money):" );
                optionTo = IOService.getInstance().readInteger();
            } else {
                isNumberDiferrent = true;
            }
        }
        return optionTo;
    }

}