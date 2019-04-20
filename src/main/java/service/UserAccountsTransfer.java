package service;

import model.Account;
import model.Currency;
import model.User;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.logging.Logger;

public class UserAccountsTransfer {

    private final static Logger LOG = Logger.getLogger(Logger.class.getName());

    /**
     * Validates the number of accounts and the currencies type of user's bank accounts
     *
     * @param numberUserAccounts number user's bank accounts
     * @param isConditionForPayment at first I assume there are no conditions for making the payment
     * @param currencyTypeForFirstAccount currency type of first bank account
     * @param currencyTypeForSecondAccount currency type of second bank account
     * @param optionFrom the account number from which the payment is made
     * @param indexOfFirstAccount the bank account id from which the payment is made
     *                            (from the list of user's bank accounts)
     * @param currencyFirstAccount the currency type of indexOfFirstAccount
     * @param continuePayment validates if there is a bank account with the same currency type
     *                        as the account from which payment is made
     * @param LOG logger
     */
    public void validateNumberAndCurrencyTypeOfUserBankAccounts(User user){

        int numberUserAccounts = user.getAccounts().size();
        boolean isConditionForPayment = false;

        if (numberUserAccounts == 0){
            LOG.warning( user.getUserName() + " does not have any bank account." );
        } else {
            if (numberUserAccounts == 1) {
                LOG.warning( "You have one bank account. Please create another bank account of the same type: "
                        + user.getAccounts().get( 0 ).getCurrency() );
            } else {
                if (numberUserAccounts == 2) {
                    Currency currencyTypeForFirstAccount = user.getAccounts().get(0).getCurrency();
                    Currency currencyTypeForSecondAccount = user.getAccounts().get(1).getCurrency();
                    if (!currencyTypeForFirstAccount.equals(currencyTypeForSecondAccount)) {
                        LOG.warning("You do not have accounts of the same currency type. Create another one!");
                    } else {
                        isConditionForPayment = true;
                    }
                } else {
                    isConditionForPayment = true;
                }
            }
        }

        if (isConditionForPayment == true) {
            int optionFrom = 0;
            int indexOfFirstAccount;

            LOG.info( "All user bank accounts are:" );
            DetailsBankAccount.showDetailsUserBankAccount( user );

            boolean isBadOption = false;
            while (isBadOption == false){
                try {
                    LOG.info( "Type the number of account (from) - it must be between 1 and " + numberUserAccounts + ": " );
                    optionFrom = IOService.getInstance().readInteger();
                    while (optionFrom > numberUserAccounts || optionFrom < 0){
                        LOG.info( "Type the number of account (from) - it must be between 1 and " + numberUserAccounts + ": " );
                        optionFrom = IOService.getInstance().readInteger();
                    }
                    isBadOption = true;
                } catch (InputMismatchException e){
                    LOG.warning( "Incorrect entry. Please input only integer." );
                    isBadOption = false;
                }
            }

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
                makeTransfer(user, optionFrom, indexOfFirstAccount, currencyFirstAccount, numberUserAccounts);
            } else {
                LOG.warning( "You have a single " + currencyFirstAccount + " account. In order to make transfers " +
                        "between accounts, you must create another account of the same currency type." );
            }
        }
    }

    /**
     * Makes transfer money between user's bank accounts.
     *
     * @param balanceFirstAccount the balance of the account from which the transfer will be made
     * @param validateBalanceOfPayment the amount of money that will be withdrawn (from first account)
     * @param balanceSecondAccount the balance of the account where the transfer will be made
     * @param indexOfSecondAccount the bank account id where the payment will be made
     *                             (from the list of user's bank accounts)
     * @param optionTo the account where the payment is made
     * @param isConditionForPayment validates if the account where we want to transfer money has the
     *                              same currency type as the account from we withdraw money
     * @param newBalanceOfFirstAccount the new balance of the account from which money was withdrawn
     * @param newBalanceOfSecondAccount the new balance of the account in which money was transferred
     * @param LOG logger
     */
    public void makeTransfer(User user, int optionFrom,int indexOfFirstAccount, Currency currencyFirstAccount,
                             int numberUserAccounts) {

        BigDecimal balanceFirstAccount = user.getAccounts().get( optionFrom - 1 ).getBalance();

        BigDecimal validateBalanceOfPayment = validateBalanceAccount( balanceFirstAccount );

        BigDecimal balanceSecondAccount;
        int indexOfSecondAccount;
        int optionTo;
        boolean isConditionForPayment = false;
        do {
            optionTo = validateNumberAccount( optionFrom, numberUserAccounts );
            balanceSecondAccount = user.getAccounts().get( optionTo - 1 ).getBalance();
            indexOfSecondAccount = user.getAccounts().get( optionTo -1 ).getId();
            Currency currencySecondAccount = user.getAccounts().get( optionTo - 1).getCurrency();
            if(currencyFirstAccount.equals( currencySecondAccount )){
                isConditionForPayment = true;
            } else {
                LOG.warning( "Accounts must have the same currency type." );
            }
        } while (isConditionForPayment == false);

        BigDecimal newBalanceOfFirstAccount = balanceFirstAccount.subtract( validateBalanceOfPayment );
        BigDecimal newBalanceOfSecondAccount = validateBalanceOfPayment.add( balanceSecondAccount );

        for (Account account : user.getAccounts()) {
            if (account.getId() == indexOfFirstAccount) {
                account.setBalance( newBalanceOfFirstAccount );
            }
            if (account.getId() == indexOfSecondAccount) {
                account.setBalance( newBalanceOfSecondAccount );
            }
        }
    }

    /**
     * Validates if the value that you want to be transferred does not exceed the value available in the user account.
     *
     * @param LOG logger
     * @return balanceFrom the value that you want to be transferred
     */
    public BigDecimal validateBalanceAccount(BigDecimal balance){
        BigDecimal balanceFrom = null;
        boolean isBadOption = false;
        while (isBadOption == false)    {
            try {
                LOG.info("Enter balance for account: ");
                balanceFrom = IOService.getInstance().readBigDecimal();
                while (balance.compareTo(balanceFrom) < 0){
                    LOG.info("Type a new balance: ");
                    balanceFrom = IOService.getInstance().readBigDecimal();
                }
                isBadOption = true;
            } catch (InputMismatchException e) {
               LOG.warning( "Incorrect entry. Please input only integer." );
               isBadOption = false;
            }
        }

        return balanceFrom;
    }

    /**
     * Validates if the account where you want to transfer the money is the same with the account from which
     * the transfer is made.
     *
     * @param LOG logger
     * @return optionTo the account where you want to transfer the money
     */
    public int validateNumberAccount(int optionFrom, int numberUserAccounts){
        int optionTo = 0;
        boolean isBadOption = false;
        while (isBadOption == false){
            try {
                LOG.info( "Type the number of account (to transfer money): " );
                optionTo = IOService.getInstance().readInteger();
                while (optionTo > numberUserAccounts || optionTo < 0){
                    LOG.info( "Type the number of account (from) - it must be between 1 and " + numberUserAccounts + ": " );
                    optionTo = IOService.getInstance().readInteger();
                }
                if (optionFrom == optionTo){
                    LOG.warning( "You cannot make money transfers within the same account. Please type another " +
                        "number of account (to transfer money):" );
                } else {
                    isBadOption = true;
                }
            } catch (InputMismatchException e){
                LOG.warning( "Incorrect entry. Please input only integer." );
                isBadOption = false;
            }
        }

        return optionTo;
    }

}