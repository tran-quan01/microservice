package quantv.indentityservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {

    @Size(min = 3, message = "USER_VALIDATION")
    String username;

    @Size(min = 8, max = 16, message = "USER_PASSWORD_INVALID")
    String password;

    @NotBlank(message="firstName is not blank")
    String firstName;

    @NotBlank(message="lastName is not blank")
    String lastName;
    LocalDate birthDate;

    List<String> roles;
}
