package dao;

import model.Account;
import model.Transaction;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import service.TransactionService;
import util.HibernateUtil;

import javax.persistence.Query;
import java.math.BigDecimal;
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
                                               Transaction tran){
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction transaction = null;
        TransactionService transactionService = new TransactionService();

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

            transactionService.createTransaction( tran );

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

        Query query = session.createQuery("FROM model.Account");

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
        List<Account> accountList = null;

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
