package fr.unilasalle.tp_garage_auto.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RendezVousDTO {
    private Long id;
    private String date;
    private String typeService;
    private Long vehiculeId; // l'ID du véhicule
    private Long technicienId; // l'ID du technicien
}
