package file;

import utils.FileUtils;
import utils.Currency;
import model.Account;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AccountReader {

    private static AccountReader instance;
    private List<Account> accountList = new ArrayList<>();
    private static final Logger LOG = Logger.getLogger(Logger.class.getName());

    private AccountReader(){}

    public static synchronized AccountReader getInstance(){
        if (instance == null){
            instance = new AccountReader();
        }
        return instance;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public void initializeAccountList() throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader( FileUtils.FILE_PATH_ACCOUNTS))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split( FileUtils.SPLIT_BY_SPACE );
                if (parts.length >= FileUtils.NUMBER_OF_COLUMNS) {
                    String userName = parts[0];
                    String accountNumber = parts[1];
                    BigDecimal balance = new BigDecimal(parts[2]);
                    String currency = parts[3];
                    Account account = new Account(userName, accountNumber, balance, Currency.valueOf(currency));
                    accountList.add(account);
                } else {
                    LOG.warning("... ignoring line: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            LOG.warning("File not found!");
        }
    }
}