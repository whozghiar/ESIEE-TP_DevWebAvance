package fr.unilasalle.tp_garage_auto.controllers;

import fr.unilasalle.tp_garage_auto.DTO.TechnicienDTO;
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

@RestController
@RequestMapping("/technicien")
@RequiredArgsConstructor
@Slf4j
public class TechnicienController {
    private final TechnicienService technicienService;

    @GetMapping
    public ResponseEntity<List<Technicien>> getTechnicien() {
        log.info("Récupération de tous les techniciens...");
        List<Technicien> techniciens = this.technicienService.getAllTechniciens();
        return new ResponseEntity<>(techniciens, HttpStatus.OK);
    }

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
