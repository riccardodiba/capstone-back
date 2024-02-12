package riccardodiba.capstoneBack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import riccardodiba.capstoneBack.payloads.utente.UserLoginDTO;
import riccardodiba.capstoneBack.payloads.utente.UserLoginResponseDTO;
import riccardodiba.capstoneBack.services.AuthService;
import riccardodiba.capstoneBack.services.UsersService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;
    @Autowired
    UsersService usersService;

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO body) {
        String accessToken = authService.authenticateUser(body);
        return new UserLoginResponseDTO(accessToken);
    }
}
