package service;

import file.FileReader;
import model.Account;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class AccountUser {

    private static AccountUser instance;
    private List<Account> userAccountList = new ArrayList<>();

    private AccountUser(){}

    public static synchronized AccountUser getInstance(){
        if (instance == null){
            instance = new AccountUser();
        }
        return instance;
    }

    public List<Account> getUserAccountList() {
        return userAccountList;
    }

    public void setUserAccountList(List<Account> userAccountList) {
        this.userAccountList = userAccountList;
    }

    public void getUserAccount(User user) {
        userAccountList.clear();
        for (Account account : FileReader.getInstance().getAccountList()) {
            if (user.getUserName().equals(account.getUsername())) {
                userAccountList.add(new Account( account.getUsername(), account.getAccountNumber(),
                        account.getBalance(), account.getCurrency()));
            }
        }
    }

}
