package fr.unilasalle.tp_garage_auto.repositories;


import fr.unilasalle.tp_garage_auto.beans.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {

    Set<Vehicule> findByClientId(Long clientId);

    Set<Vehicule> findByMarqueContainingIgnoreCase(String marque);
    Set<Vehicule> findByModeleContainingIgnoreCase(String modele);
    Set<Vehicule> findByAnnee(Integer annee);
    Vehicule findByImmatriculationIgnoreCase(String immatriculation);








}
