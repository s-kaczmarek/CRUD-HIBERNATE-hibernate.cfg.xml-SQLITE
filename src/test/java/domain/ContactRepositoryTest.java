package domain;

import domain.utils.ContactBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class ContactRepositoryTest {

    @Test
    public void shouldAddContactsFromListToDatabase(){

        // Creating list of two contacts

        List<Contact> listOfContacts = new ArrayList<Contact>();

        Contact contact1 = new ContactBuilder()
                .buildFirstName("A")
                .buildLastName("A")
                .buildEmail("a@mail.com")
                .buildGroups(new ArrayList<String>(Arrays.asList("Family", "Gym")))
                .build();

        Contact contact2 = new ContactBuilder()
                .buildFirstName("B")
                .buildLastName("B")
                .buildEmail("b@mail.com")
                .buildGroups(new ArrayList<String>(Arrays.asList("Work", "Friends")))
                .build();

        listOfContacts.add(contact1);
        listOfContacts.add(contact2);

        System.out.println("List of Contacts to write to database: ");
        System.out.println(listOfContacts.get(0));
        System.out.println(listOfContacts.get(1));

        // Adding list of two contacts to database

        ContactRepository.addNewContactsFromList(listOfContacts);

        // Creating custom query to fetch only two test contacts from database
        // TODO redesign mechanism of creating custom queries to make it return multiple objects from database not
        // TODO only one
        String customQueryFindTestContact1 = "select c from Contact c where c.firstName = :firstName and c.lastName = :lastName";
        String key11 = "firstName";
        String value11 = contact1.getFirstName();
        String key12 = "lastName";
        String value12 = contact1.getLastName();
        Map parametersMap1 = new HashMap<String, String>();
        parametersMap1.put(key11, value11);
        parametersMap1.put(key12, value12);

        List<Contact> contact1Found = ContactRepository.listContactsByCustomQuery(customQueryFindTestContact1, parametersMap1);
        Contact contact1fromDatabase = contact1Found.get(0);

        String customQueryFindTestContact2 = "select c from Contact c where c.firstName = :firstName and c.lastName = :lastName";
        String key21 = "firstName";
        String value21 = contact2.getFirstName();
        String key22 = "lastName";
        String value22 = contact2.getLastName();
        Map parametersMap2 = new HashMap<String, String>();
        parametersMap1.put(key21, value21);
        parametersMap1.put(key22, value22);

        List<Contact> contact2Found = ContactRepository.listContactsByCustomQuery(customQueryFindTestContact2, parametersMap1);
        Contact contact2fromDatabase = contact2Found.get(0);

        List<Contact> contactsFetchedFromDatabseList = new ArrayList<Contact>();
        contactsFetchedFromDatabseList.add(contact1fromDatabase);
        contactsFetchedFromDatabseList.add(contact2fromDatabase);

        System.out.println("List of Contacts from database: ");
        System.out.println(contactsFetchedFromDatabseList.get(0));
        System.out.println(contactsFetchedFromDatabseList.get(1));

        if(listOfContacts.size() == contactsFetchedFromDatabseList.size()){

            Assert.assertEquals(listOfContacts.get(0).getFirstName(),
                    contactsFetchedFromDatabseList.get(0).getFirstName());

            Assert.assertEquals(listOfContacts.get(1).getFirstName(),
                    contactsFetchedFromDatabseList.get(1).getFirstName());

            Assert.assertEquals(listOfContacts.get(0).getLastName(),
                    contactsFetchedFromDatabseList.get(0).getLastName());

            Assert.assertEquals(listOfContacts.get(1).getLastName(),
                    contactsFetchedFromDatabseList.get(1).getLastName());

        }else{
            System.out.println("ERROR: size of list passed to database don't match size of list fetched from database");
        }

        // Delete test data from database

        ContactRepository.deleteContact(contactsFetchedFromDatabseList.get(0).getContactId());
        ContactRepository.deleteContact(contactsFetchedFromDatabseList.get(1).getContactId());


    }
}
