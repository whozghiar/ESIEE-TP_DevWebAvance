package fr.unilasalle.tp_garage_auto.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String telephone;
    private String email;
}