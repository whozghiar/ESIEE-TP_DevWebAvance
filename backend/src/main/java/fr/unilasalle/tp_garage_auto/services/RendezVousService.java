package fr.unilasalle.tp_garage_auto.services;

import fr.unilasalle.tp_garage_auto.beans.RendezVous;
import fr.unilasalle.tp_garage_auto.exceptions.DBException;
import fr.unilasalle.tp_garage_auto.exceptions.NotFoundException;
import fr.unilasalle.tp_garage_auto.repositories.RendezVousRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RendezVousService {

    private final RendezVousRepository rendezVousRepository;

    /**
     * Récupérer tous les rendezVous
     * @return
     */
    public List<RendezVous> getAllRendezVous() {
        return rendezVousRepository.findAll();
    }

    /**
     * Créer ou mettre à jour un rendezVous
     * @param rendezVous
     * @return
     * @throws NotFoundException
     * @throws DBException
     */
    public RendezVous updateRendezVous(RendezVous rendezVous) throws NotFoundException, DBException{
        RendezVous existingRendezVous = null;

        if(rendezVous.getId() != null){
            existingRendezVous = rendezVousRepository.findById(rendezVous.getId()).orElse(null);
            if(existingRendezVous == null){
                throw new NotFoundException("Could not find rendezVous with id " + rendezVous.getId());
            }
        }else{
            existingRendezVous = new RendezVous();
        }

        existingRendezVous.setDate(rendezVous.getDate());
        existingRendezVous.setVehicule(rendezVous.getVehicule());
        existingRendezVous.setTypeService(rendezVous.getTypeService());

        try{
            return rendezVousRepository.save(existingRendezVous);
        }catch(DBException e){
            throw new DBException("Error while updating rendezVous");
        }
    }

    /**
     * Supprimer un rendezVous
     * @param id
     * @throws NotFoundException
     * @throws DBException
     */
    public void deleteRendezVous(Long id) throws NotFoundException, DBException {
        RendezVous existingRendezVous = rendezVousRepository.findById(id).orElse(null);
        if (existingRendezVous == null) {
            throw new NotFoundException("Could not find rendezVous with id " + id);
        }

        try {
            rendezVousRepository.delete(existingRendezVous);
        } catch (DBException e) {
            throw new DBException("Error while deleting rendezVous with id " + id);
        }
    }

}
