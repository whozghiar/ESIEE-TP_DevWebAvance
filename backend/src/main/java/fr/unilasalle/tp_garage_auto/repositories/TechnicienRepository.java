package fr.unilasalle.tp_garage_auto.repositories;

import fr.unilasalle.tp_garage_auto.beans.Technicien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicienRepository extends JpaRepository<Technicien, Long> {

}
