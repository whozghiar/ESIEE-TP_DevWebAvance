package fr.unilasalle.tp_garage_auto.services;

import fr.unilasalle.tp_garage_auto.beans.RendezVous;
import fr.unilasalle.tp_garage_auto.exceptions.DBException;
import fr.unilasalle.tp_garage_auto.exceptions.NotFoundException;
import fr.unilasalle.tp_garage_auto.exceptions.ServiceException;
import fr.unilasalle.tp_garage_auto.repositories.RendezVousRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RendezVousService {

    private final RendezVousRepository rendezVousRepository;


    /**
     * Récupérer tous les rendezVous
     * @return
     */

    public Set<RendezVous> getAllRendezVous() throws ServiceException {
        try{
            return new HashSet<>(rendezVousRepository.findAll());
        }catch (Exception e){
            throw new ServiceException("Erreur lors de la récupération des rendez-vous.",new NullPointerException());
        }
    }

    /**
     * Récupérer un rendezVous par id
     * @param id
     * @return
     * @throws NotFoundException
     */
    public RendezVous getRendezVousById(Long id) throws NotFoundException {
        RendezVous rendezVous = rendezVousRepository.findById(id).orElse(null);
        if (rendezVous == null) {
            throw new NotFoundException("Impossible de trouver le rendez-vous avec l'id " + id + ".",new NullPointerException());
        }
        return rendezVous;
    }

    /**
     * Récupérer les rendezVous d'un client par son id
     * @param client_id
     * @return
     */
/*
    public Set<RendezVous> getRendezVousByClientId(Long client_id) {
        return rendezVousRepository.findByClientId(client_id);
    }
*/

    /**
     * Récupérer les rendezVous d'un technicien par son id
     * @param technicien_id
     * @return Set<RendezVous>
     */
    public Set<RendezVous> getRendezVousByTechnicienId(Long technicien_id) {
        Set<RendezVous> rendezVous = rendezVousRepository.findByTechnicienId(technicien_id);
        if (rendezVous == null) {
            throw new NotFoundException("Impossible de trouver un rendez-vous avec le technicien id " + technicien_id + ".",new NullPointerException());
        }
        return rendezVous;
    }

    /**
     * Récupérer les rendezVous d'un vehicule par son id
     * @param vehicule_id
     * @return Set<RendezVous>
     */
    public Set<RendezVous> getRendezVousByVehiculeId(Long vehicule_id) {
        Set<RendezVous> rendezVous = rendezVousRepository.findByVehiculeId(vehicule_id);
        if (rendezVous == null) {
            throw new NotFoundException("Impossible de trouver un rendez-vous avec le vehicule id " + vehicule_id + ".",new NullPointerException());
        }
        return rendezVous;
    }

    /**
     * Récupérer les rendezVous par date
     * @param date
     * @return Set<RendezVous>
     */
    public Set<RendezVous> getRendezVousByDate(String date) {
        Set<RendezVous> rendezVous = rendezVousRepository.findByDate(date);
        if (rendezVous == null) {
            throw new NotFoundException("Impossible de trouver un rendez-vous avec la date " + date + ".",new NullPointerException());
        }
        return rendezVous;
    }

    /**
     * Récupérer les rendezVous par typeService
     * @param typeService
     * @return Set<RendezVous>
     */
    public Set<RendezVous> getRendezVousByTypeService(String typeService) {
        Set<RendezVous> rendezVous = rendezVousRepository.findByTypeServiceContainingIgnoreCase(typeService);
        if (rendezVous == null) {
            throw new NotFoundException("Impossible de trouver un rendez-vous avec le type de service " + typeService + ".",new NullPointerException());
        }
        return rendezVous;
    }




    /**
     * Créer un rendezVous
     * @param rendezVous
     * @return
     * @throws ServiceException
     * @throws DBException
     * @throws NotFoundException
     */

    @Transactional
    public RendezVous createRendezVous(RendezVous rendezVous) throws ServiceException, DBException, NotFoundException {

        if(rendezVous == null){
            throw new ServiceException("Le rendez-vous ne peut pas être null.",new NullPointerException());
        }

        if(rendezVous.getId() != null){
            throw new ServiceException("Le rendez-vous ne peut pas avoir d'id",new NullPointerException());
        }

        try{
            return rendezVousRepository.save(rendezVous);
        }catch (Exception e){
            throw new DBException("Erreur lors de la création du rendez-vous en base.", e);
        }
    }

    /**
     * Mettre à jour un rendezVous
     * @param rendezVous
     * @return
     * @throws ServiceException
     * @throws NotFoundException
     * @throws DBException
     */
    @Transactional
    public RendezVous updateRendezVous(Long id,RendezVous rendezVous) throws NotFoundException, DBException, ServiceException {
        if(rendezVous == null){
            throw new ServiceException("Le rendez-vous ne peut pas être null.",new NullPointerException());
        }

        if(id == null){
            throw new ServiceException("L'id du rendez-vous ne peut pas être null.",new NullPointerException());
        }

        RendezVous existingRendezVous = rendezVousRepository.findById(id).orElse(null);
        if(existingRendezVous == null){
            throw new NotFoundException("Impossible de trouver un rendez-vous avec l'id " + rendezVous.getId() + ".",new NullPointerException());
        }

        existingRendezVous.setDate(rendezVous.getDate());
        existingRendezVous.setTypeService(rendezVous.getTypeService());
        existingRendezVous.setVehicule(rendezVous.getVehicule());
        existingRendezVous.setTechnicien(rendezVous.getTechnicien());

        try{
            return rendezVousRepository.save(existingRendezVous);
        }catch (Exception e){
            throw new DBException("Erreur lors de la mise à jour du rendez-vous en base.", e);
        }
    }

    /**
     * Supprimer un rendezVous
     * @param id
     * @throws NotFoundException
     * @throws DBException
     */
    @Transactional
    public void deleteRendezVous(Long id) throws NotFoundException, DBException {
        RendezVous existingRendezVous = rendezVousRepository.findById(id).orElse(null);
        if (existingRendezVous == null) {
            throw new NotFoundException("Impossible de trouver un rendez-vous avec l'id " + id + ".",new NullPointerException());
        }

        try {
            rendezVousRepository.delete(existingRendezVous);
        } catch (DBException e) {
            throw new DBException("Erreur lors de la suppression du rendez-vous avec l'id " + id + ".", e);
        }
    }

}
