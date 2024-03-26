package fr.unilasalle.tp_garage_auto.services;

// ------ Imports Beans ------
import fr.unilasalle.tp_garage_auto.DTO.VehiculeDTO;
import fr.unilasalle.tp_garage_auto.beans.Client;
// ------ Imports DTO ------
import fr.unilasalle.tp_garage_auto.DTO.ClientDTO;
// ------ Imports Exceptions ------
import fr.unilasalle.tp_garage_auto.exceptions.DBException;
import fr.unilasalle.tp_garage_auto.exceptions.NotFoundException;

// ------ Imports Repositories ------
import fr.unilasalle.tp_garage_auto.repositories.ClientRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    /**
     * Convertir une entité Client en ClientDTO
     */
    private ClientDTO convertToDTO(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setNom(client.getNom());
        dto.setPrenom(client.getPrenom());
        dto.setTelephone(client.getTelephone());
        dto.setEmail(client.getEmail());
        // Vérifier si la liste des véhicules est null
        if (client.getVehicules() != null) {
            dto.setVehicules(client.getVehicules().stream()
                    .map(v -> new VehiculeDTO(v.getId(), v.getMarque(), v.getModele(), v.getImmatriculation(), v.getAnnee()))
                    .collect(Collectors.toList()));
        } else {
            // Initialiser avec une liste vide si null
            dto.setVehicules(new ArrayList<>());
        }
        return dto;
    }


    /**
     * Récupérer tous les clients
     */
    public List<ClientDTO> getAllClients() {
        return this.clientRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    /**
     * Créer ou mettre à jour un client
     * @param clientDto
     * @return
     */
    public ClientDTO updateClient(ClientDTO clientDto) throws NotFoundException, DBException {
        Client existingClient = null;

        if (clientDto.getId() != null) {
            existingClient = this.clientRepository.findById(clientDto.getId()).orElse(null);
            if(existingClient == null) {
                throw new NotFoundException("Could not find client with id " + clientDto.getId());
            }

        } else {
            existingClient = new Client();
        }

        existingClient.setNom(clientDto.getNom());
        existingClient.setPrenom(clientDto.getPrenom());
        existingClient.setTelephone(clientDto.getTelephone());
        existingClient.setEmail(clientDto.getEmail());


        try{
            Client updatedClient = this.clientRepository.save(existingClient);
            return this.convertToDTO(updatedClient);

        } catch (Exception e) {
            throw new DBException("Error while saving client");
        }
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
