package movie.storage.service;

import movie.storage.model.User;

public interface AuthenticationService {
    User register(String email, String password);

    User register(User user);
}
