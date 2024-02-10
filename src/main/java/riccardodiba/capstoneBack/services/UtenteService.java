package riccardodiba.capstoneBack.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import riccardodiba.capstoneBack.entities.Ruoli;
import riccardodiba.capstoneBack.entities.Utente;
import riccardodiba.capstoneBack.exception.BadRequestException;
import riccardodiba.capstoneBack.exception.NotFoundException;
import riccardodiba.capstoneBack.payloads.utente.UtenteDTO;
import riccardodiba.capstoneBack.repositories.UtenteDAO;


import java.util.UUID;


@Service
public class UtenteService {

    @Autowired
    private static UtenteDAO utenteDAO;

    public Page<Utente> getUsers(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return utenteDAO.findAll(pageable);
    }
    public Utente findById(UUID uuid) throws NotFoundException {
        return utenteDAO.findById(uuid).orElseThrow(() -> new NotFoundException(uuid));
    }
    public  static Utente findByEmail(String email) {
        return utenteDAO.findByEmail(email).orElseThrow(() -> new NotFoundException("Utente con email" + email + " non trovato!"));
    }

    public void deleteById(UUID id) {
        Utente user = this.findById(id);
        utenteDAO.delete(user);
    }

    public Utente findByIdAndUpdate(UUID id, UtenteDTO body) {
        Utente utente = this.findById(id);
        utente.setEmail(body.email());
        utente.setNome(body.nome());
        utente.setCognome(body.cognome());
        return utenteDAO.save(utente);
    }

    public Utente setAdmin(UUID id) {
        Utente utente = utenteDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
        utente.setRuoli(Ruoli.ADMIN);
        return utenteDAO.save(utente);
    }
    public Utente setUser(UUID id) {
        Utente utente = utenteDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
        utente.setRuoli(Ruoli.UTENTE);
        return utenteDAO.save(utente);
    }


}

