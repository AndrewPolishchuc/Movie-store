package movie.storage.model.dto;

import lombok.Data;

@Data
public class MovieSessionResponseDto {
    private Long movieSessionId;
    private Long movieId;
    private Long cinemaHallId;
    private String showTime;
}
