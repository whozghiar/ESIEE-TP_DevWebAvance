package fr.unilasalle.tp_garage_auto.services;

import fr.unilasalle.tp_garage_auto.beans.RendezVous;
import fr.unilasalle.tp_garage_auto.beans.Technicien;
import fr.unilasalle.tp_garage_auto.exceptions.DBException;
import fr.unilasalle.tp_garage_auto.exceptions.DTOException;
import fr.unilasalle.tp_garage_auto.exceptions.NotFoundException;
import fr.unilasalle.tp_garage_auto.exceptions.ServiceException;
import fr.unilasalle.tp_garage_auto.repositories.RendezVousRepository;
import fr.unilasalle.tp_garage_auto.repositories.TechnicienRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TechnicienService {
    private final TechnicienRepository technicienRepository;
    private final RendezVousRepository rendezVousRepository;

    /**
     * Récupérer tous les techniciens
     * @return
     */
    public Set<Technicien> getAllTechniciens() throws ServiceException {
        try{
            return new HashSet<>(technicienRepository.findAll());
        }catch (Exception e){
            throw new ServiceException("Erreur lors de la récupération des techniciens.",e);
        }
    }


    /**
     * Récupérer un technicien par id
     * @param id
     * @return
     * @throws NotFoundException
     * @throws DTOException
     */
    public Technicien getTechnicienById(Long id) throws NotFoundException, DTOException {
        Technicien technicien = technicienRepository.findById(id).orElse(null);
        if (technicien == null) {
            throw new NotFoundException("Impossible de trouver le technicien avec l'id " + id + ".",new NullPointerException());
        }
        return technicien;
    }

    /**
     * Récupérer un technicien par nom
     * @param nom
     * @return
     */
    public Set<Technicien> getTechnicienByNom(String nom){
        Set<Technicien> techniciens = technicienRepository.findByNomContainingIgnoreCase(nom);
        if(techniciens == null || techniciens.isEmpty()){
            throw new NotFoundException("Impossible de trouver de techniciens avec le nom " + nom + ".",new NullPointerException());
        }
        return techniciens;
    }

    /**
     * Récupérer un technicien par prenom
     * @param prenom
     * @return
     */
    public Set<Technicien> getTechnicienByPrenom(String prenom){
        Set<Technicien> techniciens = technicienRepository.findByPrenomContainingIgnoreCase(prenom);
        if(techniciens == null || techniciens.isEmpty()){
            throw new NotFoundException("Impossible de trouver de techniciens avec le prenom " + prenom + ".",new NullPointerException());
        }
        return techniciens;
    }

    /**
     * Récupérer un technicien par email
     * @param email
     * @return
     */
    public Technicien getTechnicienByEmail(String email){
        Technicien technicien = technicienRepository.findByEmail(email);
        if(technicien == null){
            throw new NotFoundException("Impossible de trouver de techniciens avec l'email " + email + ".",new NullPointerException());
        }
        return technicien;
    }


    /**
     * Créer un technicien
     * @param technicien
     * @return
     */
    @Transactional
    public Technicien createTechnicien(Technicien  technicien) throws NotFoundException, DBException, ServiceException {
        if(technicien == null){
            throw new ServiceException("Le technicien ne peut pas être null.",new NullPointerException());
        }

        if(technicien.getId() != null){
            throw new ServiceException("Le technicien ne peut pas avoir d'id.",new NullPointerException());
        }

        try{
            return technicienRepository.save(technicien);
        }catch (Exception e){
            throw new DBException("Erreur lors de la création du technicien en base.", e);
        }

    }

    /**
     * Mettre à jour un technicien
     * @param id
     * @param technicien
     * @return
     * @throws NotFoundException
     * @throws DBException
     * @throws DTOException
     * @throws ServiceException
     */
    @Transactional
    public Technicien updateTechnicien(Long id,Technicien technicien) throws NotFoundException, DBException, DTOException, ServiceException {
        if(technicien == null){
            throw new ServiceException("Le technicien ne peut pas être null.",new NullPointerException());
        }

        if(id == null){
            throw new ServiceException("L'id du technicien ne peut pas être null.",new NullPointerException());
        }

        Technicien existingTechnicien = technicienRepository.findById(technicien.getId()).orElse(null);
        if(existingTechnicien == null){
            throw new NotFoundException("Impossible de trouver le technicien avec l'id " + technicien.getId() + ".",new NullPointerException());
        }

        if(technicien.getNom() != null)
            existingTechnicien.setNom(technicien.getNom());
        if(technicien.getPrenom() != null)
            existingTechnicien.setPrenom(technicien.getPrenom());

        try{
            return technicienRepository.save(technicien);
        }catch (Exception e){
            throw new DBException("Erreur lors de la mise à jour du technicien en base.", e);
        }
    }


    /**
     * Supprimer un technicien
     * @param id
     * @throws NotFoundException
     * @throws DBException
     */
    @Transactional
    public void deleteTechnicien(Long id) throws NotFoundException, DBException {
        Technicien existingTechnicien = technicienRepository.findById(id).orElse(null);
        if (existingTechnicien == null) {
            throw new NotFoundException("Impossible de trouver le technicien avec l'id " + id + ".",new NullPointerException());
        }

        Set<RendezVous> rendezVous = rendezVousRepository.findByTechnicienId(id);
        if(!rendezVous.isEmpty()){
            for(RendezVous rdv : rendezVous){
                rdv.setTechnicien(null);
                rendezVousRepository.save(rdv);
            }
        }

        try {
            technicienRepository.delete(existingTechnicien);
        } catch (DBException e) {
            throw new DBException("Erreur lors de la suppression du technicien avec l'id " + id + ".", e);
        }
    }
}
