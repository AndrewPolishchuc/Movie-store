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
    private final MovieService movieService;
    private final CinemaHallService cinemaHallService;

    @Autowired
    public MovieSessionMapper(MovieService movieService, CinemaHallService cinemaHallService) {
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
    }

    public MovieSession convertDtoToMovieSession(MovieSessionRequestDto movieSessionDto) {
        return new MovieSession(movieService.getById(movieSessionDto.getMovieId()),
                cinemaHallService.getById(movieSessionDto.getCinemaHallId()),
                LocalDateTime.parse(movieSessionDto.getShowTime(),
                        DateTimeFormatter.ofPattern("d-MM-yyyy hh:mm:ss a")));
    }

    public MovieSessionResponseDto convertMovieSessionToDto(MovieSession movieSession) {
        return new MovieSessionResponseDto(movieSession.getCinemaHall().getId(),
                movieSession.getMovie().getId(), movieSession.getShowTime().toString());
    }
}
