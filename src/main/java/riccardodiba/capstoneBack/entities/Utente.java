package riccardodiba.capstoneBack.entities;



import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "utenti")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Utente {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String nome;
    private String username;
    private String cognome;
    private String email;
    private String password;


    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL)
    private List<Animale> animali;
    @Enumerated(EnumType.STRING)
    private Ruoli ruoli;
}
