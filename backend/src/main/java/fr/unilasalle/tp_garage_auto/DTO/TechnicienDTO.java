package fr.unilasalle.tp_garage_auto.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class TechnicienDTO implements Serializable {
    private Long id;
    private String nom;
    private String prenom;
}
