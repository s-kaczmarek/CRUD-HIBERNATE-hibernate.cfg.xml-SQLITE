import domain.Contact;
import domain.utils.ContactBuilder;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {

    public static List<Contact> generateTestData(){

        ContactBuilder contactBuilder = new ContactBuilder();

        Contact contact1 = contactBuilder.buildFirstName("Jon").buildLastName("Doe").build();



        List<Contact> contacts = new ArrayList<Contact>();
        contacts.add(contact1);

        return contacts;

    }

    public void addSampleData(){

    }

}
