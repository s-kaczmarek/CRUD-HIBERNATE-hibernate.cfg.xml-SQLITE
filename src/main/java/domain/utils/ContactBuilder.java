package domain.utils;

import domain.Contact;
import domain.Email;
import domain.Group;

public class ContactBuilder {

    private Contact contact;
    private Group group;
    private Email email;

    public ContactBuilder(){
        this.contact = new Contact();
    }

    public void buildFirstName(String firstName){
        contact.setFirstName(firstName);
    }

    public void buildLastName(String lastName){
        contact.setLastName(lastName);
    }

    public void buildEmail(String email_){
        this.email = new Email(email_);
        contact.setEmail(email);
    }

    public void buildGroups(String ...groups){

        for(int i=0; i < groups.length; i++){

            this.group = new Group(groups[i]);
            contact.getGroups().add(group);
            group.getContacts().add(contact);

        }
    }

    public Contact build(){
        return this.contact;
    }
}
