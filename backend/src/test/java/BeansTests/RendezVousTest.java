package BeansTests;

import static org.junit.jupiter.api.Assertions.*;

import fr.unilasalle.tp_garage_auto.beans.RendezVous;
import fr.unilasalle.tp_garage_auto.beans.Technicien;
import fr.unilasalle.tp_garage_auto.beans.Vehicule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;
import jakarta.validation.ConstraintViolation;

class RendezVousTest {
    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testRendezVousValidationSuccess() {
        RendezVous rendezVous = new RendezVous();
        rendezVous.setDate("01/01/2023"); // Format de date valide
        rendezVous.setTypeService("Réparation");

        Set<ConstraintViolation<RendezVous>> violations = validator.validate(rendezVous);
        assertTrue(violations.isEmpty(), "Le rendez-vous devrait passer toutes les contraintes de validation");
    }

    @Test
    public void testDateValidationFailure() {
        RendezVous rendezVous = new RendezVous();
        rendezVous.setDate("01-01-2023"); // Format de date incorrect

        Set<ConstraintViolation<RendezVous>> violations = validator.validate(rendezVous);
        assertFalse(violations.isEmpty(), "La date avec un format incorrect devrait échouer la validation");
    }

    @Test
    public void testTypeServiceValidationFailure() {
        RendezVous rendezVous = new RendezVous();
        rendezVous.setTypeService(""); // Type de service vide

        Set<ConstraintViolation<RendezVous>> violations = validator.validate(rendezVous);
        assertFalse(violations.isEmpty(), "Le type de service vide devrait échouer la validation");
    }

    @Test
    public void testRendezVousRelations() {
        RendezVous rendezVous = new RendezVous();
        rendezVous.setDate("01/01/2023");
        rendezVous.setTypeService("Réparation");

        Vehicule vehicule = new Vehicule();
        vehicule.setImmatriculation("AZ-BR");
        Technicien technicien = new Technicien();

        rendezVous.setVehicule(vehicule);
        rendezVous.setTechnicien(technicien);

        assertSame(vehicule, rendezVous.getVehicule(), "Le véhicule associé devrait être le même que celui défini");
        assertSame(technicien, rendezVous.getTechnicien(), "Le technicien associé devrait être le même que celui défini");
    }
}
