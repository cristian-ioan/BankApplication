package model;

import javax.persistence.*;

@Entity
@Table(name = "person", schema = "bank-app")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="user_id")
    private User user;

    @Column(name="address", length = 50)
    private String address;

    @Column(name = "first_name", length = 16)
    private String firstName;

    @Column(name = "last_name", length = 16)
    private String lastName;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (getId() != person.getId()) return false;
        if (getUser() != null ? !getUser().equals( person.getUser() ) : person.getUser() != null) return false;
        if (getAddress() != null ? !getAddress().equals( person.getAddress() ) : person.getAddress() != null)
            return false;
        if (getFirstName() != null ? !getFirstName().equals( person.getFirstName() ) : person.getFirstName() != null)
            return false;
        return getLastName() != null ? getLastName().equals( person.getLastName() ) : person.getLastName() == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", user=" + user + ", address='" + address + '\'' +
                ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + '}';
    }
}
