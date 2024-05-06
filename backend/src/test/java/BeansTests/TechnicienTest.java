package BeansTests;

import fr.unilasalle.tp_garage_auto.beans.Technicien;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TechnicienTest {
    private Technicien technicien;

    @BeforeEach
    public void setUp() {
        technicien = new Technicien();
    }

    @Test
    public void testNom() {
        String nom = "AUBRY";
        technicien.setNom(nom);
        assertEquals(nom, technicien.getNom());
    }

    @Test
    public void testPrenom() {
        String prenom = "Martine";
        technicien.setPrenom(prenom);
        assertEquals(prenom, technicien.getPrenom());
    }
}