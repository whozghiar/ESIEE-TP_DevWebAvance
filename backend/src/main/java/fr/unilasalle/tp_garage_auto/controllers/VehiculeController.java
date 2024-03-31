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
import org.springframework.web.bind.annotation.*;

import fr.unilasalle.tp_garage_auto.services.VehiculeService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/vehicule")
@RequiredArgsConstructor
@Slf4j
public class VehiculeController {

    private final VehiculeService vehiculeService;

    /**
     * Méthode GET pour récupérer tous les vehicules
     * @return
     */
    @GetMapping
    public ResponseEntity<Set<Vehicule>> getVehicule() {
        try{
            log.info("Récupération de tous les vehicules...");
            Set<Vehicule> vehicules = this.vehiculeService.getAllVehicules();
            log.info("Vehicules récupérés avec succès : \n\t" + vehicules);
            return new ResponseEntity<>(vehicules, HttpStatus.OK);
        } catch (ServiceException e) {
            log.error("Erreur lors de la récupération des vehicules.", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Méthode GET pour récupérer un vehicule par id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Vehicule> getVehiculeById(@PathVariable Long id) {
        try {
            log.info("Récupération du vehicule avec l'id " + id);
            Vehicule vehicule = this.vehiculeService.getVehiculeById(id);
            log.info("Vehicule récupéré avec succès : \n\t" + vehicule);
            return new ResponseEntity<>(vehicule, HttpStatus.OK);
        } catch (NotFoundException e) {
            log.error("Impossible de trouver le vehicule avec l'id " + id + ".", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Méthode GET pour récupérer les vehicules par id d'un client
     * @param client_id
     * @return
     */
    @GetMapping
    public ResponseEntity<Set<Vehicule>> getVehiculesByClient(@PathVariable Long client_id) {
        try {
            log.info("Récupération des vehicules du client avec l'id " + client_id);
            Set<Vehicule> vehicules = this.vehiculeService.getVehiculesByClient(client_id);
            log.info("Vehicules récupérés avec succès : \n\t" + vehicules);
            return new ResponseEntity<>(vehicules, HttpStatus.OK);
        } catch (NotFoundException e) {
            log.error("Impossible de trouver les vehicules du client avec l'id " + client_id + ".", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Méthode GET pour récupérer les vehicules par marque
     * @param marque
     * @return
     */
    @GetMapping
    public ResponseEntity<Set<Vehicule>> getVehiculeByMarque(@RequestParam String marque) {
        try {
            log.info("Récupération des vehicules de la marque " + marque);
            Set<Vehicule> vehicules = this.vehiculeService.getVehiculeByMarque(marque);
            log.info("Vehicules récupérés avec succès : \n\t" + vehicules);
            return new ResponseEntity<>(vehicules, HttpStatus.OK);
        } catch (NotFoundException e) {
            log.error("Impossible de trouver les vehicules de la marque " + marque + ".", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Méthode GET pour récupérer les vehicules par modèle
     * @param modele
     * @return
     */
    @GetMapping
    public ResponseEntity<Set<Vehicule>> getVehiculeByModele(@RequestParam String modele) {
        try {
            log.info("Récupération des vehicules du modèle " + modele);
            Set<Vehicule> vehicules = this.vehiculeService.getVehiculeByModele(modele);
            log.info("Vehicules récupérés avec succès : \n\t" + vehicules);
            return new ResponseEntity<>(vehicules, HttpStatus.OK);
        } catch (NotFoundException e) {
            log.error("Impossible de trouver les vehicules du modèle " + modele + ".", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Méthode GET pour récupérer les vehicules par année
     * @param annee
     * @return
     */
    @GetMapping
    public ResponseEntity<Set<Vehicule>> getVehiculeByAnnee(@RequestParam String annee) {
        try {
            log.info("Récupération des vehicules de l'année " + annee);
            Set<Vehicule> vehicules = this.vehiculeService.getVehiculeByAnnee(Integer.parseInt(annee));
            log.info("Vehicules récupérés avec succès : \n\t" + vehicules);
            return new ResponseEntity<>(vehicules, HttpStatus.OK);
        } catch (NotFoundException e) {
            log.error("Impossible de trouver les vehicules de l'année " + annee + ".", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (NumberFormatException e) {
            log.error("L'année doit être un nombre.", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Méthode GET pour récupérer les vehicules par immatriculation
     * @param immatriculation
     * @return
     */
    @GetMapping
    public ResponseEntity<Vehicule> getVehiculeByImmatriculation(@RequestParam String immatriculation) {
        try {
            log.info("Récupération des vehicules de l'immatriculation " + immatriculation);
            Vehicule vehicule = this.vehiculeService.getVehiculeByImmatriculation(immatriculation);
            log.info("Vehicules récupérés avec succès : \n\t" + vehicule);
            return new ResponseEntity<>(vehicule, HttpStatus.OK);
        } catch (NotFoundException e) {
            log.error("Impossible de trouver les vehicules de l'immatriculation " + immatriculation + ".", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Méthode POST pour créer un vehicule
     * @param vehicule
     * @return
     */
    @PostMapping
    public ResponseEntity<Vehicule> postVehicule(@RequestBody Vehicule vehicule) {
        try{
            log.info("Création d'un vehicule ...");
            Vehicule savedObjet = this.vehiculeService.createVehicule(vehicule);
            log.info("Vehicule créé avec succès : \n\t" + savedObjet);
            return new ResponseEntity<>(savedObjet, HttpStatus.CREATED);
        } catch (DBException e){
            log.error("Erreur lors de l'enregistrement du vehicule.", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            log.error("Impossible de trouver le vehicule avec l'id " + vehicule.getId() + ".", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DTOException e) {
            log.error("Erreur avec le DTO : ", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (ServiceException e) {
            log.error("Erreur de service : ", e);
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * Méthode PUT pour mettre à jour un vehicule
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Vehicule> putVehicule(@PathVariable Long id,@RequestBody Vehicule vehicule) {
        try{
            log.info("Mise à jour du vehicule ...");
            Vehicule savedObjet = this.vehiculeService.updateVehicule(id, vehicule);
            log.info("Vehicule mis à jour avec succès : \n\t" + savedObjet);
            return new ResponseEntity<>(savedObjet, HttpStatus.ACCEPTED);
        } catch (DBException e){
            log.error("Erreur lors de la mise à jour du vehicule.", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            log.error("Impossible de trouver le vehicule avec l'id " + vehicule.getId() + ".", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DTOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Méthode DELETE pour supprimer un vehicule
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicule(@PathVariable Long id) {
        try {
            log.info("Suppression du vehicule ...");
            this.vehiculeService.deleteVehicule(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundException e) {
            log.error("Impossible de trouver le vehicule avec l'id " + id + ".", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DBException e) {
            log.error("Erreur lors de la suppression du vehicule.", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
