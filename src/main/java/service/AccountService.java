package service;

import dao.AccountDao;
import exception.BalanceException;
import model.Account;
import model.User;
import validation.AccountValidator;
import util.IbanGeneratorUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

public class AccountService {

    private AccountDao accountDao = new AccountDao();
    private Account newAccount;
    private AccountValidator accountValidator = new AccountValidator();
    private final static Logger LOG = Logger.getLogger(Logger.class.getName());

    public void createAccount(Account account){
       accountDao.createAccount(account);
    }

    public Account findById(Long id){
        return accountDao.getEntityById(Account.class, id);
    }

    public List<Account> findAll(){
        return accountDao.findAll();
    }

    public List<Account> findAllAccountsById(Long id){
        return accountDao.findAllAccountsById( id );
    }

    public Account updateAccount(Account account) {
        return accountDao.updateEntity( account );
    }

    /**
     * Creates the new bank account for the logged user.
     */
    public void createUserBankAccount(User user)  throws BalanceException {

        String iban = IbanGeneratorUtils.generateIban();
        LOG.info("Iban: " + iban);

        BigDecimal balanceAccount = accountValidator.validateBalance();

        String currencyTypeOfAccount = accountValidator.validateCurrencyType();

        LocalDateTime createdTime = LocalDateTime.now();

        newAccount = new Account(user, iban, currencyTypeOfAccount, balanceAccount, createdTime, createdTime);

        createAccount(newAccount);

        LOG.info("The bank account for " + user.getUserName() + " was successfully created!");

        newAccount = null;
    }

}
