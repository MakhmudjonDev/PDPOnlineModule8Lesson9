package app.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserDTO {
    @NotBlank(message = "username.notblank")
    String username;
    @NotBlank(message = "password.notblank")
    String password;
    @NotBlank(message = "confirm.notblank")
    String confirmPassword;
}
