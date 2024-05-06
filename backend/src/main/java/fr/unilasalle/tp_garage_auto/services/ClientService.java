package fr.unilasalle.tp_garage_auto.services;

// ------ Imports Beans ------

import fr.unilasalle.tp_garage_auto.beans.Client;
import fr.unilasalle.tp_garage_auto.beans.RendezVous;
import fr.unilasalle.tp_garage_auto.beans.Vehicule;
import fr.unilasalle.tp_garage_auto.exceptions.DBException;
import fr.unilasalle.tp_garage_auto.exceptions.DTOException;
import fr.unilasalle.tp_garage_auto.exceptions.NotFoundException;
import fr.unilasalle.tp_garage_auto.exceptions.ServiceException;
import fr.unilasalle.tp_garage_auto.repositories.ClientRepository;
import fr.unilasalle.tp_garage_auto.repositories.RendezVousRepository;
import fr.unilasalle.tp_garage_auto.repositories.VehiculeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService {

    private final ClientRepository clientRepository;
    private final VehiculeRepository vehiculeRepository;
    private final RendezVousRepository rendezVousRepository;

    /**
     * Récupérer tous les clients
     */
    public Set<Client> getAllClients() throws ServiceException {
        try {
            return new HashSet<>(clientRepository.findAll());
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la récupération des clients.",new NullPointerException());
        }
    }

    /**
     * Récupérer un client par id
     * @param id
     * @return
     * @throws NotFoundException
     * @throws DTOException
     */
    public Client getClientById(Long id) throws NotFoundException {
        Client client = clientRepository.findById(id).orElse(null);
        if (client == null) {
            throw new NotFoundException("Impossible de trouver le client avec l'id " + id + ".",new NullPointerException());
        }
        return client;
    }


    /**
     * Récupérer les clients par nom
     * @param nom
     * @return
     */
    public Set<Client> getClientsByName(String nom) {
        Set<Client> clients = clientRepository.findByNomContainingIgnoreCase(nom);
        if (clients.isEmpty()) {
            throw new NotFoundException("Impossible de trouver un client avec le nom " + nom + ".",new NullPointerException());
        }
        return clients;
    }

    /**
     * Récupérer les clients par prénom
     * @param prenom
     * @return
     */
    public Set<Client> getClientsBySurname(String prenom) {
        Set<Client> clients = clientRepository.findByPrenomContainingIgnoreCase(prenom);
        if (clients.isEmpty()) {
            throw new NotFoundException("Impossible de trouver un client avec le prénom " + prenom + ".",new NullPointerException());
        }
        return clients;
    }

    /**
     * Récupérer les clients par email
     * @param email
     * @return
     */
    public Client getClientByEmail(String email) {
        Client client = clientRepository.findByEmailContainingIgnoreCase(email);
        if (client == null) {
            throw new NotFoundException("Impossible de trouver un client avec l'email " + email + ".",new NullPointerException());
        }
        return client;
    }

    /**
     * Récupérer les clients par téléphone
     * @param telephone
     * @return
     */
    public Client getClientsByPhone(String telephone) {
        Client client = clientRepository.findByTelephoneContainingIgnoreCase(telephone);
        if (client == null) {
            throw new NotFoundException("Impossible de trouver un client avec le téléphone " + telephone + ".",new NullPointerException());
        }
        return client;
    }



    /**
     * Créer un client
     * @param client
     * @return
     */
    @Transactional
    public Client createClient(Client client) throws DBException, ServiceException {
        if (client == null) {
            throw new ServiceException("Le client ne peut pas être null.",new NullPointerException());
        }

        if(client.getId() != null){
            throw new ServiceException("Le client ne peut pas avoir d'id.",new NullPointerException());
        }

        Client existingClient = this.clientRepository.findByEmailContainingIgnoreCase(client.getEmail());
        if (existingClient != null) {
            throw new ServiceException("Un client avec l'email " + client.getEmail() + " existe déjà.", new NullPointerException());
        }

        try {
            return this.clientRepository.save(client);
        }
        catch (Exception e) {
            throw new DBException("Erreur lors de la création du client en base.", e);
        }
    }

    /**
     * Mettre à jour un client
     * @param client
     * @return
     */
    @Transactional
    public Client updateClient(Long id, Client client) throws NotFoundException, DBException, ServiceException {
        if (client == null) {
            throw new ServiceException("Le client ne peut pas être null.", new NullPointerException());
        }

        if(id == null){
            throw new ServiceException("L'id du client ne peut pas être null.", new NullPointerException());
        }

        Client existingClient = this.clientRepository.findById(id).orElse(null);
        if (existingClient == null) {
            throw new NotFoundException("Impossible de trouver un client avec l'id " + client.getId() + ".", new NullPointerException());
        }

        if (client.getNom() != null)
            existingClient.setNom(client.getNom());
        if (client.getPrenom() != null)
            existingClient.setPrenom(client.getPrenom());
        if (client.getTelephone() != null)
            existingClient.setTelephone(client.getTelephone());
        if (client.getEmail() != null)
            existingClient.setEmail(client.getEmail());

        try {
            return this.clientRepository.save(existingClient);
        } catch (Exception e) {
            throw new DBException("Erreur lors de la mise à jour du client en base.", e);
        }
    }



    /**
     * Supprimer un client
     * @param id
     */
    @Transactional
    public void deleteClient(Long id) throws NotFoundException, DBException {
        Client existingClient = this.clientRepository.findById(id).orElse(null);
        if (existingClient == null) {
            throw new NotFoundException("Impossible de trouver un client avec l'id " + id + ".", new NullPointerException());
        }


        // Si le client a des véhicules, on les supprime
        Set<Vehicule> vehicules = this.vehiculeRepository.findByClientId(existingClient.getId());
        if (!vehicules.isEmpty()) {
            for (Vehicule vehicule : vehicules) {
                try {
                    this.vehiculeRepository.delete(vehicule);
                } catch (DBException e) {
                    throw new DBException("Erreur lors de la suppression du véhicule avec l'id " + vehicule.getId(), e);
                }
            }
        }

        // Si le client a des rendez-vous, on les supprime
        Set<RendezVous> rendezVous = this.rendezVousRepository.findByClientId(existingClient.getId());
        if (!rendezVous.isEmpty()) {
            for (RendezVous rdv : rendezVous) {
                try {
                    this.rendezVousRepository.delete(rdv);
                } catch (DBException e) {
                    throw new DBException("Erreur lors de la suppression du rendez-vous avec l'id " + rdv.getId(), e);
                }
            }
        }

        try {
            this.clientRepository.delete(existingClient);
        } catch (DBException e) {
            throw new DBException("Erreur lors de la suppression du client avec l'id " + id, e);
        }
    }

}
