package BeansTests;

import fr.unilasalle.tp_garage_auto.beans.RendezVous;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RendezVousTest {
    private RendezVous rendezVous;

    @BeforeEach
    public void setUp() {
        rendezVous = new RendezVous();
    }

    @Test
    public void testDate() {
        String date = "01/01/2024";
        rendezVous.setDate(date);
        assertEquals(date, rendezVous.getDate());
    }

    @Test
    public void testTypeService() {
        String typeService = "Révision";
        rendezVous.setTypeService(typeService);
        assertEquals(typeService, rendezVous.getTypeService());
    }
}