package fr.unilasalle.tp_garage_auto.DTO;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VehiculeDTO {
    private Long id;
    private String marque;
    private String modele;
    private String immatriculation;
    private int annee;
    // Pas de champ pour Client pour éviter la récursivité
}