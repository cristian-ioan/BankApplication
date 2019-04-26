package dao;

import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

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

    public Optional<User> verifyUserPassword(User user){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try{
            transaction = session.beginTransaction();
            List<User> users = session.createQuery("FROM User").list();
            for(User iteratorUser : users){
                if(user.getUserName().equals(iteratorUser.getUserName())&&
                        user.getPassword().equals(iteratorUser.getPassword())) {
                    user.setId(iteratorUser.getId());
                    return Optional.of(user);
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
