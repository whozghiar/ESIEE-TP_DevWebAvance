package fr.unilasalle.tp_garage_auto.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.unilasalle.tp_garage_auto.beans.Client;
import fr.unilasalle.tp_garage_auto.exceptions.DTOException;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class ClientDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String telephone;
    private String email;


    private Set<VehiculeDTO> vehicules; // liste des véhicules du client pour éviter la récursivité

    public static ClientDTO fromEntity(Client client) throws DTOException {
        // Si le client est null, on throw une exception
        if(client == null){
            throw new DTOException("Le client ne peut pas être null");
        }

        // On crée un nouveau clientDTO
        return ClientDTO.builder()
                .id(client.getId())
                .nom(client.getNom())
                .prenom(client.getPrenom())
                .telephone(client.getTelephone())
                .email(client.getEmail())
                .build();
    }

    public static Client toEntity(ClientDTO clientDTO) throws DTOException {
        // Si le clientDTO est null, on retourne null
        if(clientDTO == null){
            throw new DTOException("Le clientDTO ne peut pas être null");
        }

        // On crée un nouveau client
        return Client.builder()
                .id(clientDTO.getId())
                .nom(clientDTO.getNom())
                .prenom(clientDTO.getPrenom())
                .telephone(clientDTO.getTelephone())
                .email(clientDTO.getEmail())
                .build();
    }
}
