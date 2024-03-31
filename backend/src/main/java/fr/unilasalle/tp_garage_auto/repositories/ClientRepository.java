package fr.unilasalle.tp_garage_auto.repositories;

import fr.unilasalle.tp_garage_auto.beans.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Set<Client> findByNomContainingIgnoreCase(String nom);
    Set<Client> findByPrenomContainingIgnoreCase(String prenom);

    Set<Client> findByEmailContainingIgnoreCase(String email);

    Set<Client> findByTelephoneContainingIgnoreCase(String telephone);
}
