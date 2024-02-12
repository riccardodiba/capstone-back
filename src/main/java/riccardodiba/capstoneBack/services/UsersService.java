package riccardodiba.capstoneBack.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import riccardodiba.capstoneBack.entities.User;
import riccardodiba.capstoneBack.exception.BadRequestException;
import riccardodiba.capstoneBack.exception.NotFoundException;
import riccardodiba.capstoneBack.payloads.utente.NewUserDTO;
import riccardodiba.capstoneBack.repositories.UsersDAO;

import java.util.Optional;
import java.util.UUID;

@Service
public class UsersService {
    @Autowired
    private UsersDAO usersDAO;


    public Page<User> getUsers(int page, int size, String orderBy) {

        if (size >= 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return usersDAO.findAll(pageable);
    }

    public User save(NewUserDTO body) {

        usersDAO.findByEmail(body.email()).ifPresent(user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " è già in uso!");
        });

        User newUser = new User();
        newUser.setSurname(body.surname());
        newUser.setName(body.name());
        newUser.setEmail(body.email());
        newUser.setPassword(body.password());
        return usersDAO.save(newUser);
    }

    public User findById(UUID id) {
        return usersDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(UUID id) {
        User found = this.findById(id);
        usersDAO.delete(found);
    }

    public User findByIdAndUpdate(UUID id, User body) {
        User found = this.findById(id);
        found.setSurname(body.getSurname());
        found.setName(body.getName());
        found.setEmail(body.getEmail());
        found.setPassword(body.getPassword());
        return usersDAO.save(found);
    }


    public User findByEmail(String email) throws NotFoundException {
        return usersDAO.findByEmail(email).orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovata!"));
    }



        }


