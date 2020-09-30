package movie.storage.util;

import movie.storage.exception.IncorrectDataHandlingException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory = initSessionFactory();

    private HibernateUtil() {
    }

    private static SessionFactory initSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {
            throw new IncorrectDataHandlingException("Failed to build SessionFactory", e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}