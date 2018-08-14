package domain;

import hibernate.utils.HibernateUtils;
import org.hibernate.Session;

import java.util.List;

public class ContactRepository {

    public static void listAllContacts(){

        Session session = null;
        try {
            session = HibernateUtils.openSession();
            List<Contact> listContact = session.createQuery("SELECT c FROM Contact c").getResultList();

            if (listContact == null) {
                System.out.println("No employee found . ");
            } else {
                for (Contact contact : listContact) {
                    System.out.println(contact.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }


    }

    public static void listContactById(Integer id){
        Session session = null;
        try {
            session = HibernateUtils.openSession();
            try{
                Contact contactById = session.find(Contact.class, id);
                System.out.println(contactById);
            } catch (NullPointerException e){
                System.out.println("No contact with id: " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static void addNewContact(Contact contact){

        Session session = null;
        try {
            session = HibernateUtils.openSession();
            session.getTransaction().begin();
            session.saveOrUpdate(contact);
            session.getTransaction().commit();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static void editExistingContact(Contact contactToUpdate){

        Session session = null;
        try {
            session = HibernateUtils.openSession();
            session.getTransaction().begin();
            session.merge(contactToUpdate);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }

    public static void deleteContact(int idToDelete){

        Session session = null;
        try {
            session = HibernateUtils.openSession();

            Contact contactToDelete = session.find(Contact.class, idToDelete);
            System.out.println(contactToDelete);

            session.getTransaction().begin();
            session.remove(contactToDelete);
            session.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
