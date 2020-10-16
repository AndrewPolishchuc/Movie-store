package movie.storage.service;

import java.util.List;
import movie.storage.model.Order;
import movie.storage.model.Ticket;
import movie.storage.model.User;

public interface OrderService {
    Order completeOrder(List<Ticket> tickets, User user);

    List<Order> getOrderHistory(User user);
}
