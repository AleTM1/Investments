package tests;

import main_structure.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PortafoglioTest {

    @Test
    void initTest()throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Portafoglio portafoglio = new Portafoglio(3.0, 8, 12);

        Class p = portafoglio.getClass();
        Field r = p.getDeclaredField("root");
        r.setAccessible(true);
        boolean root = (boolean) r.get(portafoglio);

        assertTrue(root);

        Field monitor = p.getDeclaredField("monitorRendimenti");
        monitor.setAccessible(true);
        MonitorRendimenti monitorRendimenti = (MonitorRendimenti) monitor.get(portafoglio);

        Field aB = p.getDeclaredField("builder");
        aB.setAccessible(true);
        AzioneBuilder azioneBuilder = (AzioneBuilder) aB.get(portafoglio);

        getIdTest(portafoglio, 0);

        addTitoloTest(portafoglio);

        generateAzioneTest(portafoglio);

        int size0 = portafoglio.getArrayTitoli().size();
        Method winUp = p.getDeclaredMethod("winUpgrade");
        winUp.setAccessible(true);
        winUp.invoke(portafoglio);

        assertEquals(portafoglio.getArrayTitoli().size(), size0 + 2);

        upgradeNResetTest(portafoglio, monitorRendimenti, azioneBuilder);

        lossAnalisysTest();
    }


    void getIdTest(Portafoglio portafoglio, int n) {
        assertEquals(portafoglio.getId(), n);
    }


    void addTitoloTest(Portafoglio portafoglio) {
        Azione a1 = portafoglio.generateAzione(1200);
        Azione a2 = portafoglio.generateAzione(2004.5);
        portafoglio.addTitolo(a1);
        portafoglio.addTitolo(a2);
        assertEquals(portafoglio.getArrayTitoli().get(0), a1);
        assertEquals(portafoglio.getArrayTitoli().get(1), a2);

        assertEquals(portafoglio.getValue(), 3204.5);
    }


    void generateAzioneTest(Portafoglio portafoglio) {
        portafoglio.addTitolo(portafoglio.generateAzione(978.3));
        portafoglio.addTitolo(portafoglio.generateAzione(1402));
        assertEquals(portafoglio.getArrayTitoli().get(2).getValue(), 978.3);
        assertEquals(portafoglio.getArrayTitoli().get(3).getValue(), 1402);
    }


    void upgradeNResetTest(Portafoglio portafoglio, MonitorRendimenti monitorRendimenti, AzioneBuilder builder) throws  NoSuchFieldException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Portafoglio portafoglio1 = (Portafoglio) portafoglio.getArrayTitoli().get(portafoglio.getArrayTitoli().size() - 1);
        assertEquals(portafoglio1.getId(), 2);

        Class p1 = portafoglio1.getClass();

        Field aB = p1.getDeclaredField("builder");
        aB.setAccessible(true);
        AzioneBuilder azioneBuilder1 = (AzioneBuilder) aB.get(portafoglio1);

        ArrayList<Titolo> titoli = portafoglio.getArrayTitoli();

        assertEquals(azioneBuilder1, builder);
        assertTrue(titoli.contains(portafoglio1));

        //reset test

        Class m = monitorRendimenti.getClass();
        Field aV = m.getDeclaredField("variations");
        aV.setAccessible(true);
        ArrayList<Double> variations = (ArrayList<Double>)aV.get(monitorRendimenti);

        Field monitor1 = p1.getDeclaredField("monitorRendimenti");
        monitor1.setAccessible(true);
        MonitorRendimenti monitorRendimenti1 = (MonitorRendimenti) monitor1.get(portafoglio1);
        Class m1 = monitorRendimenti1.getClass();
        Field aV1 = m1.getDeclaredField("variations");
        aV1.setAccessible(true);
        ArrayList<Double> variations1 = (ArrayList<Double>)aV1.get(monitorRendimenti1);

        variations.set(0, 5.4);
        variations.set(1, -3.4);
        variations1.set(0, 9.7);
        variations1.set(1, 6.8);

        assertEquals(variations.get(0), 5.4);
        assertEquals(variations.get(1), -3.4);
        assertEquals(variations1.get(0), 9.7);
        assertEquals(variations1.get(1), 6.8);

        Class p = portafoglio.getClass();
        Method resAll = p.getDeclaredMethod("resetAll");
        resAll.setAccessible(true);
        resAll.invoke(portafoglio);

        assertEquals(variations.get(0), 0);
        assertEquals(variations.get(1), 0);
        assertEquals(variations1.get(0), 0);
        assertEquals(variations1.get(1), 0);

    }


    void lossAnalisysTest()throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
        Portafoglio portafoglio = new Portafoglio(3.0, 8, 12);
        Azione a1 = portafoglio.generateAzione(1200);
        Azione a2 = portafoglio.generateAzione(2004.5);
        portafoglio.addTitolo(a1);
        portafoglio.addTitolo(a2);
        ArrayList<Titolo> titoli = portafoglio.getArrayTitoli();

        Class p = portafoglio.getClass();
        Field monitor = p.getDeclaredField("monitorRendimenti");
        monitor.setAccessible(true);
        Class m = ((MonitorRendimenti) monitor.get(portafoglio)).getClass();
        Field aV = m.getDeclaredField("variations");
        aV.setAccessible(true);
        ArrayList<Double> variations = (ArrayList<Double>)aV.get(monitor.get(portafoglio));
        variations.set(0, -100.5);
        variations.set(1, 80.3);
        Method lossAn = p.getDeclaredMethod("lossAnalisys");
        lossAn.setAccessible(true);
        lossAn.invoke(portafoglio);

        assertNotEquals(a1, titoli.get(0));
        assertEquals(a2, titoli.get(1));
    }
}


