package movie.storage.service;

import java.time.LocalDate;
import java.util.List;
import movie.storage.model.MovieSession;

public interface MovieSessionService {
    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);

    MovieSession add(MovieSession session);
}
