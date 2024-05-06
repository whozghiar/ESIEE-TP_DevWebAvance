package fr.unilasalle.tp_garage_auto.repositories;

import fr.unilasalle.tp_garage_auto.beans.Technicien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TechnicienRepository extends JpaRepository<Technicien, Long> {

    Set<Technicien> findByNomContainingIgnoreCase(String nom);
    Set<Technicien> findByPrenomContainingIgnoreCase(String prenom);
    Technicien findByEmail(String email);

    @Query("SELECT COUNT(rv) FROM RendezVous rv WHERE rv.technicien.id = :technicienId")
    Long countByTechnicienId(Long technicienId);

    @Query("SELECT COUNT(t) FROM Technicien t")
    Long countAllTechniciens();



}
