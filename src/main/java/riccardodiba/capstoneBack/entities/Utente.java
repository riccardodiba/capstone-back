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

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String cognome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL)
    private List<Animale> animali;

    @Enumerated(EnumType.STRING)
    private Ruoli ruoli;
}
