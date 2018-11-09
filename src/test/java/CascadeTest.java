import domain.Contact;
import domain.ContactRepository;
import domain.utils.ContactBuilder;
import org.junit.Assert;
import org.junit.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CascadeTest {

    private String firstName = "Jon";
    private String lastName = "Doe";

    @Test
    public void shouldAddCascadeEntities() {

        ContactBuilder contactBuilder = new ContactBuilder();

        Contact jonObject = null;

        Contact testContact = contactBuilder
                .buildFirstName(firstName)
                .buildLastName(lastName)
                .buildEmail("j.doe@email.com")
                .buildGroups("work", "gym")
                .build();

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
            Assert.assertEquals("Jon", jonObject.getFirstName());
            Assert.assertEquals("Doe", jonObject.getLastName());
        } else if (contactsFound.size() > 1) {
            System.out.println("Oh, o, We have more than one " + firstName + " " + lastName);
        } else {
            System.out.println("Unknown ERROR!!!");
        }

        int idToDelete = jonObject.getContactId();

        ContactRepository.deleteContact(idToDelete);

    }
}
