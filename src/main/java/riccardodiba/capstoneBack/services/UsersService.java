package riccardodiba.capstoneBack.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import riccardodiba.capstoneBack.entities.User;
import riccardodiba.capstoneBack.exception.NotFoundException;
import riccardodiba.capstoneBack.repositories.UserDAO;


import java.util.UUID;

@Service
public class UsersService {
    @Autowired
    private UserDAO userDAO;

    public Page<User> getUsers(int page, int size, String orderBy) {
        if (size >= 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));

        return userDAO.findAll(pageable);
    }


    public User findById(UUID id) {
        return userDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(UUID id) {
        User found = this.findById(id);
        userDAO.delete(found);
    }

    public User findByIdAndUpdate(UUID id, User body) {
        User found = this.findById(id);
        found.setSurname(body.getSurname());
        found.setName(body.getName());
        found.setEmail(body.getEmail());
        found.setPassword(body.getPassword());
        return userDAO.save(found);
    }


    public User findByEmail(String email) throws NotFoundException {
        return userDAO.findByEmail(email).orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovata!"));
    }


}
