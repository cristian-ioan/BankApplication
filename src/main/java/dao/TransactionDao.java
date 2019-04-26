package dao;

import model.Transaction;
import org.hibernate.Session;
import util.HibernateUtil;

import javax.persistence.Query;
import java.util.List;

public class TransactionDao extends GenericDao<Transaction>{

    public void createTransaction(Transaction transaction){
        createEntity( transaction );
    }

    public List<Transaction> findAll(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM model.Transaction");

        List<Transaction> list = query.getResultList();

        transaction.commit();
        session.close();
        return list;
    }


}
