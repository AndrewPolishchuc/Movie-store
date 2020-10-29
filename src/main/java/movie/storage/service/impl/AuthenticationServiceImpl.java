package movie.storage.service.impl;

import movie.storage.exception.AuthenticationException;
import movie.storage.model.User;
import movie.storage.service.AuthenticationService;
import movie.storage.service.ShoppingCartService;
import movie.storage.service.UserService;
import movie.storage.util.HashUtil;
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
    public User login(String email, String password) throws AuthenticationException {
        User user = userService.findByEmail(email);
        if (isPasswordValid(user, password)) {
            return user;
        }
        throw new AuthenticationException("Login or password is incorrect");
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

    private boolean isPasswordValid(User user, String password) {
        return HashUtil.hashPassword(password, user.getSalt()).equals(user.getPassword());
    }
}
