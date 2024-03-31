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

@RestController
@RequestMapping("/vehicule")
@RequiredArgsConstructor
@Slf4j
public class VehiculeController {

    private final VehiculeService vehiculeService;

    @GetMapping
    public ResponseEntity<List<Vehicule>> getVehicule() {
        log.info("Récupération de tous les vehicules...");
        List<Vehicule> vehicules = this.vehiculeService.getAllVehicules();
        return new ResponseEntity<>(vehicules, HttpStatus.OK);
    }

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
