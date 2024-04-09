package ServicesTests;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.unilasalle.tp_garage_auto.beans.Client;
import fr.unilasalle.tp_garage_auto.beans.Vehicule;
import org.junit.Test;
;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class ClientServiceTest {

    @Test
    public void bidirectionalAssociationTest() throws JsonProcessingException {
        Client client = new Client();
        client.setEmail("toto@mail.com");
        client.setNom("toto");
        client.setPrenom("tata");
        client.setTelephone("0606060606");
        client.setVehicules(new HashSet<>());

        Vehicule vehicule = new Vehicule();
        vehicule.setAnnee(2020);
        vehicule.setImmatriculation("AA-123-BB");
        vehicule.setMarque("Renault");
        vehicule.setModele("Clio");
        vehicule.setClient(client);

        client.getVehicules().add(vehicule);

        final String vehiculeJson = new ObjectMapper().writeValueAsString(vehicule);
        final String clientJson = new ObjectMapper().writeValueAsString(client);

        System.out.println("vehiculeJson = " + vehiculeJson);
        System.out.println("clientJson = " + clientJson);

        assertTrue(vehiculeJson.contains("marque"));
        assertFalse(vehiculeJson.contains("toto"));

        assertTrue(clientJson.contains("marque"));
    }
}
