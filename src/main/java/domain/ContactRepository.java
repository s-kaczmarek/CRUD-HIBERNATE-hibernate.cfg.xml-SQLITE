package domain;

import hibernate.utils.HibernateUtils;
import org.hibernate.Session;

import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContactRepository {

    public static List<Contact> listAllContacts(){

        List<Contact> listContact = new ArrayList<Contact>();

        Session session = null;
        try {
            session = HibernateUtils.openSession();
            listContact = session.createQuery("SELECT c FROM Contact c").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return listContact;
    }

    public static Contact listContactById(Integer id){
        Session session = null;
        Contact contactById = null;
        try {
            session = HibernateUtils.openSession();
            try{
                contactById = session.find(Contact.class, id);
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
        return contactById;
    }

    public static List<Contact> listContactsByCustomQuery(String query, Map<String, String> map){
        Session session = null;
        List<Contact> contactsFound = new ArrayList<Contact>();
        try{
            session = HibernateUtils.openSession();
            TypedQuery<Contact> customQuery = session.createQuery(query, Contact.class);
            for (Map.Entry<String, String> entry : map.entrySet()){

                String keyParameter = entry.getKey();
                String valueParameter = entry.getValue();
                customQuery.setParameter(keyParameter, valueParameter);

            }

            contactsFound = customQuery.getResultList();

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if (session != null && session.isOpen()){
                session.close();
            }
        }
        return contactsFound;
    }

    public static void addNewContact(Contact contact){

        Session session = null;
        try {
            session = HibernateUtils.openSession();
            session.getTransaction().begin();
            session.saveOrUpdate(contact);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static void addNewContactsFromList(List<Contact> contacts){

        for(Contact contact : contacts){
            Session session = null;
            try {
                session = HibernateUtils.openSession();
                session.getTransaction().begin();
                session.save(contact);
                session.getTransaction().commit();
            }catch (Exception e){
                e.printStackTrace();
            }finally{
                if (session != null && session.isOpen()){
                    session.close();
                }
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

    public static void deleteContactsFromList(List<Contact> contacts){

        for(Contact contact : contacts){
            Session session = null;
            try {
                session = HibernateUtils.openSession();
                session.getTransaction().begin();
                session.remove(contact);
                session.getTransaction().commit();
            }catch (Exception e){
                e.printStackTrace();
            }finally{
                if (session != null && session.isOpen()){
                    session.close();
                }
            }
        }
    }
}
