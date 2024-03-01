package riccardodiba.capstoneBack.payloads.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserDTO(@NotNull(message = "email cannot be null")
                      @Email
                      String email,
                      @NotNull(message = "name cannot be null")
                      @Size(min = 3, max = 30, message = "name must be between 3 e 30 chars")
                      String name,

                      @NotNull(message = "surname cannot be null")
                      @Size(min = 3, max = 30, message = "surname must be between 3 e 30 chars")
                      String surname,

                      @NotNull(message = "password cannot be null")
                      @Size(min = 3, max = 30, message = "password must be between 3 e 30 chars")
                      String password) {
}