package riccardodiba.capstoneBack.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import riccardodiba.capstoneBack.entities.User;
import riccardodiba.capstoneBack.exception.UnauthorizedException;
import riccardodiba.capstoneBack.payloads.utente.UserLoginDTO;
import riccardodiba.capstoneBack.security.JWTTools;

@Service
public class AuthService {
    @Autowired
    private UsersService usersService;

    @Autowired
    private JWTTools jwtTools;

    public String authenticateUser(UserLoginDTO body) {

        User user = usersService.findByEmail(body.email());


        if (body.password().equals(user.getPassword())) {

            return jwtTools.createToken(user);
        } else {

            throw new UnauthorizedException("Credenziali non valide!");
        }
    }
}

