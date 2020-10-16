package movie.storage.dao.impl;

import movie.storage.dao.ShoppingCartDao;
import movie.storage.exception.DataProcessingException;
import movie.storage.lib.Dao;
import movie.storage.model.ShoppingCart;
import movie.storage.model.User;
import movie.storage.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    private static final Logger logger = Logger.getLogger(ShoppingCartDaoImpl.class);

    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {
        logger.info("Add shopping cart");
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(shoppingCart);
            transaction.commit();
            logger.info("Shopping cart added: " + shoppingCart);
            return shoppingCart;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Unable to add new shopping cart - "
                    + shoppingCart, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    @Override
    public ShoppingCart getByUser(User user) {
        logger.info("Get user shopping cart");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from ShoppingCart sc "
                    + "left join fetch sc.tickets "
                    + "join fetch sc.user "
                    + "where sc.user = :user", ShoppingCart.class)
            .setParameter("user", user)
            .getSingleResult();
        } catch (Exception e) {
            throw new DataProcessingException("Unable to get shopping cart of user - "
                    + user, e);
        }
    }

    @Override
    public void update(ShoppingCart shoppingCart) {
        logger.info("Update shopping cart");
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(shoppingCart);
            logger.info("Shopping cart updated");
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Unable to update shoppingCart " + shoppingCart
                    + shoppingCart, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
