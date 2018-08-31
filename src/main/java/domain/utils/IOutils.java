package domain.utils;

import domain.Contact;
import domain.ContactRepository;
import domain.utils.helpers.CSVHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class IOutils {

    public static void saveAllContactsToCSV() throws Exception {

        List<Contact> allContactsList = null;
        allContactsList = ContactRepository.listAllContacts();

        for(Contact contact : allContactsList){
            List<String> stringListToAdd = new ArrayList<String>();
            stringListToAdd.add(String.valueOf(contact.getContactId()));
            stringListToAdd.add(contact.getFirstName());
            stringListToAdd.add(contact.getLastName());
            //stringListToAdd.add(String.valueOf(contact.getEmail()));
            //stringListToAdd.add(String.valueOf(contact.getGroups()));

            System.out.println(stringListToAdd);

            String filePathWindows = "D:\\CLOUD\\MEGA\\Documents\\#PROGRAMOWANIE\\JAVA\\Brudnopisy\\CRUD-HIBERNATE-hibernate.cfg.xml-SQLITE\\test.csv";

            File csvFile = new File(filePathWindows);
            FileOutputStream fos = new FileOutputStream(csvFile, true);
            Writer fw = new OutputStreamWriter(fos, "UTF-8");
            CSVHelper.writeLine(fw, stringListToAdd);
            fw.flush();
            fw.close();

            // TODO wyciągląc grupy z listy contacts i dodać osobno do listy stringListToAdd
        }


    }

    public static void loadCSVtoDatabase(String pathToFile){


    }
}
