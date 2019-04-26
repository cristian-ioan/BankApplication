package service;

import dao.AccountDao;
import model.Account;
import model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.logging.Logger;

public class AccountsTransfer {

    private AccountDao accountDao = new AccountDao();
    private final static Logger LOG = Logger.getLogger(Logger.class.getName());

    /**
     * Validates the number of accounts and the currencies type of user's bank accounts
     */
    public void verifyPayment(List<Account> accounts){

        int numberUserAccounts = accounts.size();
        boolean isConditionForPayment = false;

        if (numberUserAccounts == 0){
            LOG.warning( "You don't have any bank account." );
        } else {
            if (numberUserAccounts == 1) {
                LOG.warning( "You have one bank account. Please create another bank account of the same type: "
                        + accounts.get(0).getAccount_Type());
            } else {
                if (numberUserAccounts == 2) {
                    String currencyTypeOfFirstAccount = accounts.get(0).getAccount_Type();
                    String currencyTypeOfSecondAccount = accounts.get(1).getAccount_Type();
                    if (!currencyTypeOfFirstAccount.equals(currencyTypeOfSecondAccount)) {
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
            long indexOfFirstAccount;

            LOG.info( "All user bank accounts are:" );
            DetailsBankAccount.showDetailsUserBankAccount(accounts);

            optionFrom = validateOptionFrom(numberUserAccounts);

            indexOfFirstAccount = accounts.get( optionFrom - 1 ).getId();
            String currencyFirstAccount = accounts.get( optionFrom - 1 ).getAccount_Type();

            boolean continuePayment = false;
            for (Account account : accounts) {
                if (continuePayment == false){
                    if ((indexOfFirstAccount != account.getId()) && (currencyFirstAccount.equals(account.getAccount_Type()))) {
                        continuePayment = true;
                    } else {
                        continuePayment = false;
                    }
                }
            }

            if (continuePayment == true) {
                makeTransfer(accounts, optionFrom, indexOfFirstAccount, currencyFirstAccount, numberUserAccounts);
            } else {
                LOG.warning( "You have a single " + currencyFirstAccount + " account. In order to make transfers " +
                        "between accounts, you must create another account of the same currency type." );
            }
        }
    }

    /**
     * Makes transfer money between user's bank accounts.
     */
    public void makeTransfer(List<Account> accounts, int optionFrom,long indexOfFirstAccount, String currencyFirstAccount,
                             int numberUserAccounts) {

        BigDecimal balanceFirstAccount = accounts.get(optionFrom - 1).getBalance();

        BigDecimal validateBalanceOfPayment = validateBalanceAccount( balanceFirstAccount );

        BigDecimal balanceSecondAccount;
        long indexOfSecondAccount;
        int optionTo;
        boolean isConditionForPayment = false;
        do {
            optionTo = validateNumberAccount( optionFrom, numberUserAccounts );
            balanceSecondAccount = accounts.get( optionTo - 1 ).getBalance();
            indexOfSecondAccount = accounts.get( optionTo -1 ).getId();
            String currencySecondAccount = accounts.get( optionTo - 1).getAccount_Type();
            if(currencyFirstAccount.equals( currencySecondAccount )){
                isConditionForPayment = true;
            } else {
                LOG.warning( "Accounts must have the same currency type." );
            }
        } while (isConditionForPayment == false);

        BigDecimal newBalanceOfFirstAccount = balanceFirstAccount.subtract( validateBalanceOfPayment );
        BigDecimal newBalanceOfSecondAccount = validateBalanceOfPayment.add( balanceSecondAccount );

        LOG.info( "Type details of tran: " );
        String detailsTransaction = IOService.getInstance().readLine();
        LocalDateTime createdTime = LocalDateTime.now();

        Transaction tran = null;
        tran = new Transaction( accounts.get(optionTo - 1).getAccount_Number(),validateBalanceOfPayment,
                detailsTransaction, createdTime, accounts.get(optionTo - 1));

        accountDao.updateAccountcreateTransaction( accounts, indexOfFirstAccount, indexOfSecondAccount,
                newBalanceOfFirstAccount, newBalanceOfSecondAccount, tran );

    }

    /**
     * Validates the account number
     *
     * @param numberUserAccounts
     * @return
     */
    private int validateOptionFrom(int numberUserAccounts){
        boolean isBadOption = false;
        int optionFrom = 0;
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
        return optionFrom;
    }

    /**
     * Validates if the value that you want to be transferred does not exceed the value available in the user account.
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
