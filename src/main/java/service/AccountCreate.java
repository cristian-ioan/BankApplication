package service;

import file.FileWriter;
import model.Account;
import model.User;
import storage.UserInitializationImpl;
import model.Currency;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Random;
import java.util.logging.Logger;

public class AccountCreate {

    private FileWriter fileWriter = new FileWriter();
    private Account newAccount;
    private String addLineIntoAccountsFile;
    private final static Logger LOG = Logger.getLogger(Logger.class.getName());

    public void createUserBankAccount(User user) throws IOException {
        String iban = generateIban();
        LOG.info("Iban: " + iban);

        LOG.info("Enter balance for account: ");
        BigDecimal balanceOfUserBankAccount = new BigDecimal(IOService.getInstance().readInteger());

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

    public String generateIban() {
        Random value = new Random();
        String iban = "RO";
        String swiftCode = "RZBR";

        int r1 = value.nextInt( 10 );
        int r2 = value.nextInt( 10 );
        if (r1 == 0){
            if (r2 != 0){
                iban += "0" + r2 + swiftCode + "000000";
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

    public String validateCurrencyType(){
        LOG.info("Enter currency [RON/EUR]: ");
        String currencyTypeOfUserBankAccount = IOService.getInstance().readLine();
        while (!currencyTypeOfUserBankAccount.substring(0, 3).equals(String.valueOf(Currency.RON)) &&
                !currencyTypeOfUserBankAccount.substring(0, 3).equals(String.valueOf(Currency.EUR))){
            LOG.warning("The currency must be 'RON' or 'EUR'!");
            LOG.info("Enter currency [RON/EUR]: ");
            currencyTypeOfUserBankAccount = IOService.getInstance().readLine();
        }
        return currencyTypeOfUserBankAccount;
    }

}