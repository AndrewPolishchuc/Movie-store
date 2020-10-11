package movie.storage.service;

import movie.storage.model.MovieSession;
import movie.storage.model.ShoppingCart;
import movie.storage.model.User;

public interface ShoppingCartService {
    void addSession(MovieSession movieSession, User user);

    ShoppingCart getByUser(User user);

    void registerNewShoppingCart(User user);

    void clear(ShoppingCart shoppingCart);
}
