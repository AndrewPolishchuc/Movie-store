package movie.storage.model.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class MovieSessionRequestDto {
    @NonNull
    private Long movieId;
    @NonNull
    private Long cinemaHallId;
    @NonNull
    private String showTime;
}
