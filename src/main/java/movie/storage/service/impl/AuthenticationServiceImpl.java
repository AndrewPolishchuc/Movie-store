package movie.storage.service.impl;

import movie.storage.model.User;
import movie.storage.service.AuthenticationService;
import movie.storage.service.ShoppingCartService;
import movie.storage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;

    @Autowired
    public AuthenticationServiceImpl(UserService userService,
                                     ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        return register(user);
    }

    @Override
    public User register(User user) {
        userService.add(user);
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }
}
