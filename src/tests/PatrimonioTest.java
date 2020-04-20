package tests;

import com.company.Patrimonio;
import org.junit.jupiter.api.Test;

import java.lang.reflect.*;
import static org.junit.jupiter.api.Assertions.*;


class PatrimonioTest {

    double[] setUp(double amount, double pR)throws NoSuchFieldException, IllegalAccessException{
        Patrimonio patrimonio = new Patrimonio(amount, pR);
        Class p = patrimonio.getClass();
        Field risk = p.getDeclaredField("risk");
        risk.setAccessible(true);
        double r = (Double) risk.get(patrimonio);
        Field totalAmount = p.getDeclaredField("totalAmount");
        totalAmount.setAccessible(true);
        double a = (Double) totalAmount.get(patrimonio);

        double[] array = new double[2];
        array[0] = a;
        array[1] = r;
        return array;
    }

    @Test
    void testConstructor() throws NoSuchFieldException, IllegalAccessException{
        double amount = 1000.0;
        double pR = 5.0;
        double[] results = setUp(amount, pR);
        assertEquals(results[1], 5);
        assertEquals(results[0], 1000);

        amount = -20.0;
        pR = 0;
        results = setUp(amount, pR);
        assertEquals(results[1], 0.5);
        assertEquals(results[0], 100);

        amount = 100;
        pR = 30;
        results = setUp(amount, pR);
        assertEquals(results[1], 10);
        assertEquals(results[0], 100);
    }

}