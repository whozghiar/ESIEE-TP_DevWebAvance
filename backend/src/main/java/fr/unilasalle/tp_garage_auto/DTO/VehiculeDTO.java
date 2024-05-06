package fr.unilasalle.tp_garage_auto.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehiculeDTO {
    private Long id;
    private String marque;
    private String modele;
    private String immatriculation;
    private Integer annee;
    private Long clientId;
}