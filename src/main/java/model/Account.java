package model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "account", schema = "bank-app")
public class Account {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(targetEntity = Transaction.class, mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaction> transactions;

    @Column(name="account_number", length = 50)
    private String account_Number;

    @Column(name="account_type", length = 50)
    private String account_Type;

    @Column(name="balance", precision = 16, scale = 2)
    private BigDecimal balance;

    @Column(name = "created_time", length = 8)
    private LocalDateTime createdTime;

    @Column(name = "updated_time", length = 8)
    private LocalDateTime updatedTime;

    public Account(){};

    public Account(User user, String account_Number, String account_Type,
                   BigDecimal balance, LocalDateTime createdTime, LocalDateTime updatedTime) {
        this.user = user;
        this.account_Number = account_Number;
        this.account_Type = account_Type;
        this.balance = balance;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public Account(long id, User user, List<Transaction> transactions, String account_Number, String account_Type,
                   BigDecimal balance, LocalDateTime createdTime, LocalDateTime updatedTime) {
        this.id = id;
        this.user = user;
        this.transactions = transactions;
        this.account_Number = account_Number;
        this.account_Type = account_Type;
        this.balance = balance;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public String getAccount_Number() {
        return account_Number;
    }

    public void setAccount_Number(String account_Number) {
        this.account_Number = account_Number;
    }

    public String getAccount_Type() {
        return account_Type;
    }

    public void setAccount_Type(String account_Type) {
        this.account_Type = account_Type;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Account)) return false;

        Account account = (Account) o;

        if (getId() != account.getId()) return false;
        if (getUser() != null ? !getUser().equals( account.getUser() ) : account.getUser() != null) return false;
        if (getTransactions() != null ? !getTransactions().equals( account.getTransactions() ) : account.getTransactions() != null)
            return false;
        if (getAccount_Number() != null ? !getAccount_Number().equals( account.getAccount_Number() ) : account.getAccount_Number() != null)
            return false;
        if (getAccount_Type() != null ? !getAccount_Type().equals( account.getAccount_Type() ) : account.getAccount_Type() != null)
            return false;
        if (getBalance() != null ? !getBalance().equals( account.getBalance() ) : account.getBalance() != null)
            return false;
        if (getCreatedTime() != null ? !getCreatedTime().equals( account.getCreatedTime() ) : account.getCreatedTime() != null)
            return false;
        return getUpdatedTime() != null ? getUpdatedTime().equals( account.getUpdatedTime() ) : account.getUpdatedTime() == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        result = 31 * result + (getTransactions() != null ? getTransactions().hashCode() : 0);
        result = 31 * result + (getAccount_Number() != null ? getAccount_Number().hashCode() : 0);
        result = 31 * result + (getAccount_Type() != null ? getAccount_Type().hashCode() : 0);
        result = 31 * result + (getBalance() != null ? getBalance().hashCode() : 0);
        result = 31 * result + (getCreatedTime() != null ? getCreatedTime().hashCode() : 0);
        result = 31 * result + (getUpdatedTime() != null ? getUpdatedTime().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", user=" + user +
                ", account_Number='" + account_Number + '\'' + ", account_Type='" + account_Type + '\'' +
                ", balance=" + balance + ", createdTime=" + createdTime + ", updatedTime=" + updatedTime + '}';
    }
}