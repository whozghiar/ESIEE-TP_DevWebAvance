package fr.unilasalle.tp_garage_auto.DTO;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RendezVousDTO {
    private Long id;
    private String date;
    private String typeService;
    private Long vehiculeId; // l'ID du véhicule
    private Long technicienId; // l'ID du technicien
}
