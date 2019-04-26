package model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction", schema = "bank-app")
public class Transaction {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="to_account", length = 50, nullable = false)
    private String toAccount;

    @Column(name="balance", precision = 16, scale = 2)
    private BigDecimal balance;

    @Column(name="details", length = 100)
    private String detail;

    @Column(name = "created_time", length = 8)
    private LocalDateTime createdTime;

    @ManyToOne
    @JoinColumn(name="account_id")
    private Account account;

    public Transaction(){}

    public Transaction(String toAccount, BigDecimal balance, String detail, LocalDateTime createdTime,
                       Account account){
        this.toAccount = toAccount;
        this.balance = balance;
        this.detail = detail;
        this.createdTime = createdTime;
        this.account = account;
    }

    public Transaction(long id, String toAccount, BigDecimal balance, String detail, LocalDateTime createdTime,
                       Account account) {
        this.id = id;
        this.toAccount = toAccount;
        this.balance = balance;
        this.detail = detail;
        this.createdTime = createdTime;
        this.account = account;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Transaction)) return false;

        Transaction that = (Transaction) o;

        if (getId() != that.getId()) return false;
        if (getToAccount() != null ? !getToAccount().equals( that.getToAccount() ) : that.getToAccount() != null)
            return false;
        if (getBalance() != null ? !getBalance().equals( that.getBalance() ) : that.getBalance() != null) return false;
        if (getDetail() != null ? !getDetail().equals( that.getDetail() ) : that.getDetail() != null) return false;
        if (getCreatedTime() != null ? !getCreatedTime().equals( that.getCreatedTime() ) : that.getCreatedTime() != null)
            return false;
        return getAccount() != null ? getAccount().equals( that.getAccount() ) : that.getAccount() == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getToAccount() != null ? getToAccount().hashCode() : 0);
        result = 31 * result + (getBalance() != null ? getBalance().hashCode() : 0);
        result = 31 * result + (getDetail() != null ? getDetail().hashCode() : 0);
        result = 31 * result + (getCreatedTime() != null ? getCreatedTime().hashCode() : 0);
        result = 31 * result + (getAccount() != null ? getAccount().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Transaction{" + "id=" + id + ", toAccount='" + toAccount + '\'' + ", balance=" +
                balance + ", detail='" + detail + '\'' + ", createdTime=" + createdTime + ", account=" + account + '}';
    }
}
