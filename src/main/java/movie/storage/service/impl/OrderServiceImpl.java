package movie.storage.service.impl;

import java.util.List;
import movie.storage.dao.OrderDao;
import movie.storage.lib.Inject;
import movie.storage.lib.Service;
import movie.storage.model.Order;
import movie.storage.model.Ticket;
import movie.storage.model.User;
import movie.storage.service.OrderService;
import movie.storage.service.ShoppingCartService;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;
    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public Order completeOrder(List<Ticket> tickets, User user) {
        Order order = new Order();
        order.setUser(user);
        order.setTickets(tickets);
        shoppingCartService.clear(shoppingCartService.getByUser(user));
        return orderDao.add(order);
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        return orderDao.getOrderHistory(user);
    }
}
