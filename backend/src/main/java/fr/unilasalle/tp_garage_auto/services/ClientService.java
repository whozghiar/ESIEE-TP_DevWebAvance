package fr.unilasalle.tp_garage_auto.services;

// ------ Imports Beans ------
import fr.unilasalle.tp_garage_auto.DTO.VehiculeDTO;
import fr.unilasalle.tp_garage_auto.beans.Client;
// ------ Imports DTO ------
import fr.unilasalle.tp_garage_auto.DTO.ClientDTO;
// ------ Imports Exceptions ------
import fr.unilasalle.tp_garage_auto.beans.Vehicule;
import fr.unilasalle.tp_garage_auto.exceptions.DBException;
import fr.unilasalle.tp_garage_auto.exceptions.DTOException;
import fr.unilasalle.tp_garage_auto.exceptions.NotFoundException;

// ------ Imports Repositories ------
import fr.unilasalle.tp_garage_auto.exceptions.ServiceException;
import fr.unilasalle.tp_garage_auto.repositories.ClientRepository;

import fr.unilasalle.tp_garage_auto.repositories.VehiculeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService {

    // INJECTION DU SERVICE VEHICULE
    private final VehiculeService vehiculeService;

    private final ClientRepository clientRepository;

    /**
     * Récupérer tous les clients
     */
    public List<Client> getAllClients() {
        return clientRepository.findAll();
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
    public Client updateClient(Client client) throws NotFoundException, DBException, ServiceException {
        if (client == null) {
            throw new ServiceException("Le client ne peut pas être null.");
        }

        if (client.getId() == null) {
            throw new ServiceException("Le client doit avoir un id.");
        }

        Client existingClient = this.clientRepository.findById(client.getId()).orElse(null);
        if (existingClient == null) {
            throw new NotFoundException("Impossible de trouver un client avec l'id " + client.getId() + ".");
        }

        try {
            return this.clientRepository.save(client);
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
