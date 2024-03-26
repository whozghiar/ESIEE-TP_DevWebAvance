package fr.unilasalle.tp_garage_auto.DTO;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClientDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String telephone;
    private String email;

    private List<VehiculeDTO> vehicules; // liste des véhicules du client pour éviter la récursivité
}
