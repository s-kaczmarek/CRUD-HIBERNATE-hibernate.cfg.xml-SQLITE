package domain;

import hibernate.utils.HibernateUtils;
import org.hibernate.Session;

public class GroupRepository {

    public static void addNewGroup(Group group){

        Session session = null;
        try {
            session = HibernateUtils.openSession();
            session.getTransaction().begin();
            session.saveOrUpdate(group);
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
