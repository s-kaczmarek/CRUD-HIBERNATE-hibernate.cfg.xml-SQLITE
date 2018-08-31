import domain.Contact;
import domain.ContactRepository;
import domain.Email;
import domain.Group;
import domain.utils.SampleDataUtils;
import hibernate.utils.HibernateUtils;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;
import javax.persistence.TypedQuery;
import java.util.List;

public class SampleDataTest {

    @Test
    public void shouldAddSampleDataToDataBase(){

        List<Contact> contacts = SampleDataUtils.generateTestData();

        ContactRepository.addNewContactsFromList(contacts);

        for(Contact contact : contacts){

            int contactIdToCheck = contact.getContactId();
            Assert.assertNotNull(ContactRepository.listContactById(contactIdToCheck));
            ContactRepository.deleteContact(contactIdToCheck);


        }
    }
}
