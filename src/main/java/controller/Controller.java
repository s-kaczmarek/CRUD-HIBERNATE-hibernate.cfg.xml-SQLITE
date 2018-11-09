package controller;

import domain.*;
import domain.utils.comparators.ComparatorContactByName;

import java.util.*;

public class Controller {

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
    }

    Scanner scanner = new Scanner(System.in);

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

    // TODO
    public void addNewContact(){
        System.out.println("Add New Contact");
        System.out.println("===============");
        System.out.println("");

        System.out.println("First Name: ");
        String firstName = scanner.next();
        System.out.println("Last Name: ");
        String lastName = scanner.next();

        Contact contact = new Contact(firstName, lastName);

        System.out.println("Email: ");
        String email_ = scanner.next();

        Email email = new Email(email_);
        contact.setEmail(email);

        boolean groupRun = true;
        do{
            System.out.println("Group name: ");
            String groupName = scanner.next();
            Group group = new Group(groupName);
            contact.getGroups().add(group);
            group.getContacts().add(contact);
            //GroupRepository.addNewGroup(group);

            System.out.println("Would you like to add another group? y/n");
            String choice = scanner.next();
            switch (choice.charAt(0)){
                case 'y':
                    break;
                case 'n':
                    groupRun = false;
                    break;
            }

        }while(groupRun);

        ContactRepository.addNewContact(contact);
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

    public void dropTables(){
    }
}
