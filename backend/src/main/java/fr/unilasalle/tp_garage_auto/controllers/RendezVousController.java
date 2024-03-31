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
import java.util.Set;

@RestController
@RequestMapping("/rendez-vous")
@RequiredArgsConstructor
@Slf4j
public class RendezVousController {

    private final RendezVousService rendezVousService;

    /**
     * Méthode GET pour récupérer tous les rendez-vous
     * @return
     */
    @GetMapping
    public ResponseEntity<Set<RendezVous>> getRendezVous() {
        try{
            log.info("Récupération de tous les rendez-vous ...");
            Set<RendezVous> rendezVous = this.rendezVousService.getAllRendezVous();
            log.info("Rendez-vous récupérés avec succès : \n\t" + rendezVous);
            return new ResponseEntity<>(rendezVous, HttpStatus.OK);
        } catch (ServiceException e) {
            log.error("Erreur lors de la récupération des rendez-vous.", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Méthode GET pour récupérer un rendez-vous par id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<RendezVous> getRendezVousById(@PathVariable Long id) {
        try {
            log.info("Récupération du rendez-vous avec l'id " + id);
            RendezVous rendezVous = this.rendezVousService.getRendezVousById(id);
            log.info("Rendez-vous récupéré avec succès : \n\t" + rendezVous);
            return new ResponseEntity<>(rendezVous, HttpStatus.OK);
        } catch (NotFoundException e) {
            log.error("Impossible de trouver le rendez-vous avec l'id " + id + ".", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Méthode GET pour récupérer les rendez-vous par client_id
     * @param client_id
     * @return
     */
    @GetMapping
    public ResponseEntity<Set<RendezVous>> getRendezVousByClientId(@RequestParam Long client_id) {
        try {
            log.info("Récupération des rendez-vous du client avec l'id " + client_id);
            Set<RendezVous> rendezVous = this.rendezVousService.getRendezVousByClientId(client_id);
            log.info("Rendez-vous récupérés avec succès : \n\t" + rendezVous);
            return new ResponseEntity<>(rendezVous, HttpStatus.OK);
        } catch (NotFoundException e) {
            log.error("Impossible de trouver les rendez-vous du client avec l'id " + client_id + ".", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Méthode GET pour récupérer les rendez-vous par technicien_id
     * @param technicien_id
     * @return
     */
    @GetMapping
    public ResponseEntity<Set<RendezVous>> getRendezVousByTechnicienId(@RequestParam Long technicien_id) {
        try {
            log.info("Récupération des rendez-vous du technicien avec l'id " + technicien_id);
            Set<RendezVous> rendezVous = this.rendezVousService.getRendezVousByTechnicien(technicien_id);
            log.info("Rendez-vous récupérés avec succès : \n\t" + rendezVous);
            return new ResponseEntity<>(rendezVous, HttpStatus.OK);
        } catch (NotFoundException e) {
            log.error("Impossible de trouver les rendez-vous du technicien avec l'id " + technicien_id + ".", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Méthode GET pour récupérer les rendez-vous par vehicule_id
     * @param vehicule_id
     * @return
     */
    @GetMapping
    public ResponseEntity<Set<RendezVous>> getRendezVousByVehiculeId(@RequestParam Long vehicule_id) {
        try {
            log.info("Récupération des rendez-vous du véhicule avec l'id " + vehicule_id);
            Set<RendezVous> rendezVous = this.rendezVousService.getRendezVousByVehicule(vehicule_id);
            log.info("Rendez-vous récupérés avec succès : \n\t" + rendezVous);
            return new ResponseEntity<>(rendezVous, HttpStatus.OK);
        } catch (NotFoundException e) {
            log.error("Impossible de trouver les rendez-vous du véhicule avec l'id " + vehicule_id + ".", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Méthode GET pour récupérer les rendez-vous par date
     * @param date
     * @return
     */
    @GetMapping
    public ResponseEntity<Set<RendezVous>> getRendezVousByDate(@RequestParam String date) {
        try {
            log.info("Récupération des rendez-vous du " + date);
            Set<RendezVous> rendezVous = this.rendezVousService.getRendezVousByDate(date);
            log.info("Rendez-vous récupérés avec succès : \n\t" + rendezVous);
            return new ResponseEntity<>(rendezVous, HttpStatus.OK);
        } catch (NotFoundException e) {
            log.error("Impossible de trouver les rendez-vous du " + date + ".", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Méthode GET pour récupérer les rendez-vous par type de service
     * @param typeService
     * @return
     */
    @GetMapping
    public ResponseEntity<Set<RendezVous>> getRendezVousByTypeService(@RequestParam String typeService) {
        try {
            log.info("Récupération des rendez-vous du type de service " + typeService);
            Set<RendezVous> rendezVous = this.rendezVousService.getRendezVousByTypeService(typeService);
            log.info("Rendez-vous récupérés avec succès : \n\t" + rendezVous);
            return new ResponseEntity<>(rendezVous, HttpStatus.OK);
        } catch (NotFoundException e) {
            log.error("Impossible de trouver les rendez-vous du type de service " + typeService + ".", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Méthode POST pour créer un rendez-vous
     * @param rendezVous
     * @return
     */
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

    /**
     * Méthode PUT pour mettre à jour un rendez-vous
     * @param id
     * @param rendezVous
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<RendezVous> putRendezVous(@PathVariable Long id,@RequestBody RendezVous rendezVous) {
        try{
            log.info("Mise à jour du rendez-vous ...");
            RendezVous savedObjet = this.rendezVousService.updateRendezVous(id,rendezVous);
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

    /**
     * Méthode DELETE pour supprimer un rendez-vous
     * @param id
     * @return
     */
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
