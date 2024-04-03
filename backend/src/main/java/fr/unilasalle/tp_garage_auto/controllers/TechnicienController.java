package fr.unilasalle.tp_garage_auto.controllers;

import fr.unilasalle.tp_garage_auto.DTO.TechnicienDTO;
import fr.unilasalle.tp_garage_auto.beans.Client;
import fr.unilasalle.tp_garage_auto.beans.Technicien;
import fr.unilasalle.tp_garage_auto.exceptions.DBException;
import fr.unilasalle.tp_garage_auto.exceptions.DTOException;
import fr.unilasalle.tp_garage_auto.exceptions.NotFoundException;
import fr.unilasalle.tp_garage_auto.exceptions.ServiceException;
import fr.unilasalle.tp_garage_auto.services.TechnicienService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/technicien")
@RequiredArgsConstructor
@Slf4j
public class TechnicienController {
    private final TechnicienService technicienService;


    /**
     * Méthode GET pour récupérer tous les techniciens
     * @param nom : nom du technicien
     * @param prenom : prenom du technicien
     * @return
     */
    @GetMapping
    public ResponseEntity<?> getTechnicien(@RequestParam(required = false) Optional<String> nom,
                                           @RequestParam(required = false) Optional<String> prenom,
                                           @RequestParam(required = false) Optional<Long> rendezVousId) throws ServiceException {
        if (nom.isPresent()) {
            return getTechnicienByNom(nom.get());
        } else if (prenom.isPresent()) {
            return getTechnicienByPrenom(prenom.get());
        } else if (rendezVousId.isPresent()) {
            return getTechnicienByRendezVousId(rendezVousId.get());
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
    public ResponseEntity<Technicien> getTechnicienById(@PathVariable Long id) {
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
     * Méthode GET pour récupérer les techniciens par rendezVousId
     * @param rendezVousId
     * @return
     */
    public ResponseEntity<Technicien> getTechnicienByRendezVousId(Long rendezVousId) {
        log.info("Récupération du technicien avec le rendezVousId " + rendezVousId);
        Technicien technicien = this.technicienService.getTechnicienByRendezVousId(rendezVousId);
        log.info("Technicien récupéré avec succès : \n\t" + technicien);
        return new ResponseEntity<>(technicien, HttpStatus.OK);
    }

    /**
     * Méthode POST pour créer un technicien
     * @param technicien
     * @return
     */
    @PostMapping
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
    public ResponseEntity<Technicien> putTechnicien(@PathVariable Long id,@RequestBody Technicien technicien) throws ServiceException {
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
    public ResponseEntity<Void> deleteTechnicien(@PathVariable Long id) {
        log.info("Suppression du technicien avec l'id " + id + ".");
        this.technicienService.deleteTechnicien(id);
        log.info("Technicien supprimé avec succès.");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
