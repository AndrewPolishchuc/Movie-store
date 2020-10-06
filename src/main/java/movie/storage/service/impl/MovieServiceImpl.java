package movie.storage.service.impl;

import java.util.List;
import movie.storage.dao.MovieDao;
import movie.storage.lib.Inject;
import movie.storage.lib.Service;
import movie.storage.model.Movie;
import movie.storage.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
    @Inject
    private MovieDao movieDao;

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }
}
