package dao;

import model.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import javax.persistence.Query;
import java.util.List;

public class PersonDao extends GenericDao<Person> {

    public List<Person> findAll(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM model.Person");

        List<Person> list = query.getResultList();

        transaction.commit();
        session.close();
        return list;
    }
}
