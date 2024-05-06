package fr.unilasalle.tp_garage_auto.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RendezVousDTO {
    private Long id;
    private String date;
    private String typeService;
    private Long vehiculeId;
    private Long technicienId;
}