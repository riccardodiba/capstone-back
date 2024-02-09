package riccardodiba.capstoneBack.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import riccardodiba.capstoneBack.entities.Utente;
import riccardodiba.capstoneBack.repositories.UtenteDAO;

import java.util.List;
import java.util.Optional;


@Service
public class UtenteService {

    @Autowired
    private UtenteDAO utenteDAO;

    public List<Utente> getAllUtenti() {
        return utenteDAO.findAll();
    }

    public Optional<Utente> getUtenteById(Long id) {
        return utenteDAO.findById(id);
    }

    public Utente saveUtente(Utente utente) {
        return utenteDAO.save(utente);
    }

    public void deleteUtente(Long id) {
        utenteDAO.deleteById(id);
    }
}
