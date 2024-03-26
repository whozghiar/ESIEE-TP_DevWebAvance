package fr.unilasalle.tp_garage_auto.controllers;

import fr.unilasalle.tp_garage_auto.beans.RendezVous;
import fr.unilasalle.tp_garage_auto.beans.Technicien;
import fr.unilasalle.tp_garage_auto.exceptions.DBException;
import fr.unilasalle.tp_garage_auto.exceptions.NotFoundException;
import fr.unilasalle.tp_garage_auto.services.TechnicienService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/technicien")
@RequiredArgsConstructor
@Slf4j
public class TechnicienController {
    private final TechnicienService technicienService;

    @GetMapping
    public ResponseEntity<List<Technicien>> getTechnicien() {
        log.info("Getting all techniciens ...");
        List<Technicien> techniciens = this.technicienService.getAllTechniciens();
        return new ResponseEntity<>(techniciens, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Technicien> postTechnicien(@RequestBody Technicien technicienSent) {
        try{
            log.info("Creating technicien ...");
            return technicienSent.getId() == null ?
                    new ResponseEntity<>(this.technicienService.updateTechnicien(technicienSent), HttpStatus.CREATED) :
                    new ResponseEntity<>(this.technicienService.updateTechnicien(technicienSent), HttpStatus.ACCEPTED);
        } catch (DBException e){
            log.error("Error while creating technicien", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            log.error("Could not find technicien with id " + technicienSent.getId(), e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTechnicien(@PathVariable Long id) {
        try {
            log.info("Deleting technicien with id " + id);
            this.technicienService.deleteTechnicien(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundException e) {
            log.error("Could not find technicien with id " + id, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DBException e) {
            log.error("Error while deleting technicien with id " + id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
