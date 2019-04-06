package storage;

import file.FileReader;
import model.Account;
import model.Currency;
import model.User;
import utils.FileUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AccountInitilizationImpl implements AccountInitilization {

    private FileReader fileReader = new FileReader();

    public List<Account> getAccountList() {
        return accountList;
    }

    private List<Account> accountList = new ArrayList<>();
    private static final Logger LOG = Logger.getLogger(Logger.class.getName());

    @Override
    public List<Account> initializeAccountList(User user) throws IOException {

        List<String> listStrings = fileReader.readFromFile( FileUtils.FILE_PATH_ACCOUNTS);
        int id = 0;

        for (String line : listStrings){
            String[] parts = line.split( FileUtils.SPLIT_BY_SPACE );
            if (parts.length >= FileUtils.NUMBER_OF_COLUMNS_ACCOUNTS){
                if (parts[0].equals(user.getUserName())) {
                    id++;
                    String userName = parts[0];
                    String accountNumber = parts[1];
                    BigDecimal balance = new BigDecimal(parts[2]);
                    String currency = parts[3];
                    model.Account account = new model.Account(id, userName, accountNumber, balance,
                        Currency.valueOf(currency));
                    accountList.add(account);
                }
            } else {
                LOG.warning("... ignoring line: " + line);
            }
        }
        return accountList;

    }
}
