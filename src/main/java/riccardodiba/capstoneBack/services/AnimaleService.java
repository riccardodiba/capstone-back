package riccardodiba.capstoneBack.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import riccardodiba.capstoneBack.entities.Animale;
import riccardodiba.capstoneBack.exception.NotFoundException;
import riccardodiba.capstoneBack.payloads.animale.AnimaleDTO;
import riccardodiba.capstoneBack.repositories.AnimaleDAO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AnimaleService {

    @Autowired
    private AnimaleDAO animaleDAO;

    public Page<Animale> getAllAnimali(int page,int size,String sort) {
        Pageable pageable = PageRequest.of(page,size,Sort.by(sort));
        return animaleDAO.findAll(pageable);
    }




    public Optional<Animale> getAnimaleById(UUID id) {
        return animaleDAO.findById(id);
    }

    public Animale save(AnimaleDTO body) {
        Animale animale = new Animale();
        animale.setNome(body.nome());
        animale.setSpecie(body.specie());
        animale.setDescrizione(body.descrizione());
        animale.setImmagine(body.immagine());
        return animaleDAO.save(animale);

    }
    public Animale findById(UUID id){
    return animaleDAO.findById(id).orElseThrow(() -> new NotFoundException(id));}

    public Page<Animale> findAll(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return animaleDAO.findAll(pageable);
    }

    public void deleteById(UUID id) {
        Animale animale = animaleDAO.findById(id).orElseThrow(() -> new NotFoundException(id) );
        animaleDAO.delete(animale);
    }
    }



