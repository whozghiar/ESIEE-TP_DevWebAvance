package fr.unilasalle.tp_garage_auto.repositories;

import fr.unilasalle.tp_garage_auto.beans.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {

    Set<RendezVous> findByClientId(Long clientId);
    Set<RendezVous> findByTechnicienId(Long technicienId);
    Set<RendezVous> findByVehiculeId(Long vehiculeId);

    Set<RendezVous> findByDate(String date);

    Set<RendezVous> findByTypeServiceContainingIgnoreCase(String typeService);

}
