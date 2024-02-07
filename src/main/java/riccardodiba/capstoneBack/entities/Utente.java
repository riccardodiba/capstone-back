package riccardodiba.capstoneBack.entities;



import jakarta.persistence.*;
import lombok.*;
import java.util.List;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL)
    private List<Animale> animali;

}
