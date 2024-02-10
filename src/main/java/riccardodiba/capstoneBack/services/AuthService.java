package riccardodiba.capstoneBack.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import riccardodiba.capstoneBack.entities.Utente;
import riccardodiba.capstoneBack.exception.UnauthorizedException;
import riccardodiba.capstoneBack.payloads.utente.UtenteLoginDTO;
import riccardodiba.capstoneBack.security.JWTTools;

@Service

public class AuthService {
    @Autowired
    private JWTTools jwtTools;
    public String autenticazioneUtente (UtenteLoginDTO body) {
        Utente utente = UtenteService.findByEmail(body.email());

        if (body.password().equals(utente.getPassword())) {
            // 3. Se le credenziali sono OK --> Genere un token JWT e lo ritorno
            return jwtTools.createToken(utente);
        } else {
            // 4. Se le credenziali NON sono OK --> 401 (Unauthorized)
            throw new UnauthorizedException("Credenziali non valide!");
        }


    }
}
