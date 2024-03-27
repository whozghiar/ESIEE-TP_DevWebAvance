package fr.unilasalle.tp_garage_auto.DTO;

import fr.unilasalle.tp_garage_auto.beans.Vehicule;
import fr.unilasalle.tp_garage_auto.exceptions.DTOException;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Builder
public class VehiculeDTO {
    private Long id;
    private String marque;
    private String modele;
    private String immatriculation;
    private int annee;
    // Pas de champ pour Client pour éviter la récursivité

    public static VehiculeDTO fromEntity(Vehicule vehicule) throws DTOException {
        // Si le véhicule est null, on throw une exception
        if(vehicule == null){
            throw new DTOException("Le véhicule ne peut pas être null");
        }

        // On crée un nouveau véhiculeDTO
        return VehiculeDTO.builder()
                .id(vehicule.getId())
                .marque(vehicule.getMarque())
                .modele(vehicule.getModele())
                .immatriculation(vehicule.getImmatriculation())
                .annee(vehicule.getAnnee())
                .build();

    }

    public static Vehicule toEntity(VehiculeDTO vehiculeDTO) throws DTOException {
        // Si le véhiculeDTO est null, on retourne null
        if(vehiculeDTO == null){
            throw new DTOException("Le véhiculeDTO ne peut pas être null");
        }

        // On crée un nouveau véhicule
        return Vehicule.builder()
                .id(vehiculeDTO.getId())
                .marque(vehiculeDTO.getMarque())
                .modele(vehiculeDTO.getModele())
                .immatriculation(vehiculeDTO.getImmatriculation())
                .annee(vehiculeDTO.getAnnee())
                .build();
    }
}