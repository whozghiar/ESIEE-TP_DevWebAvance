package fr.unilasalle.tp_garage_auto.repositories;

import fr.unilasalle.tp_garage_auto.beans.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Set<Client> findByNomContainingIgnoreCase(String nom);
    Set<Client> findByPrenomContainingIgnoreCase(String prenom);

    Client findByEmailContainingIgnoreCase(String email);

    Client findByTelephoneContainingIgnoreCase(String telephone);

    @Query("SELECT COUNT(c) FROM Client c")
    Long countAllClients();

    @Query("SELECT COUNT(rv) FROM RendezVous rv JOIN rv.vehicule v JOIN v.client c WHERE c.id = :clientId")
    Long countRendezVousByClientId(Long clientId);


}
