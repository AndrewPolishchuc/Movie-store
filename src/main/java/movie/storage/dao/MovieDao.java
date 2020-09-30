package movie.storage.dao;

import java.util.List;
import movie.storage.model.Movie;

public interface MovieDao {
    Movie add(Movie movie);

    List<Movie> getAll();
}
