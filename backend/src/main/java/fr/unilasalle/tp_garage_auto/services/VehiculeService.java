package fr.unilasalle.tp_garage_auto.services;

import fr.unilasalle.tp_garage_auto.beans.Vehicule;
import fr.unilasalle.tp_garage_auto.exceptions.DBException;
import fr.unilasalle.tp_garage_auto.exceptions.NotFoundException;
import fr.unilasalle.tp_garage_auto.repositories.VehiculeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
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
     * Create or update a vehicule
     * @param vehicule
     * @return
     */
    public Vehicule updateVehicule(Vehicule vehicule) throws NotFoundException, DBException {
        Vehicule existingVehicule = null;

        if (vehicule.getId() != null) {
            // Update an existing vehicule
            existingVehicule = vehiculeRepository.findById(vehicule.getId()).orElse(null);
            if (existingVehicule == null) {
                throw new NotFoundException("Could not find vehicule with id " + vehicule.getId());
            }
        } else {
            existingVehicule = new Vehicule();
        }

        existingVehicule.setMarque(vehicule.getMarque());
        existingVehicule.setModele(vehicule.getModele());
        existingVehicule.setAnnee(vehicule.getAnnee());
        existingVehicule.setClient(vehicule.getClient());
        existingVehicule.setImmatriculation(vehicule.getImmatriculation());
        existingVehicule.setRendezVous(vehicule.getRendezVous());

        try {
            return vehiculeRepository.save(existingVehicule);
        } catch (DBException e) {
            throw new DBException("Error while updating vehicule");
        }
    }

    /**
     * Delete a vehicule
     * @param id
     */
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