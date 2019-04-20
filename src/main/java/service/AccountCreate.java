package service;

import file.FileWriter;
import model.Account;
import model.User;
import model.Currency;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.logging.Logger;

public class AccountCreate {

    private FileWriter fileWriter = new FileWriter();
    private Account newAccount;
    private String addLineIntoAccountsFile;
    private final static Logger LOG = Logger.getLogger(Logger.class.getName());

    /**
     * Creates the new bank account for the logged user.
     *
     * @param iban new user account
     * @param balanceOfUserBankAccount balance of the new user account
     * @param currencyTypeOfUserBankAccount currency type of the new user account
     * @param newId new id for the new user account
     * @param newAccount new user account
     * @param addLineIntoAccountsFile the line that will be added into the file
     * @param fileWriter instantiates the FileWriter class
     * @param LOG logger
     */
    public void createUserBankAccount(User user) throws IOException {

        String iban = generateIban();
        LOG.info("Iban: " + iban);
        
        BigDecimal balanceOfUserBankAccount = null;
        boolean isBadOption = false;
        while(isBadOption == false){
            try{
                LOG.info("Enter balance for account: ");
                balanceOfUserBankAccount = IOService.getInstance().readBigDecimal();
                isBadOption = true;
            } catch (InputMismatchException e){
                LOG.warning( "Incorrect entry. Please input only integer." );
                isBadOption = false;
            }
        }

        String currencyTypeOfUserBankAccount = validateCurrencyType();

        int newId = user.getAccounts().size() +1 ;

        newAccount = new Account(newId, user.getUserName(),
                iban, balanceOfUserBankAccount, Currency.valueOf(currencyTypeOfUserBankAccount));
        user.getAccounts().add(newAccount);

        addLineIntoAccountsFile = "\n" + user.getUserName() + " " + iban + " "
                + balanceOfUserBankAccount.toString() + " " + currencyTypeOfUserBankAccount;
        fileWriter.writeStringToFile(addLineIntoAccountsFile);

        LOG.info("The bank account for " + user.getUserName() + " was successfully created!");
        addLineIntoAccountsFile = null;
        newAccount = null;
    }

    /**
     * Generates a new bank account(IBAN).
     *
     * @return iban the new bank account
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
     * @return currencyTypeOfUserBankAccount the currency type of account
     */
    public String validateCurrencyType(){

        String currencyTypeOfUserBankAccount = null;
        int option;
        boolean isBadOption = false;

        do{
            try {
                LOG.info("Input 1 for RON or input 2 for EUR: ");
                option = IOService.getInstance().readInteger();

                switch (option) {
                    case 1:
                        currencyTypeOfUserBankAccount = "RON";
                        isBadOption = true;
                        break;
                    case 2:
                        currencyTypeOfUserBankAccount = "EUR";
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

        return currencyTypeOfUserBankAccount;
    }

}