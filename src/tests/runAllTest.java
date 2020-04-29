package tests;

import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;

class RunAllTests {

    @Test
    void runTests() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        AzioneBuilderTest azioneBuilderTest = new AzioneBuilderTest();
        azioneBuilderTest.AzioneBuilderTests();

        AzioneTest azioneTest = new AzioneTest();
        azioneTest.testConstructor();
        azioneTest.testUpdateValue();

        ClockTestSingleton clockTestSingleton = new ClockTestSingleton();
        clockTestSingleton.getInstanceTest();

        MonitorRendimentiTest monitorRendimentiTest = new MonitorRendimentiTest();
        monitorRendimentiTest.initTest();

        PatrimonioTest patrimonioTest = new PatrimonioTest();
        patrimonioTest.testConstructor();

        PortafoglioTest portafoglioTest = new PortafoglioTest();
        portafoglioTest.initTest();
    }
}


