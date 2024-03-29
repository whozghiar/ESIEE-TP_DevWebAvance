package fr.unilasalle.tp_garage_auto.services;

import fr.unilasalle.tp_garage_auto.DTO.ClientDTO;
import fr.unilasalle.tp_garage_auto.DTO.TechnicienDTO;
import fr.unilasalle.tp_garage_auto.beans.Technicien;
import fr.unilasalle.tp_garage_auto.exceptions.DBException;
import fr.unilasalle.tp_garage_auto.exceptions.DTOException;
import fr.unilasalle.tp_garage_auto.exceptions.NotFoundException;
import fr.unilasalle.tp_garage_auto.repositories.TechnicienRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TechnicienService {
    private final TechnicienRepository technicienRepository;

    /**
     * Récupérer tous les techniciens
     * @return
     */
    public List<TechnicienDTO> getAllTechniciens() {
        return technicienRepository.findAll().stream()
                .map(technicien -> {
                    try {
                        return TechnicienDTO.fromEntity(technicien);
                    } catch (DTOException e) {
                        // Gérer l'exception
                        return null;
                    }
                })
                .collect(Collectors.toList());
    }

    /**
     * Créer ou mettre à jour un technicien
     * @param technicienDTO
     * @return
     */
    @Transactional
    public TechnicienDTO  updateTechnicien(TechnicienDTO  technicienDTO) throws NotFoundException, DBException, DTOException {
        Technicien technicien = TechnicienDTO.toEntity(technicienDTO);
        technicien = technicienRepository.save(technicien);
        return TechnicienDTO.fromEntity(technicien);
    }


    /**
     * Supprimer un technicien
     * @param id
     * @throws NotFoundException
     * @throws DBException
     */
    @Transactional
    public void deleteTechnicien(Long id) throws NotFoundException, DBException {
        Technicien existingTechnicien = technicienRepository.findById(id).orElse(null);
        if (existingTechnicien == null) {
            throw new NotFoundException("Could not find technicien with id " + id);
        }

        try {
            technicienRepository.delete(existingTechnicien);
        } catch (DBException e) {
            throw new DBException("Error while deleting technicien with id " + id);
        }
    }
}
