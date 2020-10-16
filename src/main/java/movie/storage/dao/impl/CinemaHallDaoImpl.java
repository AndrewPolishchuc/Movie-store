package movie.storage.dao.impl;

import java.util.List;
import movie.storage.dao.CinemaHallDao;
import movie.storage.exception.DataProcessingException;
import movie.storage.lib.Dao;
import movie.storage.model.CinemaHall;
import movie.storage.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class CinemaHallDaoImpl implements CinemaHallDao {
    private static final Logger logger = Logger.getLogger(CinemaHallDaoImpl.class);

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        logger.info("Add cinema hall");
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(cinemaHall);
            transaction.commit();
            logger.info("Cinema hall added: " + cinemaHall);
            return cinemaHall;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Unable to add new cinema hall" + cinemaHall, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    @Override
    public List<CinemaHall> getAll() {
        logger.info("Get all cinema halls");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<CinemaHall> getAllCinemaHalls
                    = session.createQuery("from CinemaHall", CinemaHall.class);
            return getAllCinemaHalls.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Unable to get all cinema halls", e);
        }
    }
}
