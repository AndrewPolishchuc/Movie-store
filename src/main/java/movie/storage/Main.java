package movie.storage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import movie.storage.lib.Injector;
import movie.storage.model.CinemaHall;
import movie.storage.model.Movie;
import movie.storage.model.MovieSession;
import movie.storage.model.User;
import movie.storage.service.AuthenticationService;
import movie.storage.service.CinemaHallService;
import movie.storage.service.MovieService;
import movie.storage.service.MovieSessionService;
import movie.storage.service.ShoppingCartService;

public class Main {
    private static Injector injector = Injector.getInstance("movie.storage");

    public static void main(String[] args) {
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        Movie firstMovie = new Movie();
        firstMovie.setTitle("Fast and Furious");
        movieService.add(firstMovie);
        Movie secondMovie = new Movie();
        secondMovie.setTitle("Suits");
        movieService.add(secondMovie);
        movieService.getAll().forEach(System.out::println);
        CinemaHallService cinemaHallService
                = (CinemaHallService) injector.getInstance(CinemaHallService.class);
        CinemaHall firstCinemaHall = new CinemaHall();
        firstCinemaHall.setDescription("hall number 1");
        cinemaHallService.add(firstCinemaHall);
        CinemaHall secondCinemaHall = new CinemaHall();
        secondCinemaHall.setDescription("hall number 2");
        cinemaHallService.add(secondCinemaHall);
        cinemaHallService.getAll().forEach(System.out::println);
        MovieSession firstMovieSession = new MovieSession();
        firstMovieSession.setCinemaHall(firstCinemaHall);
        firstMovieSession.setMovie(firstMovie);
        firstMovieSession.setShowTime(LocalDateTime.of(2020, 01, 02, 10, 00));
        MovieSessionService movieSessionService
                = (MovieSessionService) injector.getInstance(MovieSessionService.class);
        movieSessionService.add(firstMovieSession);
        MovieSession secondMovieSession = new MovieSession();
        secondMovieSession.setCinemaHall(secondCinemaHall);
        secondMovieSession.setMovie(secondMovie);
        secondMovieSession.setShowTime(LocalDateTime.of(2019, 01, 15, 18, 30));
        movieSessionService.add(secondMovieSession);
        System.out.println(movieSessionService
                .findAvailableSessions(1L, LocalDate.of(2020, 01, 02)));
        System.out.println(movieSessionService
                .findAvailableSessions(2L, LocalDate.of(2019, 01, 15)));
        AuthenticationService authenticationService
                = (AuthenticationService) injector.getInstance(AuthenticationService.class);
        User alisa = new User("alisa@gmail.com", "12345");
        authenticationService.register(alisa.getEmail(), alisa.getPassword());
        ShoppingCartService shoppingCartService
                = (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
        shoppingCartService.addSession(firstMovieSession, alisa);
        shoppingCartService.addSession(secondMovieSession, alisa);
    }
}
