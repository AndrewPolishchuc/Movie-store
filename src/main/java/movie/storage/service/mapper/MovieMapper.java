package movie.storage.service.mapper;

import movie.storage.model.Movie;
import movie.storage.model.dto.MovieRequestDto;
import movie.storage.model.dto.MovieResponseDto;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {
    public Movie convertDtoToMovie(MovieRequestDto movieRequestDto) {
        return new Movie(movieRequestDto.getDescription(), movieRequestDto.getTitle());
    }

    public MovieResponseDto convertMovieToDto(Movie movie) {
        return new MovieResponseDto(movie.getId(), movie.getTitle(), movie.getDescription());
    }
}
