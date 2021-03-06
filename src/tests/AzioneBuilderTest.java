package tests;

import main_structure.Azione;
import main_structure.AzioneBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AzioneBuilderTest {

    @Test
    void AzioneBuilderTests() throws NoSuchFieldException, IllegalAccessException{
        AzioneBuilder azioneBuilder = new AzioneBuilder(4);
        azioneBuilder.setStartingValue(2000);   // tale valore verrà verificato nei test di Azione
        Azione azione = azioneBuilder.getResult();
        AzioneTest azioneTest = new AzioneTest();
        double[] results = azioneTest.setUpClass(azione);
        assertTrue(results[0] <= 4 && results[0] >= 0);
        assertTrue(results[1] <= 4 && results[1] >= 0);
    }
}




