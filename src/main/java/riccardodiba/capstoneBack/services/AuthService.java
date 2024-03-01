package riccardodiba.capstoneBack.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import riccardodiba.capstoneBack.entities.Role;
import riccardodiba.capstoneBack.entities.User;
import riccardodiba.capstoneBack.exception.BadRequestException;
import riccardodiba.capstoneBack.exception.UnauthorizedException;
import riccardodiba.capstoneBack.payloads.user.UserDTO;
import riccardodiba.capstoneBack.payloads.user.UserLoginDTO;
import riccardodiba.capstoneBack.repositories.UserDAO;
import riccardodiba.capstoneBack.security.JWTTools;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class AuthService {
    @Autowired
    private UsersService usersService;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private JWTTools jwtTools;

    public User save(UserDTO body) {
         {
            userDAO.findByEmail(body.email()).ifPresent(user -> {
                throw new BadRequestException("l' email " + user.getEmail() + " è già in uso");
            });
            User newUser = new User();
            newUser.setName(body.name());
            newUser.setSurname(body.surname());
            newUser.setRole(Role.USER);
            newUser.setEmail(body.email().toLowerCase());
            newUser.setPassword(bcrypt.encode(body.password()));
            return userDAO.save(newUser);
        }
    }


    public String authenticateUser(UserLoginDTO body) {
        User user = usersService.findByEmail(body.email());

        if (bcrypt.matches(body.password(), user.getPassword())) {
            return jwtTools.createToken(user);
        } else {
            System.out.println(body.password() + " " + bcrypt.encode(body.password()) + " " + user.getPassword());
            throw new UnauthorizedException("Credenziali non valide!");
        }
    }

}
