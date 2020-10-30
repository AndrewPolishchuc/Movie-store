package movie.storage.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import movie.storage.model.dto.UserRequestDto;

public class PasswordsValueMatchValidator
        implements ConstraintValidator<PasswordsValueMatch, UserRequestDto> {

    @Override
    public boolean isValid(UserRequestDto userRequestDto,
                           ConstraintValidatorContext constraintValidatorContext) {
        String fieldValue = userRequestDto.getPassword();
        String fieldMatchValue = userRequestDto.getRepeatedPassword();
        return fieldValue != null && fieldValue.equals(fieldMatchValue);
    }
}
