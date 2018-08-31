import domain.Contact;
import domain.ContactRepository;
import domain.Email;
import domain.Group;
import domain.utils.ContactBuilder;
import hibernate.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.boot.jaxb.SourceType;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
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

        String customQueryFindJon = "select c from Contact c where c.firstName = :studentName and c.lastName = :lastName";
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

        @Test
        public void shouldAddCascadeEntitiesOld(){

            String firstName_ = "Jon";
            String lastName_ = "Doe";

            Contact contact = new Contact(firstName, lastName);
            Email email = new Email();
            contact.setEmail(email);
            Group group1 = new Group();
            Group group2 = new Group();
            contact.getGroups().add(group1);
            contact.getGroups().add(group2);
            group1.getContacts().add(contact);
            group2.getContacts().add(contact);

            ContactRepository.addNewContact(contact);


            Session session = null;

            try {
                session = HibernateUtils.openSession();

                String customQueryFindJon = "select c from Contact c where c.firstName = :studentName and c.lastName = :lastName";
                TypedQuery<Contact> foundJon = session.createQuery(customQueryFindJon, Contact.class);
                foundJon.setParameter("firstName", firstName_);
                foundJon.setParameter("lastName", lastName_);


                List<Contact> results = foundJon.getResultList();

                Contact jonObject = results.get(0);

                int idToDelete = jonObject.getContactId();

                Assert.assertNotEquals(null, foundJon);

                ContactRepository.deleteContact(idToDelete);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }

        }

}
