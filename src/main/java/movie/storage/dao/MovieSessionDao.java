package movie.storage.dao;

import java.time.LocalDate;
import java.util.List;
import movie.storage.model.MovieSession;

public interface MovieSessionDao {
    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);

    MovieSession add(MovieSession movieSession);

    MovieSession getById(Long id);
}
