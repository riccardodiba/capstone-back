package riccardodiba.capstoneBack.payloads.utente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;



public record UtenteDTO(
        @NotNull(message = "email non può essere nulla")
        @NotEmpty(message = "email Non può essere vuoto")
        @Email
        String email,
        @NotNull(message = "nome non può essere nullo")
        @NotEmpty(message = "nome Non può essere vuoto")
        @Size(min = 3, max = 30, message = "nome deve essere compreso tra 3 e 30 caratteri")
        String nome,

        @NotNull(message = "username non può essere nullo")
        @NotEmpty(message = "username Non può essere vuoto")
        @Size(min = 3, max = 30, message = "username deve essere compreso tra 3 e 30 caratteri")
        String username,
        @NotNull(message = "cognome non può essere nullo")
        @NotEmpty(message = "cognome Non può essere vuoto")
        @Size(min = 3, max = 30, message = "surname deve essere compreso tra 3 e 30 caratteri")
        String cognome,

        @NotNull(message = "password non può essere nulla")
        @NotEmpty(message = "password Non può essere vuoto")
        @Size(min = 3, max = 30, message = "password deve essere compreso tra 3 e 30 caratteri")
        String password) {




}