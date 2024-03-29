package fr.unilasalle.tp_garage_auto.controllers;

import fr.unilasalle.tp_garage_auto.DTO.VehiculeDTO;
import fr.unilasalle.tp_garage_auto.beans.Client;
import fr.unilasalle.tp_garage_auto.beans.Vehicule;
import fr.unilasalle.tp_garage_auto.exceptions.DBException;
import fr.unilasalle.tp_garage_auto.exceptions.DTOException;
import fr.unilasalle.tp_garage_auto.exceptions.NotFoundException;
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
    public ResponseEntity<List<VehiculeDTO>> getVehicule() {
        log.info("Getting all vehicules ...");
        List<VehiculeDTO> vehicules = this.vehiculeService.getAllVehicules();
        return new ResponseEntity<>(vehicules, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<VehiculeDTO> postVehicule(@RequestBody VehiculeDTO vehiculeSent) {
        try{
            log.info("Creating vehicule ...");
            return new ResponseEntity<>(this.vehiculeService.createVehicule(vehiculeSent), HttpStatus.CREATED);
        } catch (DBException e){
            log.error("Error while creating vehicule", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            log.error("Could not find vehicule with id " + vehiculeSent.getId(), e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DTOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<VehiculeDTO> putVehicule(@RequestBody VehiculeDTO vehiculeSent) {
        try{
            log.info("Updating vehicule ...");
            return new ResponseEntity<>(this.vehiculeService.updateVehicule(vehiculeSent), HttpStatus.ACCEPTED);
        } catch (DBException e){
            log.error("Error while updating vehicule", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            log.error("Could not find vehicule with id " + vehiculeSent.getId(), e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DTOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicule(@PathVariable Long id) {
        try {
            log.info("Deleting vehicule with id " + id);
            this.vehiculeService.deleteVehicule(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundException e) {
            log.error("Could not find vehicule with id " + id, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DBException e) {
            log.error("Error while deleting vehicule with id " + id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
