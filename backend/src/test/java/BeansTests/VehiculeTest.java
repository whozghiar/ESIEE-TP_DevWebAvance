package BeansTests;

import fr.unilasalle.tp_garage_auto.beans.Vehicule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VehiculeTest {
    private Vehicule vehicule;

    @BeforeEach
    public void setUp() {
        vehicule = new Vehicule();
    }

    @Test
    public void testMarque() {
        String marque = "Renault";
        vehicule.setMarque(marque);
        assertEquals(marque, vehicule.getMarque());
    }

    @Test
    public void testModele() {
        String modele = "Clio";
        vehicule.setModele(modele);
        assertEquals(modele, vehicule.getModele());
    }

    @Test
    public void testImmatriculation() {
        String immatriculation = "AB-123-CD";
        vehicule.setImmatriculation(immatriculation);
        assertEquals(immatriculation, vehicule.getImmatriculation());
    }

    @Test
    public void testAnnee() {
        Integer annee = 2021;
        vehicule.setAnnee(annee);
        assertEquals(annee, vehicule.getAnnee());
    }
}