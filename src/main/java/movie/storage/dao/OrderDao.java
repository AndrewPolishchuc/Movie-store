package movie.storage.dao;

import java.util.List;
import movie.storage.model.Order;
import movie.storage.model.User;

public interface OrderDao {
    Order add(Order order);

    List<Order> getOrderHistory(User user);
}
