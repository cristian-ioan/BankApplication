package file;

import model.Account;
import utils.Currency;
import utils.FileUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class FileReader {

    private static FileReader instance;
    private Map<String, String> userPasswordMap = new LinkedHashMap<>();
    private List<Account> accountList = new ArrayList<>();
    private static final Logger LOG = Logger.getLogger(Logger.class.getName());

    private FileReader(){}

    public static synchronized FileReader getInstance() {
        if (instance == null) {
            instance = new FileReader();
        }
        return instance;
    }

    public Map<String, String> getUserPasswordMap() {
        return userPasswordMap;
    }

    public void setUserPasswordMap(Map<String, String> userPasswordMap) {
        this.userPasswordMap = userPasswordMap;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public void initializeMapUserPassword() throws IOException {

        try (BufferedReader reader = new BufferedReader(new java.io.FileReader( FileUtils.FILE_PATH_USERS))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split( FileUtils.SPLIT_BY_SPACE );
                if (parts.length >= 2) {
                    String key = parts[0];
                    String value = parts[1];
                    userPasswordMap.put(key, value);
                } else {
                    LOG.warning("... ignoring line: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            LOG.warning("File not found: " + e.getMessage());
        }
    }

    public void initializeAccountList() throws IOException {

        try (BufferedReader reader = new BufferedReader(new java.io.FileReader( FileUtils.FILE_PATH_ACCOUNTS))) {
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
            LOG.warning("File not found: " + e.getMessage());
        }
    }
}
