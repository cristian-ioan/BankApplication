package service;

import dao.TranzactionDao;
import model.Tranzaction;

import java.util.List;

public class TranzactionService {

    private TranzactionDao tranzactionDao = new TranzactionDao();

    public Tranzaction findById(Long id){
        return tranzactionDao.getEntityById( Tranzaction.class, id);
    }

    public List<Tranzaction> findAll(){

        return tranzactionDao.findAll();
    }

    public void createTransaction(Tranzaction tranzaction){
        tranzactionDao.createTransaction( tranzaction );
    }
}
