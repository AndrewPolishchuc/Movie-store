package movie.storage.service;

import movie.storage.exception.AuthenticationException;
import movie.storage.model.User;

public interface AuthenticationService {
    User login(String email, String password) throws AuthenticationException;

    User register(String email, String password);
}
