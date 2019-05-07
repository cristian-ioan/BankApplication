package com.banking.model;

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

    @Column(name="account", length = 50, nullable = false)
    private String accountNumber;

    @Column(name="amount", precision = 16, scale = 2)
    private BigDecimal amount;

    @Column(name="details", length = 100)
    private String detail;

    @Column(name = "created_time", length = 8)
    private LocalDateTime createdTime;

    @Column(name = "field_type")
    private String fieldType;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name="account_id")
    private Account account;

    public Transaction(){}

    public Transaction(String accountNumber, BigDecimal amount, String detail, LocalDateTime createdTime,
                       String fieldType, Account account){
        this.accountNumber= accountNumber;
        this.amount = amount;
        this.detail = detail;
        this.createdTime = createdTime;
        this.fieldType = fieldType;
        this.account = account;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
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
        if (getAccountNumber() != null ? !getAccountNumber().equals( that.getAccountNumber() ) : that.getAccountNumber() != null)
            return false;
        if (getAmount() != null ? !getAmount().equals( that.getAmount() ) : that.getAmount() != null) return false;
        if (getDetail() != null ? !getDetail().equals( that.getDetail() ) : that.getDetail() != null) return false;
        if (getCreatedTime() != null ? !getCreatedTime().equals( that.getCreatedTime() ) : that.getCreatedTime() != null)
            return false;
        if (getFieldType() != null ? !getFieldType().equals( that.getFieldType() ) : that.getFieldType() != null)
            return false;
        return getAccount() != null ? getAccount().equals( that.getAccount() ) : that.getAccount() == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getAccountNumber() != null ? getAccountNumber().hashCode() : 0);
        result = 31 * result + (getAmount() != null ? getAmount().hashCode() : 0);
        result = 31 * result + (getDetail() != null ? getDetail().hashCode() : 0);
        result = 31 * result + (getCreatedTime() != null ? getCreatedTime().hashCode() : 0);
        result = 31 * result + (getFieldType() != null ? getFieldType().hashCode() : 0);
        result = 31 * result + (getAccount() != null ? getAccount().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Transaction{" + "id=" + id + ", accountNumber='" + accountNumber + '\'' + ", amount=" + amount +
                ", detail='" + detail + '\'' + ", createdTime=" + createdTime + ", fieldType='" + fieldType + '\'' +
                ", account=" + account + '}';
    }
}
