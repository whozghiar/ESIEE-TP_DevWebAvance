package fr.unilasalle.tp_garage_auto.services;

import fr.unilasalle.tp_garage_auto.beans.Technicien;
import fr.unilasalle.tp_garage_auto.exceptions.DBException;
import fr.unilasalle.tp_garage_auto.exceptions.NotFoundException;
import fr.unilasalle.tp_garage_auto.repositories.TechnicienRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TechnicienService {
    private final TechnicienRepository technicienRepository;

    /**
     * Récupérer tous les techniciens
     * @return
     */
    public List<Technicien> getAllTechniciens() {
        return technicienRepository.findAll();
    }

    /**
     * Créer ou mettre à jour un technicien
     * @param technicien
     * @return
     */
    public Technicien updateTechnicien(Technicien technicien) throws NotFoundException, DBException {
        Technicien existingTechnicien = null;

        if(technicien.getId() != null){
            existingTechnicien = technicienRepository.findById(technicien.getId()).orElse(null);
            if(existingTechnicien == null){
                throw new NotFoundException("Could not find technicien with id " + technicien.getId());
            }
        }else{
            existingTechnicien = new Technicien();
        }

        existingTechnicien.setNom(technicien.getNom());
        existingTechnicien.setPrenom(technicien.getPrenom());
        existingTechnicien.setRendezVous(technicien.getRendezVous());

        try{
            return technicienRepository.save(existingTechnicien);
        }catch(DBException e){
            throw new DBException("Error while updating technicien");
        }
    }


    /**
     * Supprimer un technicien
     * @param id
     * @throws NotFoundException
     * @throws DBException
     */
    public void deleteTechnicien(Long id) throws NotFoundException, DBException {
        Technicien existingTechnicien = technicienRepository.findById(id).orElse(null);
        if (existingTechnicien == null) {
            throw new NotFoundException("Could not find technicien with id " + id);
        }

        try {
            technicienRepository.delete(existingTechnicien);
        } catch (DBException e) {
            throw new DBException("Error while deleting technicien with id " + id);
        }
    }
}
