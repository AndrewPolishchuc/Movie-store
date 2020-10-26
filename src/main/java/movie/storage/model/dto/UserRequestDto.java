package movie.storage.model.dto;

import lombok.Data;

@Data
public class UserRequestDto {
    private String email;
    private String password;
}
