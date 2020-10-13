package movie.storage.dao.impl;

import java.util.List;
import movie.storage.dao.OrderDao;
import movie.storage.exception.DataProcessingException;
import movie.storage.lib.Dao;
import movie.storage.model.Order;
import movie.storage.model.User;
import movie.storage.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class OrderDaoImpl implements OrderDao {
    @Override
    public Order add(Order order) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
            return order;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Unable to create order" + order, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("select distinct o from Order o "
                    + "left join fetch o.tickets "
                    + "join fetch o.user "
                    + "where o.user = :user", Order.class)
                    .setParameter("user", user)
                    .getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Unable to get orders of user " + user, e);
        }
    }
}
