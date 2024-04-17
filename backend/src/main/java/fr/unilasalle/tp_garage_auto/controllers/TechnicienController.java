package fr.unilasalle.tp_garage_auto.controllers;

import fr.unilasalle.tp_garage_auto.DTO.TechnicienDTO;
import fr.unilasalle.tp_garage_auto.beans.Client;
import fr.unilasalle.tp_garage_auto.beans.Technicien;
import fr.unilasalle.tp_garage_auto.exceptions.DBException;
import fr.unilasalle.tp_garage_auto.exceptions.DTOException;
import fr.unilasalle.tp_garage_auto.exceptions.NotFoundException;
import fr.unilasalle.tp_garage_auto.exceptions.ServiceException;
import fr.unilasalle.tp_garage_auto.services.TechnicienService;
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

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("technicien")
@RequiredArgsConstructor
@Slf4j
@SecurityScheme(
        name = "bearerAuth",
        scheme = "bearer",
        bearerFormat = "JWT",
        type = SecuritySchemeType.HTTP,
        in = SecuritySchemeIn.HEADER
)
public class TechnicienController {
    private final TechnicienService technicienService;


    /**
     * Méthode GET pour récupérer tous les techniciens
     * @param nom : nom du technicien
     * @param prenom : prenom du technicien
     * @return
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('admin','technicien','client')")
    @Operation(summary = "Récupérer tous les techniciens",
            description = "Récupérer tous les techniciens en fonction de certains critères",
            tags = { "technicien" },
            security = { @SecurityRequirement(name = "bearerAuth") })
    public ResponseEntity<?> getTechnicien(@RequestParam(required = false, name = "nom") String nom,
                                           @RequestParam(required = false, name="prenom") String prenom) throws ServiceException {
        if (nom != null) {
            return getTechnicienByNom(nom);
        } else if (prenom != null) {
            return getTechnicienByPrenom(prenom);
        } else {
            log.info("Récupération de tous les techniciens ...");
            Set<Technicien> technicien = this.technicienService.getAllTechniciens();
            log.info("Techniciens récupérés avec succès : \n\t" + technicien);
            return new ResponseEntity<>(technicien, HttpStatus.OK);
        }
    }


    /**
     * Méthode GET pour récupérer un technicien par id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('admin','technicien','client')")
    @Operation(summary = "Récupérer un technicien par son id",
            description = "Récupérer un technicien en fonction de son id",
            tags = { "technicien" },
            security = { @SecurityRequirement(name = "bearerAuth") })
    public ResponseEntity<Technicien> getTechnicienById(@PathVariable (name = "id") Long id) {
        log.info("Récupération du technicien avec l'id " + id);
        Technicien technicien = this.technicienService.getTechnicienById(id);
        log.info("Technicien récupéré avec succès : \n\t" + technicien);
        return new ResponseEntity<>(technicien, HttpStatus.OK);
    }

    /**
     * Méthode GET pour récupérer les techniciens par nom
     * @param nom
     * @return
     */
    public ResponseEntity<Set<Technicien>> getTechnicienByNom(String nom) {
        log.info("Récupération du technicien avec le nom " + nom);
        Set<Technicien> technicien = this.technicienService.getTechnicienByNom(nom);
        log.info("Technicien récupéré avec succès : \n\t" + technicien);
        return new ResponseEntity<>(technicien, HttpStatus.OK);
    }

    /**
     * Méthode GET pour récupérer les techniciens par prenom
     * @param prenom
     * @return
     */
    public ResponseEntity<Set<Technicien>> getTechnicienByPrenom(String prenom) {
        log.info("Récupération du technicien avec le prenom " + prenom);
        Set<Technicien> technicien = this.technicienService.getTechnicienByPrenom(prenom);
        log.info("Technicien récupéré avec succès : \n\t" + technicien);
        return new ResponseEntity<>(technicien, HttpStatus.OK);
    }

    /**
     * Méthode POST pour créer un technicien
     * @param technicien
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('admin','technicien')")
    @Operation(summary = "Créer un technicien",
            description = "Créer un technicien en fonction de ses informations",
            tags = { "technicien" },
            security = { @SecurityRequirement(name = "bearerAuth") })
    public ResponseEntity<Technicien> postTechnicien(@RequestBody Technicien technicien) throws ServiceException {
        log.info("Création d'un technicien ...");
        Technicien savedObjet = this.technicienService.createTechnicien(technicien);
        log.info("Technicien créé avec succès : \n\t" + savedObjet);
        return new ResponseEntity<>(savedObjet, HttpStatus.CREATED);

    }

    /**
     * Méthode PUT pour mettre à jour un technicien
     * @param id
     * @param technicien
     * @return
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('admin','technicien')")
    @Operation(summary = "Mettre à jour un technicien",
            description = "Mettre à jour un technicien en fonction de ses informations",
            tags = { "technicien" },
            security = { @SecurityRequirement(name = "bearerAuth") })
    public ResponseEntity<Technicien> putTechnicien(@PathVariable (name = "id") Long id,@RequestBody Technicien technicien) throws ServiceException {
        log.info("Mise à jour du technicien ...");
        Technicien savedObjet = this.technicienService.updateTechnicien(id,technicien);
        log.info("Technicien mis à jour avec succès : \n\t" + savedObjet);
        return new ResponseEntity<>(savedObjet, HttpStatus.ACCEPTED);
    }

    /**
     * Méthode DELETE pour supprimer un technicien
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('admin','technicien')")
    @Operation(summary = "Supprimer un technicien",
            description = "Supprimer un technicien en fonction de son id",
            tags = { "technicien" },
            security = { @SecurityRequirement(name = "bearerAuth") })
    public ResponseEntity<Void> deleteTechnicien(@PathVariable (name = "id") Long id) {
        log.info("Suppression du technicien avec l'id " + id + ".");
        this.technicienService.deleteTechnicien(id);
        log.info("Technicien supprimé avec succès.");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
