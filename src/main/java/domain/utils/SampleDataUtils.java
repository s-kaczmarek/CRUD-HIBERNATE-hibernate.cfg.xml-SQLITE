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

        Contact contact1 = new ContactBuilder()
                .buildFirstName("Jon1")
                .buildLastName("Doe1")
                .buildEmail("jd@mail.com")
                .buildGroups(new ArrayList<String>(Arrays.asList("Family", "Gym")))
                .build();

        Contact contact2 = new ContactBuilder()
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
