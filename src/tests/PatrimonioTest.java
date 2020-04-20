package tests;

import com.company.Patrimonio;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.*;

class PatrimonioTest {

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException{
        Patrimonio patrimonio = new Patrimonio(1000, 5);
        Class p = patrimonio.getClass();
        Field risk = p.getDeclaredField("risk");
        risk.setAccessible(true);
        double r = (double)risk.get(patrimonio);
        System.out.println(r);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getTotalAmount() {
    }

    @Test
    void startAutomaticMenagement() {
    }
}