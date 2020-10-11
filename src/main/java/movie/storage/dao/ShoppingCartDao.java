package movie.storage.dao;

import movie.storage.model.ShoppingCart;
import movie.storage.model.User;

public interface ShoppingCartDao {
    ShoppingCart add(ShoppingCart shoppingCart);

    ShoppingCart getByUser(User user);

    void update(ShoppingCart shoppingCart);
}
