package BeansTests;
import static org.junit.jupiter.api.Assertions.*;

import fr.unilasalle.tp_garage_auto.beans.RendezVous;
import fr.unilasalle.tp_garage_auto.beans.Technicien;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.HashSet;
import java.util.Set;
import jakarta.validation.ConstraintViolation;

class TechnicienTest {
    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testTechnicienValidationSuccess() {
        Technicien technicien = new Technicien();
        technicien.setNom("Dupont");
        technicien.setPrenom("Jean");

        Set<ConstraintViolation<Technicien>> violations = validator.validate(technicien);
        assertTrue(violations.isEmpty(), "Le technicien devrait passer toutes les contraintes de validation");
    }

    @Test
    public void testNomValidationFailure() {
        Technicien technicien = new Technicien();
        technicien.setNom(""); // Nom vide

        Set<ConstraintViolation<Technicien>> violations = validator.validate(technicien);
        assertFalse(violations.isEmpty(), "Le nom vide devrait échouer la validation");
    }

    @Test
    public void testPrenomValidationFailure() {
        Technicien technicien = new Technicien();
        technicien.setPrenom(""); // Prénom vide

        Set<ConstraintViolation<Technicien>> violations = validator.validate(technicien);
        assertFalse(violations.isEmpty(), "Le prénom vide devrait échouer la validation");
    }

    @Test
    public void testTechnicienRendezVousRelationship() {
        Technicien technicien = new Technicien();
        technicien.setNom("Dupont");
        technicien.setPrenom("Jean");

        RendezVous rendezVous = new RendezVous();
        Set<RendezVous> rendezVousSet = new HashSet<>();
        rendezVousSet.add(rendezVous);

        technicien.setRendezVous(rendezVousSet);

        assertSame(rendezVousSet, technicien.getRendezVous(), "Les rendez-vous associés devraient être les mêmes que ceux définis");
    }
}
