package movie.storage.service.impl;

import java.time.LocalDate;
import java.util.List;
import movie.storage.dao.MovieSessionDao;
import movie.storage.lib.Inject;
import movie.storage.lib.Service;
import movie.storage.model.MovieSession;
import movie.storage.service.MovieSessionService;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {
    @Inject
    private MovieSessionDao movieSessionDao;

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        return movieSessionDao.findAvailableSessions(movieId, date);
    }

    @Override
    public MovieSession add(MovieSession session) {
        return movieSessionDao.add(session);
    }
}
