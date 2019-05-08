package com.banking.service;

import com.banking.dao.AccountDao;
import com.banking.exception.DetailsAccountException;
import com.banking.model.Account;
import com.banking.model.FieldType;
import com.banking.model.Notification;
import com.banking.model.Transaction;
import com.banking.util.DetailsBankAccount;
import com.banking.validation.PaymentValidator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

public class AccountsTransfer {

    private AccountDao accountDao = new AccountDao();
    private PaymentValidator paymentValidator = new PaymentValidator();
    private final static Logger LOG = Logger.getLogger(Logger.class.getName());

    /**
     * Validates the number of accounts and the currencies type of user's bank accounts
     */
    public void verifyPayment(List<Account> accounts) throws DetailsAccountException {

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

        if (isConditionForPayment) {
            int optionFrom = 0;
            long indexOfFirstAccount;

            LOG.info( "All user bank accounts are:" );
            DetailsBankAccount.showDetailsUserBankAccount(accounts);

            optionFrom = paymentValidator.validateOptionFrom(numberUserAccounts);

            indexOfFirstAccount = accounts.get( optionFrom - 1 ).getId();
            String currencyFirstAccount = accounts.get( optionFrom - 1 ).getAccount_Type();

            boolean continuePayment = false;
            for (Account account : accounts) {
                if (!continuePayment){
                    if ((indexOfFirstAccount != account.getId()) && (currencyFirstAccount.equals(account.getAccount_Type()))) {
                        continuePayment = true;
                    } else {
                        continuePayment = false;
                    }
                }
            }

            if (continuePayment) {
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

        BigDecimal validateBalanceOfPayment = paymentValidator.validateBalanceAccount( balanceFirstAccount );

        BigDecimal balanceSecondAccount;
        long indexOfSecondAccount;
        int optionTo;
        boolean isConditionForPayment = false;
        do {
            optionTo = paymentValidator.validateNumberAccount(optionFrom, numberUserAccounts);
            balanceSecondAccount = accounts.get(optionTo - 1).getBalance();
            indexOfSecondAccount = accounts.get(optionTo -1).getId();
            String currencySecondAccount = accounts.get( optionTo - 1).getAccount_Type();
            if(currencyFirstAccount.equals( currencySecondAccount)){
                isConditionForPayment = true;
            } else {
                LOG.warning( "Accounts must have the same currency type." );
            }
        } while (!isConditionForPayment);

        BigDecimal newBalanceOfFirstAccount = balanceFirstAccount.subtract( validateBalanceOfPayment );
        BigDecimal newBalanceOfSecondAccount = validateBalanceOfPayment.add( balanceSecondAccount );

        LOG.info( "Type details of tran: " );
        String detailsTransaction = IOService.getInstance().readLine();
        LocalDateTime createdTime = LocalDateTime.now();

        Transaction transactionIncoming = new Transaction( accounts.get(optionTo - 1).getAccount_Number(),
                validateBalanceOfPayment, detailsTransaction, createdTime, String.valueOf(FieldType.INCOMING),
                accounts.get(optionTo - 1));
        Transaction transactionOutgoing = new Transaction(accounts.get(optionFrom - 1).getAccount_Number(),
                validateBalanceOfPayment, detailsTransaction, createdTime, String.valueOf(FieldType.OUTGOING),
                accounts.get(optionFrom - 1));

        LocalDateTime sentTime = LocalDateTime.now();
        String detailsNotification = "From: ".concat(accounts.get(optionFrom-1).getAccount_Number()).
                concat(" To: ").concat(accounts.get(optionTo-1).getAccount_Number()).concat(" amount: ").
                concat(String.valueOf(validateBalanceOfPayment)).concat(" -> ").concat(detailsTransaction);
        Notification notification = new Notification(accounts.get(optionTo - 1).getUser(), detailsNotification,
                createdTime, sentTime);

        accountDao.updateAccountcreateTransaction(accounts, indexOfFirstAccount, indexOfSecondAccount,
                newBalanceOfFirstAccount, newBalanceOfSecondAccount, transactionIncoming, transactionOutgoing, notification);

    }

}
