package riccardodiba.capstoneBack.payloads.animale;


import java.util.UUID;

public record AnimaleDTO(String nome, String specie, String descrizione, String immagine, UUID uuid_adozione) {
}