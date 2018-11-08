package domain.utils;

import domain.Contact;
import domain.ContactRepository;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static domain.utils.IOutils.loadContactsFromCSV;

public class IOutilsTest {

    private static String filePath = "D:\\CLOUD\\MEGA\\Documents\\#PROGRAMOWANIE\\JAVA\\Brudnopisy\\CRUD-HIBERNATE-hibernate.cfg.xml-SQLITE\\test.csv";

    @Test
    public void shouldAddAllRecordsToCSV() throws Exception {

        List<Contact> contactsFromDatabase = ContactRepository.listAllContacts();

        if(contactsFromDatabase == null){
            contactsFromDatabase = SampleDataUtils.generateTestData();
            ContactRepository.addNewContactsFromList(contactsFromDatabase);
        }

        IOutils.saveAllContactsToCSV(filePath);
        List<String> contactsFromCSV = IOutils.loadContactsFromCSV(filePath);
        System.out.println("List of contacts: " + contactsFromCSV);
        Assert.assertEquals(contactsFromDatabase, contactsFromCSV);

    }

    // TODO
    public static void main(String[] args) {

        try {
            IOutils.saveAllContactsToCSV(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
