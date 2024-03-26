package fr.unilasalle.tp_garage_auto.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class RendezVousDTO implements Serializable {
    private Long id;
    private String date;
    private String typeService;

}
