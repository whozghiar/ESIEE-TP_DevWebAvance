package fr.unilasalle.tp_garage_auto.DTO;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TechnicienDTO {
    private Long id;
    private String nom;
    private String prenom;
    // Pas de liste de RendezVous pour éviter la récursivité
}
