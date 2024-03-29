package fr.unilasalle.tp_garage_auto.services;

import fr.unilasalle.tp_garage_auto.DTO.ClientDTO;
import fr.unilasalle.tp_garage_auto.DTO.VehiculeDTO;
import fr.unilasalle.tp_garage_auto.beans.Client;
import fr.unilasalle.tp_garage_auto.beans.Vehicule;
import fr.unilasalle.tp_garage_auto.exceptions.DBException;
import fr.unilasalle.tp_garage_auto.exceptions.DTOException;
import fr.unilasalle.tp_garage_auto.exceptions.NotFoundException;
import fr.unilasalle.tp_garage_auto.repositories.VehiculeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class VehiculeService {
    private final VehiculeRepository vehiculeRepository;

    /**
     * Get all vehicules
     * @return
     */
    public List<VehiculeDTO> getAllVehicules() {
        return vehiculeRepository.findAll().stream()
                .map(vehicule -> {
                    try {
                        return VehiculeDTO.fromEntity(vehicule);
                    } catch (DTOException e) {
                        // Gérer l'exception
                        return null;
                    }
                })
                .collect(Collectors.toList());
    }

    /**
     * Create or update a vehicule
     * @param vehiculeDTO
     * @return
     */

    @Transactional
    public VehiculeDTO createVehicule(VehiculeDTO vehiculeDTO) throws NotFoundException, DBException, DTOException {
        if (vehiculeDTO == null) {
            throw new DTOException("VehiculeDTO is null");
        }

        if (vehiculeDTO.getId() != null) {
            throw new DTOException("id must be null");
        }

        Vehicule vehicule = VehiculeDTO.toEntity(vehiculeDTO);

        try {
            vehicule = vehiculeRepository.save(vehicule);
            return VehiculeDTO.fromEntity(vehicule);
        } catch (Exception e) {
            throw new DBException("Error while saving vehicule");
        }
    }

    @Transactional
    public VehiculeDTO updateVehicule(VehiculeDTO vehiculeDTO) throws NotFoundException, DBException, DTOException {
        if (vehiculeDTO == null) {
            throw new DTOException("VehiculeDTO is null");
        }

        if (vehiculeDTO.getId() == null) {
            throw new DTOException("id must not be null");
        }

        Vehicule vehicule = VehiculeDTO.toEntity(vehiculeDTO);

        try {
            vehicule = vehiculeRepository.save(vehicule);
            return VehiculeDTO.fromEntity(vehicule);
        } catch (Exception e) {
            throw new DBException("Error while saving vehicule");
        }
    }

    /**
     * Delete a vehicule
     * @param id
     */
    @Transactional
    public void deleteVehicule(Long id) throws NotFoundException, DBException {
        Vehicule existingVehicule = vehiculeRepository.findById(id).orElse(null);
        if (existingVehicule == null) {
            throw new NotFoundException("Could not find vehicule with id " + id);
        }

        try {
            vehiculeRepository.delete(existingVehicule);
        } catch (DBException e) {
            throw new DBException("Error while deleting vehicule with id " + id);
        }
    }

}
