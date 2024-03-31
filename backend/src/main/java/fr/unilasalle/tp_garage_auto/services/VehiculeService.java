package fr.unilasalle.tp_garage_auto.services;

import fr.unilasalle.tp_garage_auto.DTO.ClientDTO;
import fr.unilasalle.tp_garage_auto.DTO.VehiculeDTO;
import fr.unilasalle.tp_garage_auto.beans.Client;
import fr.unilasalle.tp_garage_auto.beans.Technicien;
import fr.unilasalle.tp_garage_auto.beans.Vehicule;
import fr.unilasalle.tp_garage_auto.exceptions.DBException;
import fr.unilasalle.tp_garage_auto.exceptions.DTOException;
import fr.unilasalle.tp_garage_auto.exceptions.NotFoundException;
import fr.unilasalle.tp_garage_auto.exceptions.ServiceException;
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
    public List<Vehicule> getAllVehicules() {
        return vehiculeRepository.findAll();
    }

    /**
     * Get a vehicule by id
     * @param id
     * @return
     * @throws NotFoundException
     * @throws DTOException
     */
    public Vehicule getVehiculeById(Long id) throws NotFoundException {
        Vehicule vehicule = vehiculeRepository.findById(id).orElse(null);
        if (vehicule == null) {
            throw new NotFoundException("Impossible de trouver le vehicule avec l'id " + id + ".");
        }
        return vehicule;
    }

    /**
     * Create or update a vehicule
     * @param vehicule
     * @return
     */

    @Transactional
    public Vehicule createVehicule(Vehicule vehicule) throws NotFoundException, DBException, ServiceException {
        if (vehicule == null) {
            throw new ServiceException("Le vehicule ne peut pas être null.");
        }

        if (vehicule.getId() != null) {
            throw new ServiceException("Le vehicule ne peut pas avoir d'id.");
        }

        try {
            return vehiculeRepository.save(vehicule);
        } catch (Exception e) {
            throw new DBException("Erreur lors de la création du vehicule en base.", e);
        }
    }

    @Transactional
    public Vehicule updateVehicule(Vehicule vehicule) throws NotFoundException, DBException {
        if (vehicule == null) {
            throw new NotFoundException("Le vehicule ne peut pas être null.");
        }

        if (vehicule.getId() == null) {
            throw new NotFoundException("Le vehicule doit avoir un id.");
        }

        Vehicule existingVehicule = vehiculeRepository.findById(vehicule.getId()).orElse(null);

        if (existingVehicule == null) {
            throw new NotFoundException("Impossible de trouver le vehicule avec l'id " + vehicule.getId() + ".");
        }

        try {
            return vehiculeRepository.save(vehicule);
        } catch (Exception e) {
            throw new DBException("Erreur lors de la mise à jour du vehicule en base.", e);
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
            throw new DBException("Error while deleting vehicule with id " + id, e);
        }
    }

}
