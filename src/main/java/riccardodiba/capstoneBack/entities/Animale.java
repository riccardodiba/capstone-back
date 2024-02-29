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
    private UUID uuid;
    private String nome;
    private String specie;
    @Lob
    private String descrizione;
    @Lob
    private String immagine;

    private UUID uuid_adozione;





}
