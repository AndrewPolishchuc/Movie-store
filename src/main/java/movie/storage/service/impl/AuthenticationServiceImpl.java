package movie.storage.service.impl;

import java.util.Optional;
import movie.storage.exception.AuthenticationException;
import movie.storage.lib.Inject;
import movie.storage.lib.Service;
import movie.storage.model.User;
import movie.storage.service.AuthenticationService;
import movie.storage.service.ShoppingCartService;
import movie.storage.service.UserService;
import movie.storage.util.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;
    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        Optional<User> user = userService.findByEmail(email);
        if (user.isPresent() && isPasswordValid(user.get(), password)) {
            return user.get();
        }
        throw new AuthenticationException("Login or password is incorrect");
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user = userService.add(user);
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }

    private boolean isPasswordValid(User user, String password) {
        return HashUtil.hashPassword(password, user.getSalt()).equals(user.getPassword());
    }
}
