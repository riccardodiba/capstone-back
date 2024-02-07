package riccardodiba.capstoneBack.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import riccardodiba.capstoneBack.entities.Animale;
import riccardodiba.capstoneBack.exception.NotFoundException;
import riccardodiba.capstoneBack.payloads.AnimaleDTO;
import riccardodiba.capstoneBack.repositories.AnimaleDAO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AnimaleService {

    @Autowired
    private AnimaleDAO animaleDAO;

    public List<Animale> getAllAnimali() {
        return animaleDAO.findAll();
    }

    public Optional<Animale> getAnimaleById(UUID id) {
        return animaleDAO.findById(id);
    }

    public Animale save(AnimaleDTO body) {
        Animale animale = new Animale();
        animale.setNome(body.nome());
        animale.setSpecie(body.specie());
        animale.setDescrizione(body.descrizione());
        return animaleDAO.save(animale);

    }

    public void deleteById(UUID id) {
        Animale animale = animaleDAO.findById(id).orElseThrow(() -> new NotFoundException(id) );
        animaleDAO.delete(animale);
    }
    }


    
