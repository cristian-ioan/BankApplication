package service;

import model.Account;
import model.User;
import model.UserAccountCollections;

import java.util.ArrayList;
import java.util.List;

public class AccountUser {

    private static List<Account> userAccountList = new ArrayList<>();

    public static List<Account> getUserAccount(User user) {
        userAccountList.clear();
        for (Account account : UserAccountCollections.getInstance().getAccountList()) {
            if (user.getUserName().equals(account.getUsername())) {
                userAccountList.add(new Account( account.getUsername(), account.getAccountNumber(),
                        account.getBalance(), account.getCurrency()));
            }
        }
        return userAccountList;
    }

    public static List<Account> getUserAccountsList(User user){
        return AccountUser.getUserAccount(user);
    }

    public static void setUserAccountListEmpty(){
        userAccountList.clear();
    }

}
