package com.banking.dao;

import com.banking.model.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.banking.util.HibernateUtil;

import javax.persistence.Query;
import java.util.List;

public class PersonDao extends GenericDao<Person> {

    public List<Person> findAll(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM com.banking.model.Person");

        List<Person> list = query.getResultList();

        transaction.commit();
        session.close();
        return list;
    }
}
