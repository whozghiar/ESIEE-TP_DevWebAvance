package fr.unilasalle.tp_garage_auto.services;

// ------ Imports Beans ------

import fr.unilasalle.tp_garage_auto.beans.Client;
import fr.unilasalle.tp_garage_auto.exceptions.DBException;
import fr.unilasalle.tp_garage_auto.exceptions.DTOException;
import fr.unilasalle.tp_garage_auto.exceptions.NotFoundException;
import fr.unilasalle.tp_garage_auto.exceptions.ServiceException;
import fr.unilasalle.tp_garage_auto.repositories.ClientRepository;
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

    /**
     * Récupérer tous les clients
     */
    public Set<Client> getAllClients() throws ServiceException {
        try {
            return new HashSet<>(clientRepository.findAll());
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la récupération des clients.");
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
            throw new NotFoundException("Impossible de trouver le client avec l'id " + id + ".");
        }
        return client;
    }


    /**
     * Récupérer les clients par nom
     * @param nom
     * @return
     */
    public Set<Client> getClientsByName(String nom) {
        return clientRepository.findByNomContainingIgnoreCase(nom);
    }

    /**
     * Récupérer les clients par prénom
     * @param prenom
     * @return
     */
    public Set<Client> getClientsBySurname(String prenom) {
        return clientRepository.findByPrenomContainingIgnoreCase(prenom);
    }

    /**
     * Récupérer les clients par email
     * @param email
     * @return
     */
    public Client getClientsByEmail(String email) {
        return clientRepository.findByEmailContainingIgnoreCase(email);
    }

    /**
     * Récupérer les clients par téléphone
     * @param telephone
     * @return
     */
    public Client getClientsByPhone(String telephone) {
        return clientRepository.findByTelephoneContainingIgnoreCase(telephone);
    }



    /**
     * Créer un client
     * @param client
     * @return
     */
    @Transactional
    public Client createClient(Client client) throws DBException, ServiceException {
        if (client == null) {
            throw new ServiceException("Le client ne peut pas être null.");
        }

        if(client.getId() != null){
            throw new ServiceException("Le client ne peut pas avoir d'id.");
        }

        try {
            return this.clientRepository.save(client);
        } catch (Exception e) {
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
            throw new ServiceException("Le client ne peut pas être null.");
        }

        if(id == null){
            throw new ServiceException("L'id du client ne peut pas être null.");
        }

        Client existingClient = this.clientRepository.findById(id).orElse(null);
        if (existingClient == null) {
            throw new NotFoundException("Impossible de trouver un client avec l'id " + client.getId() + ".");
        }

        existingClient.setNom(client.getNom());
        existingClient.setPrenom(client.getPrenom());
        existingClient.setTelephone(client.getTelephone());
        existingClient.setEmail(client.getEmail());
        existingClient.setVehicules(client.getVehicules());

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
            throw new NotFoundException("Impossible de trouver un client avec l'id " + id + ".");
        }

        try {
            this.clientRepository.delete(existingClient);
        } catch (DBException e) {
            throw new DBException("Erreur lors de la suppression du client avec l'id " + id, e);
        }
    }

}
