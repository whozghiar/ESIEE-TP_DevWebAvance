package fr.unilasalle.tp_garage_auto.services;

import fr.unilasalle.tp_garage_auto.DTO.ClientDTO;
import fr.unilasalle.tp_garage_auto.beans.Client;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MapperService {
    private ModelMapper modelMapper;

    public MapperService() {
        this.modelMapper = new ModelMapper();
    }

    public ClientDTO convertToDto(Client client) {
        return modelMapper.map(client, ClientDTO.class);
    }

    public Client convertToEntity(ClientDTO clientDTO) {
        return modelMapper.map(clientDTO, Client.class);
    }
}