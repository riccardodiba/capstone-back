package riccardodiba.capstoneBack.repositories;
import riccardodiba.capstoneBack.entities.Animale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AnimaleDAO extends JpaRepository<Animale, Long> {

}
