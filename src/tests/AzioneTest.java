package tests;

import static org.junit.jupiter.api.Assertions.*;

import main_structure.Azione;
import main_structure.MonitorRendimenti;
import main_structure.Titolo;
import observer_classes.Observer;
import org.junit.jupiter.api.Test;

import java.lang.reflect.*;
import java.util.ArrayList;

class AzioneTest {

    @Test
    double[] setUp(double maxVarPer, Observer o, double startValue) throws NoSuchFieldException, IllegalAccessException {
        Azione azione = new Azione(maxVarPer, o, startValue);
        Class a = azione.getClass();
        Field mIP = a.getDeclaredField("maxIncPer");
        mIP.setAccessible(true);
        double maxIncPer = (Double) mIP.get(azione);
        Field mDP = a.getDeclaredField("maxDecPer");
        mDP.setAccessible(true);
        double maxDecPer = (Double) mDP.get(azione);

        assertEquals(startValue, azione.getValue());

        double[] array = new double[2];
        array[0] = maxIncPer;
        array[1] = maxDecPer;
        return array;
    }

    @Test
    void testConstructor() throws NoSuchFieldException, IllegalAccessException {
        double maxVarPer = 3;
        Observer o = new MonitorRendimenti(new ArrayList<>());
        double startValue = 1000;
        double[] results = setUp(maxVarPer, o, startValue);
        assertTrue(results[0] <= 3 && results[0] >= 0);
        assertTrue(results[1] <= 3 && results[1] >= 0);
    }

    @Test
    void testUpdateValue(){
        double maxVarPer = 3;
        ArrayList<Titolo> titoli = new ArrayList<>();
        MonitorRendimenti o = new MonitorRendimenti(titoli);
        double startValue = 1000;
        Azione azione = new Azione(maxVarPer, o, startValue);
        titoli.add(azione);
        o.extendVariationsArray();
        azione.updateValue();
        assertTrue(azione.getValue() <= 1000 && azione.getValue() >= 970);
    }
}