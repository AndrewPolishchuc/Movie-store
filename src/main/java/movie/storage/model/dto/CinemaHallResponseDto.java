package movie.storage.model.dto;

import lombok.Data;

@Data
public class CinemaHallResponseDto {
    private Long id;
    private Integer capacity;
    private String description;
}
