package controller;

import domain.*;
import domain.utils.ContactBuilder;
import domain.utils.comparators.ComparatorContactByName;
import view.View;

import java.util.*;

public class Controller {

    Scanner scanner = new Scanner(System.in);
    View view = new View();

    static class InnerUtils{

        protected static void contactListIterationLoop(List<Contact> contactsList){

            if (contactsList == null) {
                System.out.println("No contact found. ");
            } else {
                for (Contact contact : contactsList) {
                    System.out.println(contact.toString());
                }
            }
        }

        private static Contact createContact(List<List<String>> basicDataAndGroupsList){

            ContactBuilder contactBuilder = new ContactBuilder();

            String firstName = basicDataAndGroupsList.get(0).get(0);
            String lastName = basicDataAndGroupsList.get(0).get(1);
            String email_ = basicDataAndGroupsList.get(0).get(2);

            List<String> groups = basicDataAndGroupsList.get(1);

            Contact contact = contactBuilder
                    .buildFirstName(firstName)
                    .buildLastName(lastName)
                    .buildEmail(email_)
                    .buildGroups((ArrayList<String>) groups)
                    .build();

            return contact;
        }
    }

    public void getAllContacts(){

        List<Contact> allContacts = ContactRepository.listAllContacts();

        if (allContacts == null) {
            System.out.println("No contact found. ");
        } else {
            for (Contact contact : allContacts) {
                System.out.println(contact.toString());
            }
        }
    }

    public void getAllContactsSortedByName(String param){

        List<Contact> allContacts = ContactRepository.listAllContacts();
        Comparator comparatorContactByName = new ComparatorContactByName();
        Collections.sort(allContacts, comparatorContactByName);

        if(param == "asc"){

            InnerUtils.contactListIterationLoop(allContacts);
            Collections.reverse(allContacts);

        }else if(param == "desc"){

            Collections.reverse(allContacts);
            InnerUtils.contactListIterationLoop(allContacts);
        }
    }

    public void getContactById(){
        Contact contactFound = null;
        System.out.println("Type contact id:");
        int id = scanner.nextInt();
        contactFound = ContactRepository.listContactById(id);
        if(contactFound != null){
            System.out.println(contactFound);
        }else{
            System.out.println("No contact with id: " + id);
        }
    }

    public void addNewContact(){
        ContactRepository.addNewContact(Controller.InnerUtils.createContact(view.newContactCreator()));
    }

    // TODO
    public void updateContact(){
        System.out.println("Update Contact");
        System.out.println("==============");
        System.out.println("");

        System.out.println("Type contact id: ");
        int idToUpdate = scanner.nextInt();
        ContactRepository.listContactById(idToUpdate);
        System.out.println("First Name: ");
        String firstName = scanner.next();
        System.out.println("Last Name: ");
        String lastName = scanner.next();
        Contact contactToUpdate = new Contact(firstName, lastName);
        contactToUpdate.setContactId(idToUpdate);
        ContactRepository.editExistingContact(contactToUpdate);

    }

    // TODO
    public void deleteContact(){
        System.out.println("Delete Contact");
        System.out.println("==============");
        System.out.println("");

        System.out.println("Type contact id: ");
        int idToDelete = scanner.nextInt();
        ContactRepository.deleteContact(idToDelete);
    }

    // TODO
    public void dropTables(){
    }
}
