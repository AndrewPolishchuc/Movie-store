package movie.storage.service;

import movie.storage.model.User;

public interface UserService {
    User add(User user);

    User findByEmail(String email);
}
