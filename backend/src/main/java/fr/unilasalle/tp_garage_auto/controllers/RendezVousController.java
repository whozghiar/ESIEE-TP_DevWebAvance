package fr.unilasalle.tp_garage_auto.controllers;

import fr.unilasalle.tp_garage_auto.beans.RendezVous;
import fr.unilasalle.tp_garage_auto.exceptions.ServiceException;
import fr.unilasalle.tp_garage_auto.services.RendezVousService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/rendez-vous")
@RequiredArgsConstructor
@Slf4j
public class RendezVousController {

    private final RendezVousService rendezVousService;

    /**
     * Méthode GET pour récupérer tous les rendez-vous
     * @param date : date du rendez-vous
     * @param typeService : type de service
     * @param vehicule_id : id du véhicule
     * @param technicien_id : id du technicien
     * @param clientId : id du client
     * @return
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('client_admin','client_employe')")
    public ResponseEntity<?> getRendezVous(@RequestParam(required = false) Optional<String> date,
                                           @RequestParam(required = false) Optional<String> typeService,
                                           @RequestParam(required = false) Optional<Long> vehicule_id,
                                           @RequestParam(required = false) Optional<Long> technicien_id,
                                           @RequestParam(required = false) Optional<Long> clientId) throws ServiceException {

        if (date.isPresent()) {
            return getRendezVousByDate(date.get());
        } else if (typeService.isPresent()) {
            return getRendezVousByTypeService(typeService.get());
        } else if (vehicule_id.isPresent()) {
            return getRendezVousByVehiculeId(vehicule_id.get());
        } else if (technicien_id.isPresent()) {
            return getRendezVousByTechnicienId(technicien_id.get());
        } else if (clientId.isPresent()) {
            return getRendezVousByClientId(clientId.get());
        } else {
            log.info("Récupération de tous les rendez-vous ...");
            Set<RendezVous> rendezVous = this.rendezVousService.getAllRendezVous();
            log.info("Rendez-vous récupérés avec succès : \n\t" + rendezVous);
            return new ResponseEntity<>(rendezVous, HttpStatus.OK);
        }
    }

    /**
     * Méthode GET pour récupérer un rendez-vous par id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('client_admin','client_employe')")
    public ResponseEntity<RendezVous> getRendezVousById(@PathVariable Long id) {
        log.info("Récupération du rendez-vous avec l'id " + id);
        RendezVous rendezVous = this.rendezVousService.getRendezVousById(id);
        log.info("Rendez-vous récupéré avec succès : \n\t" + rendezVous);
        return new ResponseEntity<>(rendezVous, HttpStatus.OK);

    }

    /**
     * Méthode GET pour récupérer les rendez-vous par technicien_id
     * @param technicien_id
     * @return
     */
    public ResponseEntity<Set<RendezVous>> getRendezVousByTechnicienId(Long technicien_id) {
        log.info("Récupération des rendez-vous du technicien avec l'id " + technicien_id);
        Set<RendezVous> rendezVous = this.rendezVousService.getRendezVousByTechnicienId(technicien_id);
        log.info("Rendez-vous récupérés avec succès : \n\t" + rendezVous);
        return new ResponseEntity<>(rendezVous, HttpStatus.OK);
    }

    /**
     * Méthode GET pour récupérer les rendez-vous par vehicule_id
     * @param vehicule_id
     * @return
     */
    public ResponseEntity<Set<RendezVous>> getRendezVousByVehiculeId(Long vehicule_id) {
        log.info("Récupération des rendez-vous du véhicule avec l'id " + vehicule_id);
        Set<RendezVous> rendezVous = this.rendezVousService.getRendezVousByVehiculeId(vehicule_id);
        log.info("Rendez-vous récupérés avec succès : \n\t" + rendezVous);
        return new ResponseEntity<>(rendezVous, HttpStatus.OK);
    }

    /**
     * Méthode GET pour récupérer les rendez-vous par date
     * @param date
     * @return
     */
    public ResponseEntity<Set<RendezVous>> getRendezVousByDate(String date) {
        log.info("Récupération des rendez-vous du " + date);
        Set<RendezVous> rendezVous = this.rendezVousService.getRendezVousByDate(date);
        log.info("Rendez-vous récupérés avec succès : \n\t" + rendezVous);
        return new ResponseEntity<>(rendezVous, HttpStatus.OK);
    }

    /**
     * Récupérer les rendez-vous par type de service
     * @param typeService
     * @return
     */
    public ResponseEntity<Set<RendezVous>> getRendezVousByTypeService(String typeService) {
        log.info("Récupération des rendez-vous du type de service " + typeService);
        Set<RendezVous> rendezVous = this.rendezVousService.getRendezVousByTypeService(typeService);
        log.info("Rendez-vous récupérés avec succès : \n\t" + rendezVous);
        return new ResponseEntity<>(rendezVous, HttpStatus.OK);
    }

    /**
     * Récupérer les rendez-vous par client_id
     * @param clientId
     * @return
     */
    public ResponseEntity<Set<RendezVous>> getRendezVousByClientId(Long clientId) {
        log.info("Récupération des rendez-vous du client avec l'id " + clientId);
        Set<RendezVous> rendezVous = this.rendezVousService.getRendezVousByClientId(clientId);
        log.info("Rendez-vous récupérés avec succès : \n\t" + rendezVous);
        return new ResponseEntity<>(rendezVous, HttpStatus.OK);
    }

    /**
     * Méthode POST pour créer un rendez-vous
     * @param rendezVous
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('client_admin','client_employe')")
    public ResponseEntity<RendezVous> postRendezVous(@RequestBody RendezVous rendezVous) throws ServiceException {
        log.info("Création d'un rendez-vous ...");
        RendezVous savedObjet = this.rendezVousService.createRendezVous(rendezVous);
        log.info("Rendez-vous créé avec succès : \n\t" + savedObjet);
        return new ResponseEntity<>(savedObjet, HttpStatus.CREATED);
    }

    /**
     * Méthode PUT pour mettre à jour un rendez-vous
     * @param id
     * @param rendezVous
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<RendezVous> putRendezVous(@PathVariable Long id,@RequestBody RendezVous rendezVous) throws ServiceException {
        log.info("Mise à jour du rendez-vous ...");
        RendezVous savedObjet = this.rendezVousService.updateRendezVous(id,rendezVous);
        log.info("Rendez-vous mis à jour avec succès : \n\t" + savedObjet);
        return new ResponseEntity<>(savedObjet, HttpStatus.ACCEPTED);
    }

    /**
     * Méthode DELETE pour supprimer un rendez-vous
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRendezVous(@PathVariable Long id) {
        log.info("Suppression du rendez-vous avec l'id " + id + ".");
        this.rendezVousService.deleteRendezVous(id);
        log.info("Rendez-vous supprimé avec succès.");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
