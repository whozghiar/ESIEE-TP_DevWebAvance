package fr.unilasalle.tp_garage_auto.controllers;

import fr.unilasalle.tp_garage_auto.DTO.RendezVousDTO;
import fr.unilasalle.tp_garage_auto.beans.Client;
import fr.unilasalle.tp_garage_auto.beans.RendezVous;
import fr.unilasalle.tp_garage_auto.exceptions.DBException;
import fr.unilasalle.tp_garage_auto.exceptions.NotFoundException;
import fr.unilasalle.tp_garage_auto.services.ClientService;
import fr.unilasalle.tp_garage_auto.services.RendezVousService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rendez-vous")
@RequiredArgsConstructor
@Slf4j
public class RendezVousController {

    private final RendezVousService rendezVousService;

    @GetMapping
    public ResponseEntity<List<RendezVousDTO>> getRendezVous() {
        log.info("Getting all rendez-vous ...");
        List<RendezVousDTO> rendezvous = this.rendezVousService.getAllRendezVous();
        return new ResponseEntity<>(rendezvous, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RendezVousDTO> postRendezVous(@RequestBody RendezVousDTO rendezVousSent) {
        try{
            log.info("Creating rendez-vous ...");
            return rendezVousSent.getId() == null ?
                    new ResponseEntity<>(this.rendezVousService.updateRendezVous(rendezVousSent), HttpStatus.CREATED) :
                    new ResponseEntity<>(this.rendezVousService.updateRendezVous(rendezVousSent), HttpStatus.ACCEPTED);
        } catch (DBException e){
            log.error("Error while creating rendez-vous", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            log.error("Could not find rendez-vous with id " + rendezVousSent.getId(), e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRendezVous(@PathVariable Long id) {
        try {
            log.info("Deleting rendez-vous with id " + id);
            this.rendezVousService.deleteRendezVous(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundException e) {
            log.error("Could not find rendez-vous with id " + id, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DBException e) {
            log.error("Error while deleting rendez-vous with id " + id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
