package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an User.
 *
 * @param username user's name
 * @param userPassword user's password
 * @param accounts list of accounts for an user
 *
 * @author Cristian-Lucian IOAN
 * @version 1.0
 * @since   2019-03-21
 */
public class User {

    private String userName;
    private String userPassword;
    private List<Account> accounts;

    /**
     * This constructor creates an user with his list of accounts.
     */
    public User(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
        accounts = new ArrayList<>();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getUserName() != null ? !getUserName().equals( user.getUserName() ) : user.getUserName() != null)
            return false;
        return getUserPassword() != null ? getUserPassword().equals( user.getUserPassword() ) :
                user.getUserPassword() == null;
    }

    @Override
    public int hashCode() {
        int result = getUserName() != null ? getUserName().hashCode() : 0;
        result = 31 * result + (getUserPassword() != null ? getUserPassword().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "model.UserInitilization{" + "userName='" + userName + '\'' + ", userPassword='" + userPassword + '\'' + '}';
    }

}