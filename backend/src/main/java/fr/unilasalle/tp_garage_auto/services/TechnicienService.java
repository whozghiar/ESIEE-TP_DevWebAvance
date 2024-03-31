package fr.unilasalle.tp_garage_auto.services;

import fr.unilasalle.tp_garage_auto.beans.Technicien;
import fr.unilasalle.tp_garage_auto.exceptions.DBException;
import fr.unilasalle.tp_garage_auto.exceptions.DTOException;
import fr.unilasalle.tp_garage_auto.exceptions.NotFoundException;
import fr.unilasalle.tp_garage_auto.exceptions.ServiceException;
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

    /**
     * Récupérer tous les techniciens
     * @return
     */
    public Set<Technicien> getAllTechniciens() throws ServiceException {
        try{
            return new HashSet<>(technicienRepository.findAll());
        }catch (Exception e){
            throw new ServiceException("Erreur lors de la récupération des techniciens.");
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
            throw new NotFoundException("Impossible de trouver le technicien avec l'id " + id + ".");
        }
        return technicien;
    }

    /**
     * Récupérer un technicien par nom
     * @param nom
     * @return
     */
    public Set<Technicien> getTechnicienByNom(String nom){
        return technicienRepository.findByNomContainingIgnoreCase(nom);
    }

    /**
     * Récupérer un technicien par prenom
     * @param prenom
     * @return
     */
    public Set<Technicien> getTechnicienByPrenom(String prenom){
        return technicienRepository.findByPrenomContainingIgnoreCase(prenom);
    }

    /**
     * Récupérer un technicien par rendezVousId
     * @param rendezVousId
     * @return
     */
    public Technicien getTechnicienByRendezVousId(Long rendezVousId) {
        return technicienRepository.findByRendezVousId(rendezVousId);
    }



    /**
     * Créer un technicien
     * @param technicien
     * @return
     */
    @Transactional
    public Technicien createTechnicien(Technicien  technicien) throws NotFoundException, DBException, ServiceException {
        if(technicien == null){
            throw new ServiceException("Le technicien ne peut pas être null.");
        }

        if(technicien.getId() != null){
            throw new ServiceException("Le technicien ne peut pas avoir d'id.");
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
            throw new ServiceException("Le technicien ne peut pas être null.");
        }

        if(id == null){
            throw new ServiceException("L'id du technicien ne peut pas être null.");
        }

        Technicien existingTechnicien = technicienRepository.findById(technicien.getId()).orElse(null);
        if(existingTechnicien == null){
            throw new NotFoundException("Impossible de trouver le technicien avec l'id " + technicien.getId() + ".");
        }

        existingTechnicien.setNom(technicien.getNom());
        existingTechnicien.setPrenom(technicien.getPrenom());
        existingTechnicien.setRendezVous(technicien.getRendezVous());

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
            throw new NotFoundException("Impossible de trouver le technicien avec l'id " + id + ".");
        }

        try {
            technicienRepository.delete(existingTechnicien);
        } catch (DBException e) {
            throw new DBException("Erreur lors de la suppression du technicien avec l'id " + id + ".", e);
        }
    }
}
