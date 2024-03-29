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
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
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
     * Créer un client
     * @param clientDto
     * @return
     */
    public ClientDTO createClient(ClientDTO clientDto) throws NotFoundException, DBException, DTOException {
        if (clientDto == null) {
            throw new DTOException("ClientDTO cannot be null");
        }

        if (clientDto.getId() != null) {
            throw new DTOException("ClientDTO id must be null");
        }

        Client client = ClientDTO.toEntity(clientDto);


        try {
            client = this.clientRepository.save(client);
        } catch (Exception e) {
            throw new DBException("Error while saving client");
        }

        // @TODO : Gérer les véhicules associés
        if (clientDto.getVehicules() != null && !clientDto.getVehicules().isEmpty()) {
            for (VehiculeDTO vehiculeDTO : clientDto.getVehicules()) {
                vehiculeDTO.setClientId(client.getId());
                vehiculeService.createVehicule(vehiculeDTO);
            }
        }

        return ClientDTO.fromEntity(client);
    }

    /**
     * Mettre à jour un client
     * @param clientDto
     * @return
     */
    @Transactional
    public ClientDTO updateClient(ClientDTO clientDto) throws NotFoundException, DBException, DTOException {
        if (clientDto == null) {
            throw new DTOException("ClientDTO cannot be null");
        }

        if (clientDto.getId() == null) {
            throw new DTOException("id must not be null");
        }

        Client client = ClientDTO.toEntity(clientDto);

        // Créer ou mettre à jour les véhicules associés au client et les attacher à l'entité client
        if (clientDto.getVehicules() != null && !clientDto.getVehicules().isEmpty()) {
            // On crée ou met à jour les véhicules avant de les attacher au client
            client.setVehicules(clientDto.getVehicules().stream().map(vehiculeDTO -> {
                // Ici, on assume que createVehicule prend soin de l'association client<->véhicule
                VehiculeDTO savedVehiculeDTO = null;
                try {
                    // Vérifier que le véhicule n'existe pas déjà
                    if (vehiculeDTO.getId() != null) {
                        try {
                            savedVehiculeDTO = vehiculeService.updateVehicule(vehiculeDTO);
                        } catch (NotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }else{
                        savedVehiculeDTO = vehiculeService.createVehicule(vehiculeDTO);
                    }
                } catch (NotFoundException | DTOException e) {
                    throw new RuntimeException(e);
                }
                // On convertit le VehiculeDTO de retour en entité Vehicule pour l'attacher au client
                try {
                    return VehiculeDTO.toEntity(savedVehiculeDTO);
                } catch (DTOException e) {
                    throw new RuntimeException(e);
                }
            }).collect(Collectors.toList()));
        }



        try {
            client = this.clientRepository.save(client);
        } catch (Exception e) {
            throw new DBException("Error while saving client");
        }


        return ClientDTO.fromEntity(client);

    }



    /**
     * Supprimer un client
     * @param id
     */
    @Transactional
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
