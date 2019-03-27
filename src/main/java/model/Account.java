package model;

import constant.Currency;

import java.math.BigDecimal;

public class Account {

    private String accountNumber;
    private String username;
    private BigDecimal balance;
    private Currency currency;

    public Account(String username, String accountNumber, BigDecimal balance, Currency currency) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Account)) return false;

        Account account = (Account) o;

        if (!getAccountNumber().equals( account.getAccountNumber() )) return false;
        if (!getUsername().equals( account.getUsername() )) return false;
        if (!getBalance().equals( account.getBalance() )) return false;
        return getCurrency() == account.getCurrency();
    }

    @Override
    public int hashCode() {
        int result = getAccountNumber().hashCode();
        result = 31 * result + getUsername().hashCode();
        result = 31 * result + getBalance().hashCode();
        result = 31 * result + getCurrency().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Account{" + "accountNumber='" + accountNumber + '\'' + ", username='" + username + '\'' + ", balance=" + balance + ", currency=" + currency + '}';
    }
}
