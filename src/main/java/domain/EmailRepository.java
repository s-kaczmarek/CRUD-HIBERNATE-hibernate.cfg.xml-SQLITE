package domain;

import hibernate.utils.HibernateUtils;
import org.hibernate.Session;

public class EmailRepository {

    public static void addNewEmail(Email email){

        Session session = null;
        try {
            session = HibernateUtils.openSession();
            session.getTransaction().begin();
            session.saveOrUpdate(email);
            session.getTransaction().commit();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
