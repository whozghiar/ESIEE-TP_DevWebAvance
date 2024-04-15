package fr.unilasalle.tp_garage_auto.controllers;

import fr.unilasalle.tp_garage_auto.beans.RendezVous;
import fr.unilasalle.tp_garage_auto.exceptions.ServiceException;
import fr.unilasalle.tp_garage_auto.services.RendezVousService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
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
@SecurityScheme(
        name = "bearerAuth",
        scheme = "bearer",
        bearerFormat = "JWT",
        type = SecuritySchemeType.HTTP,
        in = SecuritySchemeIn.HEADER
)
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
    @PreAuthorize("hasAnyRole('admin','technicien','client')")
    @Operation(summary = "Récupérer tous les rendez-vous",
            description = "Récupérer tous les rendez-vous en fonction de certains critères",
            tags = { "rendez-vous" },
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    public ResponseEntity<?> getRendezVous(@RequestParam(required = false, name = "date") String date,
                                           @RequestParam(required = false, name = "typeService") String typeService,
                                           @RequestParam(required = false, name = "vehicule_id") Long vehicule_id,
                                           @RequestParam(required = false, name = "technicien_id") Long technicien_id,
                                           @RequestParam(required = false, name = "clientId") Long clientId) throws ServiceException {

        if (date != null) {
            return getRendezVousByDate(date);
        } else if (typeService != null) {
            return getRendezVousByTypeService(typeService);
        } else if (vehicule_id != null) {
            return getRendezVousByVehiculeId(vehicule_id);
        } else if (technicien_id != null) {
            return getRendezVousByTechnicienId(technicien_id);
        } else if (clientId != null) {
            return getRendezVousByClientId(clientId);
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
    @PreAuthorize("hasAnyRole('admin','technicien','client')")
    @Operation(summary = "Récupérer un rendez-vous par son id",
            description = "Récupérer un rendez-vous en fonction de son id",
            tags = { "rendez-vous" },
            security = { @SecurityRequirement(name = "bearerAuth") })
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
    @PreAuthorize("hasAnyRole('admin','technicien')")
    @Operation(summary = "Créer un rendez-vous",
            description = "Créer un rendez-vous en fonction de ses informations",
            tags = { "rendez-vous" },
            security = { @SecurityRequirement(name = "bearerAuth") })
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
    @PreAuthorize("hasAnyRole('admin','technicien')")
    @Operation(summary = "Mettre à jour un rendez-vous",
            description = "Mettre à jour un rendez-vous en fonction de ses informations",
            tags = { "rendez-vous" },
            security = { @SecurityRequirement(name = "bearerAuth") })
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
    @PreAuthorize("hasAnyRole('admin','technicien')")
    @Operation(summary = "Supprimer un rendez-vous",
            description = "Supprimer un rendez-vous",
            tags = { "rendez-vous" },
            security = { @SecurityRequirement(name = "bearerAuth") })
    public ResponseEntity<Void> deleteRendezVous(@PathVariable Long id) {
        log.info("Suppression du rendez-vous avec l'id " + id + ".");
        this.rendezVousService.deleteRendezVous(id);
        log.info("Rendez-vous supprimé avec succès.");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
