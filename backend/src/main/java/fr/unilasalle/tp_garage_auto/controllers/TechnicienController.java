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
import java.util.Set;

@RestController
@RequestMapping("/technicien")
@RequiredArgsConstructor
@Slf4j
public class TechnicienController {
    private final TechnicienService technicienService;

    /**
     * Méthode GET pour récupérer tous les techniciens
     * @return
     */
    @GetMapping
    public ResponseEntity<Set<Technicien>> getTechnicien() {
        try{
            log.info("Récupération de tous les techniciens ...");
            Set<Technicien> techniciens = this.technicienService.getAllTechniciens();
            log.info("Techniciens récupérés avec succès : \n\t" + techniciens);
            return new ResponseEntity<>(techniciens, HttpStatus.OK);
        } catch (ServiceException e) {
            log.error("Erreur lors de la récupération des techniciens.", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Méthode GET pour récupérer un technicien par id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Technicien> getTechnicienById(@PathVariable Long id) {
        try {
            log.info("Récupération du technicien avec l'id " + id);
            Technicien technicien = this.technicienService.getTechnicienById(id);
            log.info("Technicien récupéré avec succès : \n\t" + technicien);
            return new ResponseEntity<>(technicien, HttpStatus.OK);
        } catch (NotFoundException e) {
            log.error("Impossible de trouver le technicien avec l'id " + id + ".", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Méthode GET pour récupérer les techniciens par nom
     * @param nom
     * @return
     */
    @GetMapping
    public ResponseEntity<Set<Technicien>> getTechnicienByNom(@RequestParam String nom) {
        try {
            log.info("Récupération du technicien avec le nom " + nom);
            Set<Technicien> technicien = this.technicienService.getTechnicienByNom(nom);
            log.info("Technicien récupéré avec succès : \n\t" + technicien);
            return new ResponseEntity<>(technicien, HttpStatus.OK);
        } catch (NotFoundException e) {
            log.error("Impossible de trouver le technicien avec le nom " + nom + ".", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Méthode GET pour récupérer les techniciens par prenom
     * @param prenom
     * @return
     */
    @GetMapping
    public ResponseEntity<Set<Technicien>> getTechnicienByPrenom(@RequestParam String prenom) {
        try {
            log.info("Récupération du technicien avec le prenom " + prenom);
            Set<Technicien> technicien = this.technicienService.getTechnicienByPrenom(prenom);
            log.info("Technicien récupéré avec succès : \n\t" + technicien);
            return new ResponseEntity<>(technicien, HttpStatus.OK);
        } catch (NotFoundException e) {
            log.error("Impossible de trouver le technicien avec le prenom " + prenom + ".", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Méthode POST pour créer un technicien
     * @param technicien
     * @return
     */
    @PostMapping
    public ResponseEntity<Technicien> postTechnicien(@RequestBody Technicien technicien) {
        try{
            log.info("Création d'un technicien ...");
            Technicien savedObjet = this.technicienService.createTechnicien(technicien);
            log.info("Technicien créé avec succès : \n\t" + savedObjet);
            return new ResponseEntity<>(savedObjet, HttpStatus.CREATED);
        } catch (DBException e){
            log.error("Erreur lors de l'enregistrement du technicien.", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            log.error("Impossible de trouver le technicien avec l'id " + technicien.getId() + ".", e);
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
     * Méthode PUT pour mettre à jour un technicien
     * @param id
     * @param technicien
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Technicien> putTechnicien(@PathVariable Long id,@RequestBody Technicien technicien) {
        try {
            log.info("Mise à jour du technicien ...");
            Technicien savedObjet = this.technicienService.updateTechnicien(id,technicien);
            log.info("Technicien mis à jour avec succès : \n\t" + savedObjet);
            return new ResponseEntity<>(savedObjet, HttpStatus.ACCEPTED);
        } catch (DBException e) {
            log.error("Erreur lors de la mise à jour du technicien.", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            log.error("Impossible de trouver le technicien avec l'id " + technicien.getId() + ".", e);
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
     * Méthode DELETE pour supprimer un technicien
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTechnicien(@PathVariable Long id) {
        try {
            log.info("Suppression du technicien avec l'id " + id + ".");
            this.technicienService.deleteTechnicien(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundException e) {
            log.error("Impossible de trouver le technicien avec l'id " + id + ".", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DBException e) {
            log.error("Erreur lors de la suppression du technicien avec l'id " + id + ".", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
