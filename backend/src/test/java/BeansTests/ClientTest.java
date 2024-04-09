package BeansTests;

import static org.junit.jupiter.api.Assertions.*;

import fr.unilasalle.tp_garage_auto.beans.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;
import jakarta.validation.ConstraintViolation;

class ClientTest {
    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testClientValidationSuccess() {
        Client client = new Client();
        client.setNom("Dupont");
        client.setPrenom("Jean");
        client.setTelephone("0123456789");
        client.setEmail("jean.dupont@example.com");

        Set<ConstraintViolation<Client>> violations = validator.validate(client);
        assertTrue(violations.isEmpty(), "Le client devrait passer toutes les contraintes de validation");
    }

    @Test
    public void testNomValidationFailures() {
        Client client = new Client();
        client.setNom(""); // Nom vide
        client.setPrenom("Jean");
        client.setTelephone("0123456789");
        client.setEmail("jean.dupont@example.com");

        Set<ConstraintViolation<Client>> violations = validator.validate(client);
        assertFalse(violations.isEmpty(), "Le nom vide devrait échouer la validation");

    }


    @Test
    public void testTelephoneValidationFailure() {
        Client client = new Client();
        client.setNom("Dupont");
        client.setPrenom("Jean");
        client.setTelephone("abcdefghij"); // Téléphone invalide
        client.setEmail("jean.dupont@example.com");

        Set<ConstraintViolation<Client>> violations = validator.validate(client);
        assertFalse(violations.isEmpty(), "Le téléphone invalide devrait échouer la validation");
    }

    @Test
    public void testEmailValidationFailure() {
        Client client = new Client();
        client.setNom("Dupont");
        client.setPrenom("Jean");
        client.setTelephone("0123456789");
        client.setEmail("jean.dupont"); // Email invalide

        Set<ConstraintViolation<Client>> violations = validator.validate(client);
        assertFalse(violations.isEmpty(), "L'email invalide devrait échouer la validation");
    }
}
