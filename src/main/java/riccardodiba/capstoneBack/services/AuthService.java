package riccardodiba.capstoneBack.services;

import org.springframework.stereotype.Service;
import riccardodiba.capstoneBack.payloads.utente.UtenteLoginDTO;

@Service

public class AuthService {
    public String autenticazioneUtente (UtenteLoginDTO body) {
        return "token";

    }
}
