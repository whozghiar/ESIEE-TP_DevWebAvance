package fr.unilasalle.tp_garage_auto.services;

import fr.unilasalle.tp_garage_auto.DTO.RendezVousDTO;
import fr.unilasalle.tp_garage_auto.beans.RendezVous;
import fr.unilasalle.tp_garage_auto.beans.Technicien;
import fr.unilasalle.tp_garage_auto.beans.Vehicule;
import fr.unilasalle.tp_garage_auto.exceptions.DBException;
import fr.unilasalle.tp_garage_auto.exceptions.DTOException;
import fr.unilasalle.tp_garage_auto.exceptions.NotFoundException;
import fr.unilasalle.tp_garage_auto.exceptions.ServiceException;
import fr.unilasalle.tp_garage_auto.repositories.RendezVousRepository;
import fr.unilasalle.tp_garage_auto.repositories.TechnicienRepository;
import fr.unilasalle.tp_garage_auto.repositories.VehiculeRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RendezVousService {

    private final RendezVousRepository rendezVousRepository;
    private final VehiculeRepository vehiculeRepository;
    private final TechnicienRepository technicienRepository;


    /**
     * Récupérer tous les rendezVous
     * @return
     */

    public List<RendezVous> getAllRendezVous() {
        return rendezVousRepository.findAll();
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
            throw new NotFoundException("Impossible de trouver le rendez-vous avec l'id " + id + ".");
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
            throw new ServiceException("Le rendez-vous ne peut pas être null.");
        }

        if(rendezVous.getId() != null){
            throw new ServiceException("Le rendez-vous ne peut pas avoir d'id");
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
    public RendezVous updateRendezVous(RendezVous rendezVous) throws NotFoundException, DBException, ServiceException {
        if(rendezVous == null){
            throw new ServiceException("Le rendez-vous ne peut pas être null.");
        }

        if(rendezVous.getId() == null){
            throw new ServiceException("Le rendez-vous doit avoir un id.");
        }

        RendezVous existingRendezVous = rendezVousRepository.findById(rendezVous.getId()).orElse(null);

        if(existingRendezVous == null){
            throw new NotFoundException("Impossible de trouver un rendez-vous avec l'id " + rendezVous.getId() + ".");
        }

        try{
            return rendezVousRepository.save(rendezVous);
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
            throw new NotFoundException("Impossible de trouver un rendez-vous avec l'id " + id + ".");
        }

        try {
            rendezVousRepository.delete(existingRendezVous);
        } catch (DBException e) {
            throw new DBException("Erreur lors de la suppression du rendez-vous avec l'id " + id + ".", e);
        }
    }

}
