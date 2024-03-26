package fr.unilasalle.tp_garage_auto.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class TechnicienDTO {
    private Long id;
    private String nom;
    private String prenom;
    // Pas de liste de RendezVous pour éviter la récursivité
}
