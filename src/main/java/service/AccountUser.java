package service;

import file.FileReader;
import model.Account;
import model.User;
import model.UserAccountCollections;

import java.util.ArrayList;
import java.util.List;

public class AccountUser {

    private List<Account> userAccountList = new ArrayList<>();

    public List<Account> getUserAccountList() {
        return userAccountList;
    }

    public void setUserAccountList(List<Account> userAccountList) {
        this.userAccountList = userAccountList;
    }

    public List<Account> getUserAccount(User user) {
        userAccountList.clear();
        for (Account account : UserAccountCollections.getInstance().getAccountList()) {
            if (user.getUserName().equals(account.getUsername())) {
                userAccountList.add(new Account( account.getUsername(), account.getAccountNumber(),
                        account.getBalance(), account.getCurrency()));
            }
        }
        return userAccountList;
    }

    public void setUserAccountListEmpty(){
        userAccountList.clear();
    }

}
