package fr.unilasalle.tp_garage_auto.controllers;

import fr.unilasalle.tp_garage_auto.DTO.RendezVousDTO;
import fr.unilasalle.tp_garage_auto.beans.Client;
import fr.unilasalle.tp_garage_auto.beans.RendezVous;
import fr.unilasalle.tp_garage_auto.exceptions.DBException;
import fr.unilasalle.tp_garage_auto.exceptions.DTOException;
import fr.unilasalle.tp_garage_auto.exceptions.NotFoundException;
import fr.unilasalle.tp_garage_auto.exceptions.ServiceException;
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
    public ResponseEntity<List<RendezVous>> getRendezVous() {
        log.info("Récupération de tous les rendez-vous...");
        List<RendezVous> rendezvous = this.rendezVousService.getAllRendezVous();
        return new ResponseEntity<>(rendezvous, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RendezVous> postRendezVous(@RequestBody RendezVous rendezVous) {
        try{
            log.info("Création d'un rendez-vous ...");
            RendezVous savedObjet = this.rendezVousService.createRendezVous(rendezVous);
            log.info("Rendez-vous créé avec succès : \n\t" + savedObjet);
            return new ResponseEntity<>(savedObjet, HttpStatus.CREATED);
        } catch (DBException e){
            log.error("Erreur lors de l'enregistrement du rendez-vous.", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            log.error("Impossible de trouver le rendez-vous avec l'id " + rendezVous.getId() + ".", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DTOException e) {
            log.error("Erreur avec le DTO : ", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (ServiceException e) {
            log.error("Erreur de service : ", e);
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping
    public ResponseEntity<RendezVous> putRendezVous(@RequestBody RendezVous rendezVous) {
        try{
            log.info("Mise à jour du rendez-vous ...");
            RendezVous savedObjet = this.rendezVousService.updateRendezVous(rendezVous);
            log.info("Rendez-vous mis à jour avec succès : \n\t" + savedObjet);
            return new ResponseEntity<>(savedObjet, HttpStatus.ACCEPTED);
        } catch (DBException e){
            log.error("Erreur lors de la mise à jour du rendez-vous.", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            log.error("Impossible de trouver le rendez-vous avec l'id " + rendezVous.getId() + ".", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DTOException e) {
            log.error("Erreur avec le DTO : ", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (ServiceException e) {
            log.error("Erreur de service : ", e);
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRendezVous(@PathVariable Long id) {
        try {
            log.info("Suppression du rendez-vous avec l'id " + id + ".");
            this.rendezVousService.deleteRendezVous(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundException e) {
            log.error("Impossible de trouver le rendez-vous avec l'id " + id + ".", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DBException e) {
            log.error("Erreur lors de la suppression du rendez-vous avec l'id " + id + ".", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
