package movie.storage.service.impl;

import java.util.Optional;
import movie.storage.dao.UserDao;
import movie.storage.lib.Inject;
import movie.storage.lib.Service;
import movie.storage.model.User;
import movie.storage.service.UserService;
import movie.storage.util.HashUtil;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;

    @Override
    public User add(User user) {
        user.setSalt(HashUtil.getSalt());
        user.setPassword(HashUtil.hashPassword(user.getPassword(), user.getSalt()));
        return userDao.add(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
