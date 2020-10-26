package movie.storage.service.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import movie.storage.model.MovieSession;
import movie.storage.model.dto.MovieSessionRequestDto;
import movie.storage.model.dto.MovieSessionResponseDto;
import movie.storage.service.CinemaHallService;
import movie.storage.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionMapper {
    public static final String DATE_FORMAT = "d-MM-yyyy hh:mm:ss a";
    private final MovieService movieService;
    private final CinemaHallService cinemaHallService;

    @Autowired
    public MovieSessionMapper(MovieService movieService, CinemaHallService cinemaHallService) {
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
    }

    public MovieSession convertDtoToMovieSession(MovieSessionRequestDto movieSessionDto) {
        MovieSession movieSession = new MovieSession();
        movieSession.setCinemaHall(cinemaHallService.getById(movieSessionDto.getCinemaHallId()));
        movieSession.setMovie(movieService.getById(movieSessionDto.getMovieId()));
        movieSession.setShowTime(LocalDateTime.parse(movieSessionDto.getShowTime(),
                DateTimeFormatter.ofPattern(DATE_FORMAT)));
        return movieSession;
    }

    public MovieSessionResponseDto convertMovieSessionToDto(MovieSession movieSession) {
        MovieSessionResponseDto movieSessionResponseDto = new MovieSessionResponseDto();
        movieSessionResponseDto.setMovieSessionId(movieSession.getId());
        movieSessionResponseDto.setMovieId(movieSession.getMovie().getId());
        movieSessionResponseDto.setCinemaHallId(movieSession.getCinemaHall().getId());
        movieSessionResponseDto.setShowTime(movieSession.getShowTime().toString());
        return movieSessionResponseDto;
    }
}
