package fr.unilasalle.tp_garage_auto.controllers;

import fr.unilasalle.tp_garage_auto.DTO.ClientDTO;
import fr.unilasalle.tp_garage_auto.beans.Client;
import fr.unilasalle.tp_garage_auto.exceptions.DBException;
import fr.unilasalle.tp_garage_auto.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fr.unilasalle.tp_garage_auto.services.ClientService;

import java.util.List;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
@Slf4j
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getClient() {
        log.info("Getting all clients ...");
        List<ClientDTO> clients = this.clientService.getAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> postClient(@RequestBody ClientDTO clientDto) {
        try {
            log.info("Creating or updating client ...");
            ClientDTO savedClientDto = this.clientService.updateClient(clientDto);
            HttpStatus status = clientDto.getId() == null ? HttpStatus.CREATED : HttpStatus.ACCEPTED;
            return new ResponseEntity<>(savedClientDto, status);
        } catch (DBException e) {
            log.error("Error while creating or updating client", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            log.error("Could not find client with id " + clientDto.getId(), e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        try {
            log.info("Deleting light with id " + id);
            this.clientService.deleteClient(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundException e) {
            log.error("Could not find light with id " + id, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DBException e) {
            log.error("Error while deleting light with id " + id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
