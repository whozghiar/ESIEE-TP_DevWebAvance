package fr.unilasalle.tp_garage_auto.repositories;


import fr.unilasalle.tp_garage_auto.beans.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {

    Set<Vehicule> findByClientId(Long clientId);
    Set<Vehicule> findByMarqueContainingIgnoreCase(String marque);
    Set<Vehicule> findByModeleContainingIgnoreCase(String modele);
    Set<Vehicule> findByAnnee(Integer annee);
    Vehicule findByImmatriculationIgnoreCase(String immatriculation);

    @Query("SELECT COUNT(v) FROM Vehicule v WHERE v.client.id = :clientId")
    Long countByClientId(Long clientId);

    @Query("SELECT COUNT(v) FROM Vehicule v WHERE v.marque = :marque")
    Long countByMarque(String marque);

    @Query("SELECT COUNT(v) FROM Vehicule v WHERE v.modele = :modele")
    Long countByModele(String modele);

    @Query("SELECT COUNT(v) FROM Vehicule v WHERE v.annee = :annee")
    Long countByAnnee(Integer annee);

    @Query("SELECT COUNT(v) FROM Vehicule v")
    Long countAllVehicules();










}
