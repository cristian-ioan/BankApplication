package service;

import dao.AccountDao;
import model.Account;

import java.util.List;

public class AccountService {

    private AccountDao accountDao = new AccountDao();

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

}
