package domain.utils;

import domain.Contact;
import domain.ContactRepository;
import domain.utils.ContactBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
*   This class is used to generate hardcoded test data
*
* */


public class SampleDataUtils {

    public static List<Contact> generateTestData(){

        ContactBuilder contactBuilder = new ContactBuilder();

        Contact contact1 = contactBuilder
                .buildFirstName("Jon")
                .buildLastName("Doe")
                .buildEmail("jd@mail.com")
                .buildGroups(new ArrayList<String>(Arrays.asList("Family", "Gym")))
                .build();

        Contact contact2 = contactBuilder
                .buildFirstName("Jon2")
                .buildLastName("Doe2")
                .buildEmail("jd2@mail.com")
                .buildGroups(new ArrayList<String>(Arrays.asList("Work", "Friends")))
                .build();

        List<Contact> contacts = new ArrayList<Contact>();
        contacts.add(contact1);
        contacts.add(contact2);

        return contacts;
    }
}
