package tests;

import main_structure.Azione;
import main_structure.MonitorRendimenti;
import main_structure.Titolo;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MonitorRendimentiTest {

    @Test
    void initTest() throws NoSuchFieldException, IllegalAccessException{
        ArrayList<Titolo> titoli = new ArrayList<>();
        MonitorRendimenti monitorRendimenti = new MonitorRendimenti(titoli);
        Azione a1 = new Azione(4, monitorRendimenti, 1000);
        Azione a2 = new Azione(3, monitorRendimenti, 800);
        titoli.add(a1);
        titoli.add(a2);
        monitorRendimenti.extendVariationsArray();
        monitorRendimenti.extendVariationsArray();

        // monitoro due azioni

        Class m = monitorRendimenti.getClass();
        Field aV = m.getDeclaredField("variations");
        aV.setAccessible(true);
        ArrayList<Double> variations = (ArrayList<Double>)aV.get(monitorRendimenti);

        assertEquals(variations.size(), 2);
        assertEquals(titoli.size(), 2);

        resetVariationsTest(monitorRendimenti, variations);
    }

    @Test
    void resetVariationsTest(MonitorRendimenti monitorRendimenti, ArrayList<Double> variations){
        variations.set(0, 3.2);
        assertEquals(variations.get(0), 3.2);
        monitorRendimenti.resetVariation(0);
        assertEquals(variations.get(0), 0);
        variations.set(0, 8.5);
        variations.set(1, 5.4);

    }

}