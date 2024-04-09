package fr.unilasalle.tp_garage_auto.controllers;

import fr.unilasalle.tp_garage_auto.DTO.VehiculeDTO;
import fr.unilasalle.tp_garage_auto.beans.Client;
import fr.unilasalle.tp_garage_auto.beans.Vehicule;
import fr.unilasalle.tp_garage_auto.exceptions.DBException;
import fr.unilasalle.tp_garage_auto.exceptions.DTOException;
import fr.unilasalle.tp_garage_auto.exceptions.NotFoundException;
import fr.unilasalle.tp_garage_auto.exceptions.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import fr.unilasalle.tp_garage_auto.services.VehiculeService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/vehicule")
@RequiredArgsConstructor
@Slf4j
public class VehiculeController {

    private final VehiculeService vehiculeService;

    /**
     * Méthode GET pour récupérer tous les vehicules
     * @param client_id : id du client
     * @param marque : marque du vehicule
     * @param modele : modèle du vehicule
     * @param annee : année du vehicule
     * @param immatriculation : immatriculation du vehicule
     * @return
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('admin','technicien','client')")
    public ResponseEntity<?> getVehicule(@RequestParam(required = false) Optional<String> client_id,
                                         @RequestParam(required = false) Optional<String> marque,
                                         @RequestParam(required = false) Optional<String> modele,
                                         @RequestParam(required = false) Optional<String> annee,
                                         @RequestParam(required = false) Optional<String> immatriculation) throws ServiceException {
        if(client_id.isPresent()){
            return getVehiculesByClient(Long.parseLong(client_id.get()));
        } else if (marque.isPresent()) {
            return getVehiculeByMarque(marque.get());
        } else if (modele.isPresent()) {
            return getVehiculeByModele(modele.get());
        } else if (annee.isPresent()) {
            return getVehiculeByAnnee(annee.get());
        } else if (immatriculation.isPresent()) {
            return getVehiculeByImmatriculation(immatriculation.get());
        } else {
                log.info("Récupération de tous les vehicules...");
                Set<Vehicule> vehicules = this.vehiculeService.getAllVehicules();
                log.info("Vehicules récupérés avec succès : \n\t" + vehicules);
                return new ResponseEntity<>(vehicules, HttpStatus.OK);
        }
    }

    /**
     * Méthode GET pour récupérer un vehicule par id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('admin','technicien','client')")
    public ResponseEntity<Vehicule> getVehiculeById(@PathVariable Long id) {
        log.info("Récupération du vehicule avec l'id " + id);
        Vehicule vehicule = this.vehiculeService.getVehiculeById(id);
        log.info("Vehicule récupéré avec succès : \n\t" + vehicule);
        return new ResponseEntity<>(vehicule, HttpStatus.OK);
    }

    /**
     * Récupérer les vehicules par id d'un client
     * @param client_id
     * @return
     */

    public ResponseEntity<Set<Vehicule>> getVehiculesByClient(Long client_id) {
        log.info("Récupération des vehicules du client avec l'id " + client_id);
        Set<Vehicule> vehicules = this.vehiculeService.getVehiculesByClient(client_id);
        log.info("Vehicules récupérés avec succès : \n\t" + vehicules);
        return new ResponseEntity<>(vehicules, HttpStatus.OK);
    }

    /**
     * Récupérer les vehicules par marque
     * @param marque
     * @return
     */
    public ResponseEntity<Set<Vehicule>> getVehiculeByMarque(String marque) {
        log.info("Récupération des vehicules de la marque " + marque);
        Set<Vehicule> vehicules = this.vehiculeService.getVehiculeByMarque(marque);
        log.info("Vehicules récupérés avec succès : \n\t" + vehicules);
        return new ResponseEntity<>(vehicules, HttpStatus.OK);
    }

    /**
     * Récupérer les vehicules par modèle
     * @param modele
     * @return
     */
    public ResponseEntity<Set<Vehicule>> getVehiculeByModele(String modele) {
        log.info("Récupération des vehicules du modèle " + modele);
        Set<Vehicule> vehicules = this.vehiculeService.getVehiculeByModele(modele);
        log.info("Vehicules récupérés avec succès : \n\t" + vehicules);
        return new ResponseEntity<>(vehicules, HttpStatus.OK);
    }

    /**
     * Récupérer les vehicules par année
     * @param annee
     * @return
     */
    public ResponseEntity<Set<Vehicule>> getVehiculeByAnnee(String annee) {
        log.info("Récupération des vehicules de l'année " + annee);
        Set<Vehicule> vehicules = this.vehiculeService.getVehiculeByAnnee(Integer.parseInt(annee));
        log.info("Vehicules récupérés avec succès : \n\t" + vehicules);
        return new ResponseEntity<>(vehicules, HttpStatus.OK);
    }

    /**
     * Récupérer les vehicules par immatriculation
     * @param immatriculation
     * @return
     */
    public ResponseEntity<Vehicule> getVehiculeByImmatriculation(String immatriculation) {
        log.info("Récupération des vehicules de l'immatriculation " + immatriculation);
        Vehicule vehicule = this.vehiculeService.getVehiculeByImmatriculation(immatriculation);
        log.info("Vehicules récupérés avec succès : \n\t" + vehicule);
        return new ResponseEntity<>(vehicule, HttpStatus.OK);
    }


    /**
     * Méthode PUT pour mettre à jour un vehicule
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('admin','technicien')")
    public ResponseEntity<Vehicule> putVehicule(@PathVariable Long id,@RequestBody Vehicule vehicule) {
        log.info("Mise à jour du vehicule ...");
        Vehicule savedObjet = this.vehiculeService.updateVehicule(id, vehicule);
        log.info("Vehicule mis à jour avec succès : \n\t" + savedObjet);
        return new ResponseEntity<>(savedObjet, HttpStatus.ACCEPTED);
    }

    /**
     * Méthode POST pour créer un vehicule
     * @param vehicule
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('admin','technicien')")
    public ResponseEntity<Vehicule> postVehicule(@RequestBody Vehicule vehicule) throws ServiceException {
        log.info("Création d'un vehicule ...");
        Vehicule savedObjet = this.vehiculeService.createVehicule(vehicule);
        log.info("Vehicule créé avec succès : \n\t" + savedObjet);
        return new ResponseEntity<>(savedObjet, HttpStatus.CREATED);
    }

    /**
     * Méthode DELETE pour supprimer un vehicule
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('admin','technicien')")
    public ResponseEntity<Void> deleteVehicule(@PathVariable Long id) {
        log.info("Suppression du vehicule ...");
        this.vehiculeService.deleteVehicule(id);
        log.info("Vehicule supprimé avec succès.");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
