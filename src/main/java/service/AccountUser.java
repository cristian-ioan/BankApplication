package service;

import model.Account;
import model.User;
import model.UserAccountInitialization;

import java.util.*;

public class AccountUser {

    private static List<Account> userAccountList = new ArrayList<>();

    public static List<Account> getUserAccount(User user) {
        userAccountList.clear();
        for (Account account : UserAccountInitialization.getInstance().getAccountList()) {
            if (user.getUserName().equals(account.getUsername())) {
                userAccountList.add(new Account( account.getId(), account.getUsername(), account.getAccountNumber(),
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

    public static int getSizeOfUserAccountList(){
        return UserAccountInitialization.getInstance().getAccountList().size();
    }

}
