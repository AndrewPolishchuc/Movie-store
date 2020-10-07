package movie.storage.dao;

import java.util.Optional;
import movie.storage.model.User;

public interface UserDao {
    User add(User user);

    Optional<User> findByEmail(String email);
}
