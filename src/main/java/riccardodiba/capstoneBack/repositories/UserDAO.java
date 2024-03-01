package riccardodiba.capstoneBack.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import riccardodiba.capstoneBack.entities.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserDAO extends JpaRepository<User, UUID> {
    public Optional<User> findByEmail(String email);
}