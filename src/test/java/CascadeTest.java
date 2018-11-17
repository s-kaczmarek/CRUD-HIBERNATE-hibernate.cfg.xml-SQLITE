import domain.Contact;
import domain.ContactRepository;
import domain.Group;
import domain.utils.ContactBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class CascadeTest {

    // Test Data
    private String firstName = "Jonn";
    private String lastName = "Doee";
    private String email = "j.doe@email.com";
    private List<String> groups = new ArrayList(Arrays.asList("Work", "Gym"));

    @Test
    public void shouldAddCascadeEntities() {

        ContactBuilder contactBuilder = new ContactBuilder();

        Contact jonObject = null;

        Contact testContact = contactBuilder
                .buildFirstName(firstName)
                .buildLastName(lastName)
                .buildEmail(email)
                .buildGroups((ArrayList<String>) groups)
                .build();

        System.out.println("Contact built: " + testContact);

        ContactRepository.addNewContact(testContact);

        String customQueryFindJon = "select c from Contact c where c.firstName = :firstName and c.lastName = :lastName";
        String key1 = "firstName";
        String value1 = testContact.getFirstName();
        String key2 = "lastName";
        String value2 = testContact.getLastName();
        Map parametersMap = new HashMap<String, String>();
        parametersMap.put(key1, value1);
        parametersMap.put(key2, value2);

        List<Contact> contactsFound = ContactRepository.listContactsByCustomQuery(customQueryFindJon, parametersMap);

        if (contactsFound.size() == 1) {
            jonObject = contactsFound.get(0);
            Assert.assertEquals(firstName, jonObject.getFirstName());
            Assert.assertEquals(lastName, jonObject.getLastName());
        } else if (contactsFound.size() > 1) {
            System.out.println("Oh, no, We have more than one " + firstName + " " + lastName);
        } else {
            System.out.println("Unknown ERROR!!!");
        }

        int idToDelete = jonObject.getContactId();

        ContactRepository.deleteContact(idToDelete);

    }
}
