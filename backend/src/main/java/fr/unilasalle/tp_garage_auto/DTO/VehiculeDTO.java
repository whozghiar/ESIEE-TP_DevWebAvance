package fr.unilasalle.tp_garage_auto.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class VehiculeDTO implements Serializable {
    private Long id;
    private String marque;
    private String modele;
    private String immatriculation;
    private int annee;

    private Long client_id;
}
