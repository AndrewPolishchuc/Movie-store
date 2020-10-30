package movie.storage.model.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class MovieRequestDto {
    @NonNull
    private String title;
    @NonNull
    private String description;
}
