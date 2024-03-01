package riccardodiba.capstoneBack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import riccardodiba.capstoneBack.entities.User;
import riccardodiba.capstoneBack.exception.BadRequestException;
import riccardodiba.capstoneBack.payloads.user.UserDTO;
import riccardodiba.capstoneBack.payloads.user.UserLoginDTO;
import riccardodiba.capstoneBack.payloads.user.UserLoginResponseDTO;
import riccardodiba.capstoneBack.payloads.user.UserResponseDTO;
import riccardodiba.capstoneBack.services.AuthService;
import riccardodiba.capstoneBack.services.UsersService;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UsersService usersService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO body) {
        String accessToken = authService.authenticateUser(body);
        return new UserLoginResponseDTO(accessToken);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin(origins = "http://localhost:3000")
    public UserResponseDTO createUser(@RequestBody @Validated UserDTO newUserPayload, BindingResult validation) {


        if (validation.hasErrors()) {

            throw new BadRequestException("Ci sono errori nel payload!");
        } else {

            User newUser = authService.save(newUserPayload);

            return new UserResponseDTO(newUser.getId());
        }
    }
}