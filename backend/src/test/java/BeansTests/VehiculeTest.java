package BeansTests;

import static org.junit.jupiter.api.Assertions.*;

import fr.unilasalle.tp_garage_auto.beans.Client;
import fr.unilasalle.tp_garage_auto.beans.RendezVous;
import fr.unilasalle.tp_garage_auto.beans.Vehicule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.HashSet;
import java.util.Set;
import jakarta.validation.ConstraintViolation;

class VehiculeTest {
    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testVehiculeValidationSuccess() {
        Vehicule vehicule = new Vehicule();
        vehicule.setMarque("Toyota");
        vehicule.setModele("Corolla");
        vehicule.setImmatriculation("AA-123-AA");
        vehicule.setAnnee(2020);

        Set<ConstraintViolation<Vehicule>> violations = validator.validate(vehicule);
        assertTrue(violations.isEmpty(), "Le véhicule devrait passer toutes les contraintes de validation");
    }

    @Test
    public void testImmatriculationValidationFailure() {
        Vehicule vehicule = new Vehicule();
        vehicule.setImmatriculation("1234ABCD"); // Format d'immatriculation incorrect

        Set<ConstraintViolation<Vehicule>> violations = validator.validate(vehicule);
        assertFalse(violations.isEmpty(), "L'immatriculation avec un format incorrect devrait échouer la validation");
    }

    @Test
    public void testAnneeValidationFailure() {
        Vehicule vehicule = new Vehicule();
        vehicule.setAnnee(123); // Année incorrecte

        Set<ConstraintViolation<Vehicule>> violations = validator.validate(vehicule);
        assertFalse(violations.isEmpty(), "L'année avec une valeur incorrecte devrait échouer la validation");
    }

    @Test
    public void testVehiculeRelations() {
        Vehicule vehicule = new Vehicule();
        vehicule.setMarque("Toyota");
        vehicule.setModele("Corolla");
        vehicule.setImmatriculation("AA-123-AA");
        vehicule.setAnnee(2020);

        Client client = new Client();
        vehicule.setClient(client);

        RendezVous rendezVous = new RendezVous();
        Set<RendezVous> rendezVousSet = new HashSet<>();
        rendezVousSet.add(rendezVous);
        vehicule.setRendezVous(rendezVousSet);

        assertSame(client, vehicule.getClient(), "Le client associé devrait être le même que celui défini");
        assertEquals(rendezVousSet, vehicule.getRendezVous(), "Les rendez-vous associés devraient être les mêmes que ceux définis");
    }
}
