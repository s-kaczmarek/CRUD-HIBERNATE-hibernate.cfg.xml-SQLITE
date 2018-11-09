package domain.utils.comparators;

import domain.Contact;

import java.util.Comparator;

public class ComparatorContactByName implements Comparator<Contact> {

    @Override
    public int compare(Contact c1, Contact c2) {

        // if lastNames of compared objects are not the same, compare them
        if(!c1.getLastName().toLowerCase().equals(c2.getLastName().toLowerCase())){
            return c1.getLastName().compareTo(c2.getLastName());

        // if lastNames are the same, compare by firstName
        }else if(c1.getLastName().toLowerCase().equals(c2.getLastName().toLowerCase())){
            return c1.getFirstName().toLowerCase().compareTo(c2.getFirstName().toLowerCase());

            // other case like firstName and lastName are the same, compare by id
        }else{
            return c1.getContactId() - c2.getContactId();
        }
    }
}
