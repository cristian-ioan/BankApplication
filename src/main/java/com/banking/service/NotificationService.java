package com.banking.service;

import com.banking.dao.NotificationDao;
import com.banking.model.Notification;

import java.util.List;

public class NotificationService {

    private NotificationDao notificationDao = new NotificationDao();

    public void createNotification(Notification notification){
        notificationDao.createEntity( notification );
    }

    public Notification findById(Long id){
        return notificationDao.getEntityById(Notification.class, id);
    }

    public List<Notification> findAll(){

        return notificationDao.findAll();
    }
}
