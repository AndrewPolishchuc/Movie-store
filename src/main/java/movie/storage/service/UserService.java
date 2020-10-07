package movie.storage.service;

import java.util.Optional;
import movie.storage.model.User;

public interface UserService {
    User add(User user);

    Optional<User> findByEmail(String email);
}
