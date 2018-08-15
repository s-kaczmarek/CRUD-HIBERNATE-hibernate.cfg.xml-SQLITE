import domain.Contact;
import domain.ContactRepository;
import domain.Email;
import domain.Group;
import hibernate.utils.HibernateUtils;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;
import javax.persistence.TypedQuery;
import java.util.List;

public class CascadeTest {

    @Test
    public void shouldAddCascadeEntities(){

        String firstName = "Jon";
        String lastName = "Doe";

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
            foundJon.setParameter("firstName", firstName);
            foundJon.setParameter("lastName", lastName);

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
