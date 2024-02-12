package riccardodiba.capstoneBack.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "animali")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Animale {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID uuid;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String specie;

    @Column(nullable = false)
    private String descrizione;

    @ManyToOne
    @JoinColumn(name = "USING utente_id::bigint", nullable = false)
    private User user;


}
