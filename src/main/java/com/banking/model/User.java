package com.banking.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user", schema = "bank-app")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(targetEntity = Account.class, mappedBy = "user", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<Account> accounts;

    @OneToMany(targetEntity = Notification.class, mappedBy = "user", cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY)
    private List<Notification> notifications;

    @Column(name="username", length = 16, nullable = false)
    private String userName;

    @Column(name="password", length = 32, nullable = false)
    private String password;

    @Column(name = "created_time", length = 8)
    private LocalDateTime createdTime;

    @Column(name = "updated_time", length = 8)
    private LocalDateTime updatedTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getId() != user.getId()) return false;
        if (getAccounts() != null ? !getAccounts().equals( user.getAccounts() ) : user.getAccounts() != null)
            return false;
        if (getNotifications() != null ? !getNotifications().equals( user.getNotifications() ) : user.getNotifications() != null)
            return false;
        if (getUserName() != null ? !getUserName().equals( user.getUserName() ) : user.getUserName() != null)
            return false;
        if (getPassword() != null ? !getPassword().equals( user.getPassword() ) : user.getPassword() != null)
            return false;
        if (getCreatedTime() != null ? !getCreatedTime().equals( user.getCreatedTime() ) : user.getCreatedTime() != null)
            return false;
        return getUpdatedTime() != null ? getUpdatedTime().equals( user.getUpdatedTime() ) : user.getUpdatedTime() == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getAccounts() != null ? getAccounts().hashCode() : 0);
        result = 31 * result + (getNotifications() != null ? getNotifications().hashCode() : 0);
        result = 31 * result + (getUserName() != null ? getUserName().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getCreatedTime() != null ? getCreatedTime().hashCode() : 0);
        result = 31 * result + (getUpdatedTime() != null ? getUpdatedTime().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", accounts=" + accounts + ", notifications=" +
                notifications + ", userName='" + userName + '\'' + ", password='" + password + '\'' +
                ", createdTime=" + createdTime + ", updatedTime=" + updatedTime + '}';
    }
}