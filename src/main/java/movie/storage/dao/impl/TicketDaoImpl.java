package movie.storage.dao.impl;

import movie.storage.dao.TicketDao;
import movie.storage.exception.DataProcessingException;
import movie.storage.lib.Dao;
import movie.storage.model.Ticket;
import movie.storage.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class TicketDaoImpl implements TicketDao {
    @Override
    public Ticket add(Ticket ticket) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(ticket);
            transaction.commit();
            return ticket;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Unable to add ticket - " + ticket, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
