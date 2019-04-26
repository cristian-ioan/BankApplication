package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class GenericDao<T> {

    public void createEntity(T entity) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.persist(entity);

        transaction.commit();
        session.close();
    }
    public T getEntityById(Class<T> clazz, Long id){

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        T entity = session.get(clazz, id);

        transaction.commit();

        return entity;
    }

    public T updateEntity(T entity){

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        T entityToReturn = (T) session.merge(entity);

        transaction.commit();
        session.close();
        return entityToReturn;
    }

    public void deleteEntity(T entity){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(entity);

        transaction.commit();
        session.close();
    }

}
