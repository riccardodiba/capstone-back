package riccardodiba.capstoneBack.repositories;
import riccardodiba.capstoneBack.entities.Animale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface AnimaleDAO extends JpaRepository<Animale, UUID> {

    Optional<Animale> findById(UUID uuid);
}
