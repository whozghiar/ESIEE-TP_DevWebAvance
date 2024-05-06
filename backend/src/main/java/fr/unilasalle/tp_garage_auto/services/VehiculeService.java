package fr.unilasalle.tp_garage_auto.services;

import fr.unilasalle.tp_garage_auto.beans.Client;
import fr.unilasalle.tp_garage_auto.beans.Technicien;
import fr.unilasalle.tp_garage_auto.beans.Vehicule;
import fr.unilasalle.tp_garage_auto.exceptions.DBException;
import fr.unilasalle.tp_garage_auto.exceptions.DTOException;
import fr.unilasalle.tp_garage_auto.exceptions.NotFoundException;
import fr.unilasalle.tp_garage_auto.exceptions.ServiceException;
import fr.unilasalle.tp_garage_auto.repositories.ClientRepository;
import fr.unilasalle.tp_garage_auto.repositories.VehiculeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class VehiculeService {
    private final VehiculeRepository vehiculeRepository;
    private final ClientRepository clientRepository;

    /**
     * Get all vehicules
     * @return
     */
    public Set<Vehicule> getAllVehicules() throws ServiceException {
        try {
            return new HashSet<>(vehiculeRepository.findAll());
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la récupération des vehicules.",new NullPointerException());
        }
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
            throw new NotFoundException("Impossible de trouver le vehicule avec l'id " + id + ".",new NullPointerException());
        }
        return vehicule;
    }

    /**
     * Récupère les véhicules d'un client par son id
     * @param client_id
     * @return
     */
    public Set<Vehicule> getVehiculesByClient(Long client_id) {
        Set<Vehicule> vehicules = vehiculeRepository.findByClientId(client_id);
        if(vehicules == null || vehicules.isEmpty()){
            throw new NotFoundException("Impossible de trouver de vehicules pour le client avec l'id " + client_id + ".",new NullPointerException());
        }
        return vehicules;
    }

    /**
     * Récupère les véhicules par immatriculation
     * @param immatriculation
     * @return
     */
    public Vehicule getVehiculeByImmatriculation(String immatriculation) {
        Vehicule vehicule = vehiculeRepository.findByImmatriculationIgnoreCase(immatriculation);
        if(vehicule == null){
            throw new NotFoundException("Impossible de trouver un vehicule avec l'immatriculation " + immatriculation + ".",new NullPointerException());
        }
        return vehicule;
    }

    /**
     * Récupère les véhicules par marque
     * @param marque
     * @return
     */
    public Set<Vehicule> getVehiculeByMarque(String marque) {
        Set<Vehicule> vehicules = vehiculeRepository.findByMarqueContainingIgnoreCase(marque);
        if(vehicules == null || vehicules.isEmpty()){
            throw new NotFoundException("Impossible de trouver de vehicules de la marque " + marque + ".",new NullPointerException());
        }
        return vehicules;
    }

    /**
     * Récupère les véhicules par modèle
     * @param modele
     * @return
     */
    public Set<Vehicule> getVehiculeByModele(String modele) {
        Set<Vehicule> vehicules = vehiculeRepository.findByModeleContainingIgnoreCase(modele);
        if(vehicules == null || vehicules.isEmpty()){
            throw new NotFoundException("Impossible de trouver de vehicules du modèle " + modele + ".",new NullPointerException());
        }
        return vehicules;
    }

    /**
     * Récupère les véhicules par année
     * @param annee
     * @return
     */
    public Set<Vehicule> getVehiculeByAnnee(Integer annee) {
        Set<Vehicule> vehicules = vehiculeRepository.findByAnnee(annee);
        if(vehicules == null || vehicules.isEmpty()){
            throw new NotFoundException("Impossible de trouver de vehicules de l'année " + annee + ".",new NullPointerException());
        }
        return vehicules;
    }




    /**
     * Créer un vehicule
     * @param vehicule
     * @return
     */

    @Transactional
    public Vehicule createVehicule(Vehicule vehicule) throws NotFoundException, DBException, ServiceException {
        if (vehicule == null) {
            throw new ServiceException("Le vehicule ne peut pas être null.",new NullPointerException());
        }

        if (vehicule.getId() != null) {
            throw new ServiceException("Le vehicule ne peut pas avoir d'id.",new NullPointerException());
        }

        // Si l'id du client est renseigné, on récupère le client
        if (vehicule.getClient() != null && vehicule.getClient().getId() != null) {
            vehicule.setClient(clientRepository.findById(vehicule.getClient().getId()).orElse(null));
        }

        try {
            return vehiculeRepository.save(vehicule);
        } catch (Exception e) {
            throw new DBException("Erreur lors de la création du vehicule en base.", e);
        }
    }

    /**
     * Mise à jour d'un vehicule
     * @param id
     * @param vehicule
     * @return
     * @throws NotFoundException
     * @throws DBException
     */
    @Transactional
    public Vehicule updateVehicule(Long id,Vehicule vehicule) throws NotFoundException, DBException {
        if (vehicule == null) {
            throw new NotFoundException("Le vehicule ne peut pas être null.",new NullPointerException());
        }

        if (id == null) {
            throw new NotFoundException("L'id du vehicule ne peut pas être null.",new NullPointerException());
        }

        Vehicule existingVehicule = vehiculeRepository.findById(id).orElse(null);
        if (existingVehicule == null) {
            throw new NotFoundException("Impossible de trouver le vehicule avec l'id " + vehicule.getId() + ".",new NullPointerException());
        }

        if (vehicule.getImmatriculation() != null)
            existingVehicule.setImmatriculation(vehicule.getImmatriculation());
        if (vehicule.getMarque() != null)
            existingVehicule.setMarque(vehicule.getMarque());
        if (vehicule.getModele() != null)
            existingVehicule.setModele(vehicule.getModele());
        if (vehicule.getAnnee() != null)
            existingVehicule.setAnnee(vehicule.getAnnee());

        // Si l'id du client est renseigné, on récupère le client
        if (vehicule.getClient() != null){
            // Si l'id du client est renseigné, on récupère le client
            if (vehicule.getClient().getId() != null) {
                existingVehicule.setClient(clientRepository.findById(vehicule.getClient().getId()).orElse(null));
            } else { // Sinon on met à jour le client avec les nouvelles informations
                existingVehicule.setClient(vehicule.getClient());
            }
        }else{
            existingVehicule.setClient(null);
        }

        try {
            return vehiculeRepository.save(existingVehicule);
        } catch (Exception e) {
            throw new DBException("Erreur lors de la mise à jour du vehicule en base.", e);
        }
    }

    /**
     * Supprimer un vehicule
     * @param id
     */
    @Transactional
    public void deleteVehicule(Long id) throws NotFoundException, DBException {
        Vehicule existingVehicule = vehiculeRepository.findById(id).orElse(null);
        if (existingVehicule == null) {
            throw new NotFoundException("Could not find vehicule with id " + id,new NullPointerException());
        }

        try {
            vehiculeRepository.delete(existingVehicule);
        } catch (DBException e) {
            throw new DBException("Error while deleting vehicule with id " + id, e);
        }
    }

}
