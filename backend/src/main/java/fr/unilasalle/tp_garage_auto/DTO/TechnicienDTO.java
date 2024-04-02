package fr.unilasalle.tp_garage_auto.DTO;

import fr.unilasalle.tp_garage_auto.beans.Technicien;
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
public class TechnicienDTO {
    private Long id;
    private String nom;
    private String prenom;

    public static TechnicienDTO fromEntity(Technicien technicien) throws DTOException {
        if(technicien == null){
            throw new DTOException("Le technicien ne peut pas être null",new NullPointerException());
        }

        return TechnicienDTO.builder()
                .id(technicien.getId())
                .nom(technicien.getNom())
                .prenom(technicien.getPrenom())
                .build();
    }

    public static Technicien toEntity(TechnicienDTO technicienDTO) throws DTOException {
        if(technicienDTO == null){
            throw new DTOException("Le technicienDTO ne peut pas être null",new NullPointerException());
        }

        return Technicien.builder()
                .id(technicienDTO.getId())
                .nom(technicienDTO.getNom())
                .prenom(technicienDTO.getPrenom())
                .build();
    }


}
