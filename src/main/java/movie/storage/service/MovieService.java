package movie.storage.service;

import java.util.List;
import movie.storage.model.Movie;

public interface MovieService {
    Movie add(Movie movie);

    List<Movie> getAll();

    Movie getById(Long id);
}
