package movie.storage.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
        order.setOrderDate(LocalDateTime.now());
        order.setUser(user);
        order.setTickets(new ArrayList<>(tickets));
        shoppingCartService.clear(shoppingCartService.getByUser(user));
        return orderDao.add(order);
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        return orderDao.getOrderHistory(user);
    }
}
