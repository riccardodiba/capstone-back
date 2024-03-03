package riccardodiba.capstoneBack.payloads.user;


import java.util.UUID;

public record UserLoginResponseDTO(String token, UUID my_uuid) {
}