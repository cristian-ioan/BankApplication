package dao;

import model.Notification;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import javax.persistence.Query;
import java.util.List;

public class NotificationDao extends GenericDao<Notification> {

    public List<Notification> findAll(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM model.Notification");

        List<Notification> list = query.getResultList();

        transaction.commit();
        session.close();
        return list;
    }
}
