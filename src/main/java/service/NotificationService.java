package service;

import dao.NotificationDao;
import model.Notification;

import java.util.List;

public class NotificationService {

    private NotificationDao notificationDao = new NotificationDao();

    public Notification findById(Long id){
        return notificationDao.getEntityById(Notification.class, id);
    }

    public List<Notification> findAll(){

        return notificationDao.findAll();
    }
}
