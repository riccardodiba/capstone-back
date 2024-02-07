package riccardodiba.capstoneBack.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import riccardodiba.capstoneBack.entities.Animale;
import riccardodiba.capstoneBack.repositories.AnimaleDAO;

import java.util.List;
import java.util.Optional;

@Service
public class AnimaleService {

    @Autowired
    private AnimaleDAO animaleDAO;

    public List<Animale> getAllAnimali() {
        return animaleDAO.findAll();
    }

    public Optional<Animale> getAnimaleById(Long id) {
        return animaleDAO.findById(id);
    }

    public Animale saveAnimale(Animale animale) {
        return animaleDAO.save(animale);
    }

    public void deleteAnimale(Long id) {
        animaleDAO.deleteById(id);
    }
}
