package fr.unilasalle.tp_garage_auto.services;

import fr.unilasalle.tp_garage_auto.beans.Client;
import fr.unilasalle.tp_garage_auto.beans.RendezVous;
import fr.unilasalle.tp_garage_auto.exceptions.DBException;
import fr.unilasalle.tp_garage_auto.exceptions.NotFoundException;
import fr.unilasalle.tp_garage_auto.exceptions.ServiceException;
import fr.unilasalle.tp_garage_auto.repositories.RendezVousRepository;
import fr.unilasalle.tp_garage_auto.repositories.TechnicienRepository;
import fr.unilasalle.tp_garage_auto.repositories.VehiculeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RendezVousService {

    private static final Logger log = LoggerFactory.getLogger(RendezVousService.class);
    private final RendezVousRepository rendezVousRepository;
    private final TechnicienRepository technicienRepository;
    private final VehiculeRepository vehiculeRepository;


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
     * Récupérer les rendezVous par id du client
     * @param clientId
     * @return Set<RendezVous>
     */
    public Set<RendezVous> getRendezVousByClientId(Long clientId) {
        Set<RendezVous> rendezVous = rendezVousRepository.findByClientId(clientId);
        if (rendezVous == null) {
            throw new NotFoundException("Impossible de trouver un rendez-vous avec le client id " + clientId + ".",new NullPointerException());
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

        // Si l'id du Technicien est renseigné, on récupère le technicien
        if (rendezVous.getTechnicien() != null && rendezVous.getTechnicien().getId() != null) {
            rendezVous.setTechnicien(technicienRepository.findById(rendezVous.getTechnicien().getId()).orElse(null));
        }

        // Si l'id du Vehicule est renseigné, on récupère le vehicule
        if (rendezVous.getVehicule() != null && rendezVous.getVehicule().getId() != null) {
            rendezVous.setVehicule(vehiculeRepository.findById(rendezVous.getVehicule().getId()).orElse(null));
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

        log.info("OMG RDV :  \n\t" + rendezVous.getTechnicien());

        if(existingRendezVous == null){
            throw new NotFoundException("Impossible de trouver un rendez-vous avec l'id " + rendezVous.getId() + ".",new NullPointerException());
        }

        if(rendezVous.getDate() != null)
            existingRendezVous.setDate(rendezVous.getDate());
        if(rendezVous.getTypeService() != null)
            existingRendezVous.setTypeService(rendezVous.getTypeService());

        // Si le vehicule est renseigné :
        if(rendezVous.getVehicule() != null){
            // Si l'id du Vehicule est renseigné, on récupère le vehicule
            if(rendezVous.getVehicule().getId() != null){
                existingRendezVous.setVehicule(vehiculeRepository.findById(rendezVous.getVehicule().getId()).orElse(null));
            }else{ // Sinon on récupère le vehicule renseigné
                existingRendezVous.setVehicule(rendezVous.getVehicule());
            }
        }else{
            existingRendezVous.setVehicule(null);
        }


        // Si le technicien est renseigné :
        if(rendezVous.getTechnicien() != null) {
            // Si l'id du Technicien est renseigné, on récupère le technicien
            if (rendezVous.getTechnicien().getId() != null) {
                existingRendezVous.setTechnicien(technicienRepository.findById(rendezVous.getTechnicien().getId()).orElse(null));
            } else { // Sinon on récupère le technicien renseigné
                existingRendezVous.setTechnicien(rendezVous.getTechnicien());
            }
        }else{
            existingRendezVous.setTechnicien(null);
        }

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
