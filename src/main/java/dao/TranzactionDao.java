package dao;

import model.Tranzaction;
import org.hibernate.Session;
import util.HibernateUtil;

import javax.persistence.Query;
import java.util.List;

public class TranzactionDao extends GenericDao<Tranzaction>{

    public void createTransaction(Tranzaction tranzaction){
        createEntity( tranzaction );
    }

    public List<Tranzaction> findAll(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM model.Tranzaction");

        List<Tranzaction> list = query.getResultList();

        transaction.commit();
        session.close();
        return list;
    }


}
