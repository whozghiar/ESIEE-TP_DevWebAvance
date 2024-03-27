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
import fr.unilasalle.tp_garage_auto.repositories.ClientRepository;

import fr.unilasalle.tp_garage_auto.repositories.VehiculeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {

    // INJECTION DU SERVICE VEHICULE
    private final VehiculeService vehiculeService;

    private final ClientRepository clientRepository;

    /**
     * Récupérer tous les clients
     */
    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream()
                .map(client -> {
                    try {
                        return ClientDTO.fromEntity(client);
                    } catch (DTOException e) {
                        // Gérer l'exception
                        return null;
                    }
                })
                .collect(Collectors.toList());
    }


    /**
     * Créer ou mettre à jour un client
     * @param clientDto
     * @return
     */
    public ClientDTO updateClient(ClientDTO clientDto) throws NotFoundException, DBException, DTOException {
        return null;
    }



    /**
     * Supprimer un client
     * @param id
     */
    public void deleteClient(Long id) throws NotFoundException, DBException {
        Client existingClient = this.clientRepository.findById(id).orElse(null);
        if (existingClient == null) {
            throw new NotFoundException("Could not find client with id " + id);
        }

        try {
            this.clientRepository.delete(existingClient);
        } catch (DBException e) {
            throw new DBException("Error while deleting client with id " + id);
        }
    }

}
