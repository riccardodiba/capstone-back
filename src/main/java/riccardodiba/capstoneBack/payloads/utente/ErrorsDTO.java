package riccardodiba.capstoneBack.payloads.utente;

import java.time.LocalDateTime;

public record ErrorsDTO(String message, LocalDateTime timestamp) {
}
