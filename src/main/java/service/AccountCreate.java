package service;

import model.Account;
import model.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.logging.Logger;

public class AccountCreate {

    private Account newAccount;
    private AccountService accountService = new AccountService();
    private final static Logger LOG = Logger.getLogger(Logger.class.getName());

    /**
     * Creates the new bank account for the logged user.
     */
    public void createUserBankAccount(User user)  {

        String iban = generateIban();
        LOG.info("Iban: " + iban);

        BigDecimal balanceAccount = validateBalance();

        String currencyTypeOfAccount = validateCurrencyType();

        LocalDateTime createdTime = LocalDateTime.now();

        newAccount = new Account(user, iban, currencyTypeOfAccount, balanceAccount, createdTime, createdTime);

        accountService.createAccount(newAccount);

        LOG.info("The bank account for " + user.getUserName() + " was successfully created!");

        newAccount = null;
    }

    /**
     * Generates a new bank account(IBAN).
     *
     * @return iban  = the new bank account
     */
    public String generateIban() {

        Random value = new Random();
        String iban = "RO";
        String swiftCode = "RZBR";

        int r1 = value.nextInt( 10 );
        int r2 = value.nextInt( 10 );
        if (r1 == 0){
            if (r2 != 0){
                iban += "0" + r2 + swiftCode + "000000";
            } else {
                iban += "0" + "0" + swiftCode + "000000";
            }
        } else {
            if (r2 == 0){
                iban += r1 + "0" + swiftCode + "000000";
            } else {
                iban += r1 + r2 + swiftCode + "000000";
            }
        }

        int count = 0;
        int n;
        for (int i = 0; i < 10; i++) {
            n = value.nextInt( 10 );
            iban += Integer.toString(n) ;
            count++;

        }
        return iban;
    }

    /**
     * Validates currency type of bank account.
     *
     * @return balance of account
     */
    public BigDecimal validateBalance(){

        BigDecimal balanceOfBankAccount = null;
        boolean isBadOption = false;
        while(isBadOption == false){
            try{
                LOG.info("Enter balance for account: ");
                balanceOfBankAccount = IOService.getInstance().readBigDecimal();
                isBadOption = true;
            } catch (InputMismatchException e){
                LOG.warning( "Incorrect entry. Please input only integer." );
                isBadOption = false;
            }
        }
        return balanceOfBankAccount;

    }

    /**
     * Validates currency type of bank account.
     *
     * @return currencyTypeOfUserBankAccount the currency type of account
     */
    public String validateCurrencyType(){

        String currencyType = null;
        int option;
        boolean isBadOption = false;

        do{
            try {
                LOG.info("Input 1 for RON or input 2 for EUR: ");
                option = IOService.getInstance().readInteger();

                switch (option) {
                    case 1:
                        currencyType = "RON";
                        isBadOption = true;
                        break;
                    case 2:
                        currencyType = "EUR";
                        isBadOption = true;
                        break;
                    default:
                        LOG.warning("Not a valid option.");
                        break;
                }
            } catch (InputMismatchException e){
                LOG.warning( "Incorrect entry. Please input 1 for RON or 2 for EUR." );
            }

        } while (isBadOption == false);

        return currencyType;
    }
}
