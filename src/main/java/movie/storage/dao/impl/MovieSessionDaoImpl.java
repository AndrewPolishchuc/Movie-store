package movie.storage.dao.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import movie.storage.dao.MovieSessionDao;
import movie.storage.exception.DataProcessingException;
import movie.storage.lib.Dao;
import movie.storage.model.MovieSession;
import movie.storage.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class MovieSessionDaoImpl implements MovieSessionDao {
    private static final Logger logger = Logger.getLogger(MovieSessionDaoImpl.class);

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        logger.info("Find available session");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<MovieSession> query = session.createQuery(
                    "FROM MovieSession WHERE movie_id = :movieId "
                            + "AND showTime BETWEEN :startTime AND :endTime",
                    MovieSession.class);
            query.setParameter("movieId", movieId);
            query.setParameter("startTime", date.atStartOfDay());
            query.setParameter("endTime", date.atTime(LocalTime.MAX));
            return query.getResultList();
        }
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(movieSession);
            transaction.commit();
            logger.info("Movie session added: " + movieSession);
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Unable to add new movie session - "
                    + movieSession, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
