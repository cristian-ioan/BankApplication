package com.banking.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory factory;
    private HibernateUtil() {
    }

    public static synchronized SessionFactory getSessionFactory() {

        if (factory == null) {
            factory = new Configuration().configure("hibernate.cfg.xml").
                    buildSessionFactory();
        }
        return factory;
    }
}
