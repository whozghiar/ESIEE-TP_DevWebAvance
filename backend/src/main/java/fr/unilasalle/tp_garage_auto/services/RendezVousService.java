package fr.unilasalle.tp_garage_auto.services;

import fr.unilasalle.tp_garage_auto.DTO.RendezVousDTO;
import fr.unilasalle.tp_garage_auto.beans.RendezVous;
import fr.unilasalle.tp_garage_auto.beans.Technicien;
import fr.unilasalle.tp_garage_auto.beans.Vehicule;
import fr.unilasalle.tp_garage_auto.exceptions.DBException;
import fr.unilasalle.tp_garage_auto.exceptions.DTOException;
import fr.unilasalle.tp_garage_auto.exceptions.NotFoundException;
import fr.unilasalle.tp_garage_auto.repositories.RendezVousRepository;
import fr.unilasalle.tp_garage_auto.repositories.TechnicienRepository;
import fr.unilasalle.tp_garage_auto.repositories.VehiculeRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RendezVousService {

    private final RendezVousRepository rendezVousRepository;
    private final VehiculeRepository vehiculeRepository;
    private final TechnicienRepository technicienRepository;


    /**
     * Récupérer tous les rendezVous
     * @return
     */

    public List<RendezVousDTO> getAllRendezVous() {
        return rendezVousRepository.findAll().stream()
                .map(rendezVous -> {
                    try {
                        return RendezVousDTO.fromEntity(rendezVous);
                    } catch (DTOException e) {
                        // Gérer l'exception
                        return null;
                    }
                })
                .collect(Collectors.toList());
    }

    /**
     * Créer ou mettre à jour un rendezVous
     * @param rendezVousDTO
     * @return
     * @throws NotFoundException
     * @throws DBException
     */
    public RendezVousDTO updateRendezVous(RendezVousDTO rendezVousDTO) throws NotFoundException, DBException{
        return null;
    }

    /**
     * Supprimer un rendezVous
     * @param id
     * @throws NotFoundException
     * @throws DBException
     */
    public void deleteRendezVous(Long id) throws NotFoundException, DBException {
        RendezVous existingRendezVous = rendezVousRepository.findById(id).orElse(null);
        if (existingRendezVous == null) {
            throw new NotFoundException("Could not find rendezVous with id " + id);
        }

        try {
            rendezVousRepository.delete(existingRendezVous);
        } catch (DBException e) {
            throw new DBException("Error while deleting rendezVous with id " + id);
        }
    }

}
