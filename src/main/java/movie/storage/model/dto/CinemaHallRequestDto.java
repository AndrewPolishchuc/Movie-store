package movie.storage.model.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class CinemaHallRequestDto {
    @NonNull
    private Integer capacity;
    private String description;
}
