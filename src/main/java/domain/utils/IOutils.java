package domain.utils;

import domain.Contact;
import domain.ContactRepository;
import domain.utils.helpers.CSVHelper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOutils {

    public static void saveAllContactsToCSV(String filePath) throws Exception {

        List<Contact> allContactsList = ContactRepository.listAllContacts();

        for(Contact contact : allContactsList){
            List<String> stringListToAdd = new ArrayList<String>();
            stringListToAdd.add(String.valueOf(contact.getContactId()));
            stringListToAdd.add(contact.getFirstName());
            stringListToAdd.add(contact.getLastName());
            stringListToAdd.add(String.valueOf(contact.getEmail().getEmail()));
            stringListToAdd.add(String.valueOf(contact.getGroups()));

            //System.out.println(stringListToAdd);

            //String filePathWindows = "D:\\CLOUD\\MEGA\\Documents\\#PROGRAMOWANIE\\JAVA\\Brudnopisy\\CRUD-HIBERNATE-hibernate.cfg.xml-SQLITE\\test.csv";

            File csvFile = new File(filePath);
            FileOutputStream fos = new FileOutputStream(csvFile, true);
            Writer fw = new OutputStreamWriter(fos, "UTF-8");
            CSVHelper.writeLine(fw, stringListToAdd);
            fw.flush();
            fw.close();

            // TODO wyciągląc grupy z listy contacts i dodać osobno do listy stringListToAdd
        }
    }

    public static List<String> loadContactsFromCSV(String filePath) throws Exception {

        List<String> allStringsFromCSV;
        Reader fr = new FileReader(filePath);
        allStringsFromCSV = CSVHelper.parseLine(fr);

        return allStringsFromCSV;

    }
}
