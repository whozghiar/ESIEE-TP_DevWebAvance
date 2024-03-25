package fr.unilasalle.tp_garage_auto.services;

import fr.unilasalle.tp_garage_auto.beans.Client;
import fr.unilasalle.tp_garage_auto.exceptions.DBException;
import fr.unilasalle.tp_garage_auto.exceptions.NotFoundException;
import fr.unilasalle.tp_garage_auto.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    /**
     * Récupérer tous les clients
     * @return
     */
    public List<Client> getAllClients() {
        return this.clientRepository.findAll();
    }

    /**
     * Créer ou mettre à jour un client
     * @param client
     * @return
     */
    public Client updateClient(Client client) throws NotFoundException, DBException {
        Client existingClient = null;

        if (client.getId() != null) {
            // Modifier un client existant
            existingClient = this.clientRepository.findById(client.getId()).orElse(null);
            if(existingClient == null) {
                throw new NotFoundException("Could not find client with id " + client.getId());
            }
        } else {
            existingClient = new Client();
        }

        existingClient.setNom(client.getNom());
        existingClient.setPrenom(client.getPrenom());
        existingClient.setTelephone(client.getTelephone());
        existingClient.setEmail(client.getEmail());

        try {
            return this.clientRepository.save(existingClient);
        } catch (DBException e) {
            throw new DBException("Error while updating client");
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
