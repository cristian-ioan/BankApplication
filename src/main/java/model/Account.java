package model;

import utils.Currency;

import java.math.BigDecimal;

public class Account {

    private int id;
    private String username;
    private String accountNumber;
    private BigDecimal balance;
    private Currency currency;

    public Account(int id, String username, String accountNumber, BigDecimal balance, Currency currency) {
        this.id = id;
        this.username = username;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.currency = currency;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Account)) return false;

        Account account = (Account) o;

        if (getId() != account.getId()) return false;
        if (getAccountNumber() != null ? !getAccountNumber().equals( account.getAccountNumber() ) : account.getAccountNumber() != null)
            return false;
        if (getUsername() != null ? !getUsername().equals( account.getUsername() ) : account.getUsername() != null)
            return false;
        if (getBalance() != null ? !getBalance().equals( account.getBalance() ) : account.getBalance() != null)
            return false;
        return getCurrency() == account.getCurrency();

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getAccountNumber() != null ? getAccountNumber().hashCode() : 0);
        result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
        result = 31 * result + (getBalance() != null ? getBalance().hashCode() : 0);
        result = 31 * result + (getCurrency() != null ? getCurrency().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", username='" + username + '\'' + ", accountNumber='" + accountNumber + '\'' + ", balance=" + balance + ", currency=" + currency + '}';
    }
}