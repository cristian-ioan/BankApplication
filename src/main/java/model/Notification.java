package model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notification", schema = "bank-app")
public class Notification {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name="details", length = 50)
    private String detail;

    @Column(name = "created_time", length = 8)
    private LocalDateTime createdTime;

    @Column(name = "sent_time", length = 8)
    private LocalDateTime sentTime;

    public Notification(long id, User user, String detail, LocalDateTime createdTime, LocalDateTime sentTime) {
        this.id = id;
        this.user = user;
        this.detail = detail;
        this.createdTime = createdTime;
        this.sentTime = sentTime;
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

    public LocalDateTime getSentTime() {
        return sentTime;
    }

    public void setSentTime(LocalDateTime sentTime) {
        this.sentTime = sentTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Notification)) return false;

        Notification that = (Notification) o;

        if (getId() != that.getId()) return false;
        if (getUser() != null ? !getUser().equals( that.getUser() ) : that.getUser() != null) return false;
        if (getDetail() != null ? !getDetail().equals( that.getDetail() ) : that.getDetail() != null) return false;
        if (getCreatedTime() != null ? !getCreatedTime().equals( that.getCreatedTime() ) : that.getCreatedTime() != null)
            return false;
        return getSentTime() != null ? getSentTime().equals( that.getSentTime() ) : that.getSentTime() == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        result = 31 * result + (getDetail() != null ? getDetail().hashCode() : 0);
        result = 31 * result + (getCreatedTime() != null ? getCreatedTime().hashCode() : 0);
        result = 31 * result + (getSentTime() != null ? getSentTime().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Notification{" + "id=" + id + ", user=" + user + ", detail='" + detail + '\'' +
                ", createdTime=" + createdTime + ", sentTime=" + sentTime + '}';
    }
}
