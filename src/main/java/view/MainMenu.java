package view;

import org.hibernate.SessionFactory;
import service.UserService;
import util.HibernateUtil;

import java.util.logging.Logger;

public class MainMenu {

    private UserService userService;
    private SessionFactory sessionFactory;
    private final static Logger LOG = Logger.getLogger(Logger.class.getName());

    public MainMenu(){
        userService = new UserService();
    }

    /**
     * Creates a sessionFactory and starts the application.
     */
    public void showMainMenu() {

        try {
            sessionFactory = HibernateUtil.getSessionFactory();
            userService.goAhead();
        } catch (Throwable error) {
            LOG.severe("Failed to create sessionFactory object." + error);
            throw new ExceptionInInitializerError(error);
        } finally {
            sessionFactory.close();
        }
    }
}
