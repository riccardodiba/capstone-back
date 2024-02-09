package riccardodiba.capstoneBack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import riccardodiba.capstoneBack.payloads.utente.UtenteLoginDTO;
import riccardodiba.capstoneBack.payloads.utente.UtenteLoginResponseDTO;
import riccardodiba.capstoneBack.services.AuthService;

@RestController
@RequestMapping("/auth")

public class AuthController {
    @Autowired
    AuthService authService;
    @PostMapping("/login")
    public  UtenteLoginResponseDTO login (@RequestBody UtenteLoginDTO body){
        String accessToken = authService.autenticazioneUtente(body);
        return new UtenteLoginResponseDTO(accessToken);

    }

}
