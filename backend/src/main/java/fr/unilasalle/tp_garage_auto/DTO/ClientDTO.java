package fr.unilasalle.tp_garage_auto.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class ClientDTO implements Serializable {
    private Long id;
    private String nom;
    private String prenom;
    private String telephone;
    private String email;
}
