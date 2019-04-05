package storage;

import file.FileReader;
import model.Account;
import model.Currency;
import utils.FileUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class UserAccountInitialization implements UserAccount {

    private static UserAccountInitialization instance;
    private FileReader fileReader = new FileReader();
    private  Map<String, String> userPasswordMap = new LinkedHashMap<>();
    private List<Account> accountList = new ArrayList<>();
    private static final Logger LOG = Logger.getLogger(Logger.class.getName());

    private UserAccountInitialization(){
    }

    public static synchronized UserAccountInitialization getInstance() {
        if (instance == null) {
            instance = new UserAccountInitialization();
        }
        return instance;
    }

    public Map<String, String> getUserPasswordMap() {
        return userPasswordMap;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    @Override
    public void initializeMapUserPassword() throws IOException {

        List<String> listStrings = fileReader.readFromFile(FileUtils.FILE_PATH_USERS);

        for (String line : listStrings){
            String[] parts = line.split(FileUtils.SPLIT_BY_SPACE);
            if (parts.length >= FileUtils.NUMBER_OF_COLUMNS_USERS) {
                String key = parts[0];
                String value = parts[1];
                userPasswordMap.put(key, value);
            } else {
                LOG.warning("... ignoring line: " + line);
            }
        }

    }

    @Override
    public void initializeAccountList() throws IOException {

        List<String> listStrings = fileReader.readFromFile(FileUtils.FILE_PATH_ACCOUNTS);
        int id = 0;

        for (String line : listStrings){
            String[] parts = line.split( FileUtils.SPLIT_BY_SPACE );
            if (parts.length >= FileUtils.NUMBER_OF_COLUMNS_ACCOUNTS) {
                id++;
                String userName = parts[0];
                String accountNumber = parts[1];
                BigDecimal balance = new BigDecimal(parts[2]);
                String currency = parts[3];
                Account account = new Account(id, userName, accountNumber, balance,
                        Currency.valueOf(currency));
                accountList.add(account);
            } else {
                LOG.warning("... ignoring line: " + line);
            }
        }

    }

}