package io.utils.csv;

import domain.Contact;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CsvUtilsTest {

    String path = "D:\\CLOUD\\MEGA\\Documents\\#PROGRAMOWANIE\\JAVA\\#Brudnopisy\\CRUD-HIBERNATE-hibernate.cfg.xml-SQLITE\\src\\test\\resources\\testSampleDataToLoad.csv";
    CsvUtils csvUtils = new CsvUtils();

    @Test
    public void shouldMapDataFromTestCSVtoPOJOs(){
        List<Contact> contactsReturnedFromCSVfile = csvUtils.mapCSVfileToObjectList(path);
        for(Contact contact : contactsReturnedFromCSVfile){
            System.out.println(contact);
        }
        Assert.assertEquals(contactsReturnedFromCSVfile.get(0).getFirstName(), "Jan");
        Assert.assertEquals(contactsReturnedFromCSVfile.get(1).getFirstName(), "Piotr");
        Assert.assertEquals(contactsReturnedFromCSVfile.get(0).getGroups().get(0).toString(), "Work");
    }
}
