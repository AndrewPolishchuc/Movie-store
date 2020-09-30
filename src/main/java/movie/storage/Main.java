package movie.storage;

import movie.storage.lib.Injector;
import movie.storage.model.Movie;
import movie.storage.service.MovieService;

public class Main {
    private static Injector injector = Injector.getInstance("movie.storage");

    public static void main(String[] args) {
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        movieService.add(movie);
        movieService.getAll().forEach(System.out::println);
    }
}
