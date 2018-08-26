import domain.Contact;
import domain.utils.ContactBuilder;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {

    public static List<Contact> generateTestData(){

        ContactBuilder contactBuilder = new ContactBuilder();

        Contact contact1 = contactBuilder.buildFirstName("Jan").buildLastName("Kowalski").build();



        List<Contact> contacts = new ArrayList<Contact>();

        return contacts;

    }

    public void addSampleData(){

    }

}
