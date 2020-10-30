package movie.storage.model.dto;

import com.sun.istack.NotNull;
import lombok.Data;
import movie.storage.validator.EmailConstraint;
import movie.storage.validator.PasswordsValueMatch;

@Data
@PasswordsValueMatch
public class UserRequestDto {
    @EmailConstraint
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String repeatedPassword;
}
