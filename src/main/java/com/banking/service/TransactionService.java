package com.banking.service;

import com.banking.dao.TransactionDao;
import com.banking.model.Transaction;

import java.util.List;

public class TransactionService {

    private TransactionDao transactionDao = new TransactionDao();

    public Transaction findById(Long id){
        return transactionDao.getEntityById( Transaction.class, id);
    }

    public List<Transaction> findAll(){

        return transactionDao.findAll();
    }

    public void createTransaction(Transaction transaction){
        transactionDao.createTransaction( transaction );
    }
}
