package domain;

import org.hibernate.annotations.Cascade;
import org.hibernate.sql.Update;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contacts")
public class Contact {
    @Id
    @GeneratedValue
    private int contactId;
    private String firstName;
    private String lastName;

    @OneToOne(targetEntity = Email.class,
              cascade = CascadeType.ALL)
    private Email email;

    @ManyToMany(targetEntity = Group.class, cascade = CascadeType.ALL)
    @JoinTable(
            name="Contact_Groups",
            joinColumns=@JoinColumn(name="CONTACT_ID", referencedColumnName="contactId"),
            inverseJoinColumns=@JoinColumn(name="GROUP_ID", referencedColumnName="groupId"))
    private List<Group> groups = new ArrayList<Group>();

    // Constructor

    public Contact(){}

    public Contact(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "contactId=" + contactId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email=" + email +
                ", groups=" + groups +
                '}';
    }

    // Getters and Setters


    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
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

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
