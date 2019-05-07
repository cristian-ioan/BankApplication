package com.banking.dao;

import com.banking.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.banking.util.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class UserDao extends GenericDao<User>{

    public void getUsers( ){

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            List<User> users = session.createQuery("FROM User").list();
            System.out.println(users);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Optional<User> verifyUserPassword(String username, String password){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try{
            transaction = session.beginTransaction();
            List<User> users = session.createQuery("FROM User").list();
            for(User iteratorUser : users){
                if(username.equals(iteratorUser.getUserName())&&
                        password.equals(iteratorUser.getPassword())) {
                    return Optional.of(iteratorUser);
                }
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return Optional.empty();
    }

}
