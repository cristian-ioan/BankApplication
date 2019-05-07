package com.banking.service;

import com.banking.dao.PersonDao;
import com.banking.model.Person;

import java.util.List;

public class PersonService {

    private PersonDao personDao = new PersonDao();

    public Person findById(Long id){
        return personDao.getEntityById(Person.class, id);
    }

    public List<Person> findAll(){

        return personDao.findAll();
    }
}
