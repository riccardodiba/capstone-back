package riccardodiba.capstoneBack.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import riccardodiba.capstoneBack.entities.Ruoli;
import riccardodiba.capstoneBack.entities.User;
import riccardodiba.capstoneBack.exception.BadRequestException;
import riccardodiba.capstoneBack.exception.UnauthorizedException;
import riccardodiba.capstoneBack.payloads.utente.NewUserDTO;
import riccardodiba.capstoneBack.payloads.utente.UserLoginDTO;
import riccardodiba.capstoneBack.repositories.UsersDAO;
import riccardodiba.capstoneBack.security.JWTTools;

@Service
public class AuthService {
    @Autowired
    private UsersService usersService;

    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private JWTTools jwtTools;

    public String authenticateUser(UserLoginDTO body) {

        User user = usersService.findByEmail(body.email());


        if (bcrypt.matches(body.password(), user.getPassword())) {

            return jwtTools.createToken(user);
        } else {

            throw new UnauthorizedException("Credenziali non valide!");
        }
    }

    public User save(NewUserDTO body) {

        usersDAO.findByEmail(body.email()).ifPresent(user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " è già in uso!");
        });

        User newUser = new User();
        newUser.setSurname(body.surname());
        newUser.setName(body.name());
        newUser.setEmail(body.email());

        newUser.setPassword(bcrypt.encode(body.password()));
        newUser.setRuoli(Ruoli.USER);
        return usersDAO.save(newUser);
    }

}
