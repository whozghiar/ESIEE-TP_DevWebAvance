package BeansTests;

import fr.unilasalle.tp_garage_auto.beans.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {
    private Client client;

    @BeforeEach
    public void setUp() {
        client = new Client();
    }

    @Test
    public void testNom() {
        String nom = "DUPONT";
        client.setNom(nom);
        assertEquals(nom, client.getNom());
    }

    @Test
    public void testPrenom() {
        String prenom = "Lucien";
        client.setPrenom(prenom);
        assertEquals(prenom, client.getPrenom());
    }

    @Test
    public void testTelephone() {
        String telephone = "0634472514";
        client.setTelephone(telephone);
        assertEquals(telephone, client.getTelephone());
    }

    @Test
    public void testEmail() {
        String email = "luludupont@gmail.com";
        client.setEmail(email);
        assertEquals(email, client.getEmail());
    }
}