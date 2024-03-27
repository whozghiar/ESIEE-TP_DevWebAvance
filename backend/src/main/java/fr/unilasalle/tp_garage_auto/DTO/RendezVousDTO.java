package fr.unilasalle.tp_garage_auto.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.unilasalle.tp_garage_auto.beans.RendezVous;
import fr.unilasalle.tp_garage_auto.exceptions.DTOException;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class RendezVousDTO {
    private Long id;
    private String date;
    private String typeService;

    private VehiculeDTO vehicule; // le véhicule concerné par le rendez-vous

    private TechnicienDTO technicien; // le technicien qui prend en charge le rendez-vous

    public static RendezVousDTO fromEntity(RendezVous rendezVous) throws DTOException {
        // Si le rendez-vous est null, on throw une exception
        if(rendezVous == null){
            throw new DTOException("Le rendez-vous ne peut pas être null");
        }

        // On crée un nouveau rendez-vousDTO
        return RendezVousDTO.builder()
                .id(rendezVous.getId())
                .date(rendezVous.getDate())
                .typeService(rendezVous.getTypeService())
                .vehicule(VehiculeDTO.fromEntity(rendezVous.getVehicule()))
                .technicien(TechnicienDTO.fromEntity(rendezVous.getTechnicien()))
                .build();

    }

    public static RendezVous toEntity(RendezVousDTO rendezVousDTO) throws DTOException {
        // Si le rendez-vousDTO est null, on retourne null
        if(rendezVousDTO == null){
            throw new DTOException("Le rendez-vousDTO ne peut pas être null");
        }

        // On crée un nouveau rendez-vous
        return RendezVous.builder()
                .id(rendezVousDTO.getId())
                .date(rendezVousDTO.getDate())
                .typeService(rendezVousDTO.getTypeService())
                .vehicule(VehiculeDTO.toEntity(rendezVousDTO.getVehicule()))
                .technicien(TechnicienDTO.toEntity(rendezVousDTO.getTechnicien()))
                .build();
    }


}
