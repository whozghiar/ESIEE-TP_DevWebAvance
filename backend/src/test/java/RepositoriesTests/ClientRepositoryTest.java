package RepositoriesTests;

import fr.unilasalle.tp_garage_auto.TpGarageAutoApplication;
import fr.unilasalle.tp_garage_auto.beans.Client;
import fr.unilasalle.tp_garage_auto.repositories.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(classes = TpGarageAutoApplication.class)
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    void shouldFindClientByIdWhenClientExists() {
        // Given
        Client client = new Client();
        client.setNom("DUPONT");
        client.setPrenom("Lucien");
        client.setEmail("luludupont@gmail.com");
        client.setTelephone("0634472514");
        Client savedClient = clientRepository.save(client);

        // When
        Client foundClient = clientRepository.findById(savedClient.getId()).orElse(null);

        // Then
        assertThat(foundClient).isNotNull();
    }

    @Test
    void shouldReturnNullWhenClientDoesNotExist() {
        // Given
        // No client saved

        // When
        Client foundClient = clientRepository.findById(1L).orElse(null);

        // Then
        assertThat(foundClient).isNull();
    }

    @Test
    void shouldSaveClientSuccessfully() {
        // Given
        Client client = new Client();
        client.setNom("DUPONT");
        client.setPrenom("Lucien");
        client.setEmail("luludupont@gmail.com");
        client.setTelephone("0634472514");

        // When
        Client savedClient = clientRepository.save(client);

        // Then
        assertThat(savedClient).isNotNull();
    }
}
