package fr.unilasalle.tp_garage_auto.repositories;

import fr.unilasalle.tp_garage_auto.beans.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
