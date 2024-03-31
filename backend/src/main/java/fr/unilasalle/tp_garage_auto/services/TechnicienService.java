package fr.unilasalle.tp_garage_auto.services;

import fr.unilasalle.tp_garage_auto.DTO.ClientDTO;
import fr.unilasalle.tp_garage_auto.DTO.TechnicienDTO;
import fr.unilasalle.tp_garage_auto.beans.RendezVous;
import fr.unilasalle.tp_garage_auto.beans.Technicien;
import fr.unilasalle.tp_garage_auto.exceptions.DBException;
import fr.unilasalle.tp_garage_auto.exceptions.DTOException;
import fr.unilasalle.tp_garage_auto.exceptions.NotFoundException;
import fr.unilasalle.tp_garage_auto.exceptions.ServiceException;
import fr.unilasalle.tp_garage_auto.repositories.TechnicienRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
     * Créer ou mettre à jour un technicien
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

    @Transactional
    public Technicien updateTechnicien(Technicien technicien) throws NotFoundException, DBException, DTOException, ServiceException {
        if(technicien == null){
            throw new ServiceException("Le technicien ne peut pas être null.");
        }

        if(technicien.getId() == null){
            throw new ServiceException("Le technicien doit avoir un id.");
        }

        Technicien existingTechnicien = technicienRepository.findById(technicien.getId()).orElse(null);

        if(existingTechnicien == null){
            throw new NotFoundException("Impossible de trouver le technicien avec l'id " + technicien.getId() + ".");
        }

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
