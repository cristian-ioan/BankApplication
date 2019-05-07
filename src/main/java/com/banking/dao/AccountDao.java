package com.banking.dao;

import com.banking.model.Account;
import com.banking.model.Notification;
import com.banking.model.Transaction;
import com.banking.service.NotificationService;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import com.banking.service.TransactionService;
import com.banking.util.HibernateUtil;

import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AccountDao extends GenericDao<Account> {

    /**
     * Creates a new account.
     */
    public void createAccount(Account account){
        createEntity(account);
    }

    /**
     * Updates accounts and creates a transaction.
     */
    public void updateAccountcreateTransaction(List<Account> accounts, long indexFirstAccount, long secondAccount,
                                               BigDecimal balanceOfFirstAccount, BigDecimal balanceOfSecondAccount,
                                               Transaction tranIncoming, Transaction tranOutgoing, Notification notification){
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction transaction = null;
        TransactionService transactionService = new TransactionService();
        NotificationService notificationService = new NotificationService();

        try{
            transaction = session.beginTransaction();

            for (Account account : accounts) {
                if (account.getId() == indexFirstAccount) {
                    account.setBalance( balanceOfFirstAccount );
                    updateAccount( account );
                }
                if (account.getId() == secondAccount) {
                    account.setBalance( balanceOfSecondAccount );
                    updateAccount( account );
                }
            }

            transactionService.createTransaction( tranIncoming );
            transactionService.createTransaction( tranOutgoing );

            notificationService.createNotification( notification );

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

    public List<Account> findAll(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM com.banking.model.Account");

        List<Account> accountList = query.getResultList();

        transaction.commit();
        session.close();
        return accountList;
    }

    /**
     * Finds all accounts for logged user.
     *
     * @return list of accounts
     */
    public List<Account> findAllAccountsById(long id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction transaction = null;
        List<Account> accountList = new ArrayList<>();

        try{
            transaction = session.beginTransaction();

            String hql = "FROM Account WHERE user_id = :id";
            accountList = session.createQuery(hql).setParameter("id", id).list();

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return accountList;
    }

    public void updateAccount(Account account){
        updateEntity( account );
    }
}
