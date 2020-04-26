package tests;

import com.company.Patrimonio;
import org.junit.jupiter.api.Test;

import java.lang.reflect.*;
import static org.junit.jupiter.api.Assertions.*;


class PatrimonioTest {

    double setUp(double amount, double pR)throws NoSuchFieldException, IllegalAccessException{
        Patrimonio patrimonio = new Patrimonio(amount, pR);
        Class p = patrimonio.getClass();
        Field risk = p.getDeclaredField("risk");
        risk.setAccessible(true);
        double a = amount > 100 ? amount : 100;
        assertEquals(a, patrimonio.getTotalAmount());
        return (Double) risk.get(patrimonio);
    }

    @Test
    void testConstructor() throws NoSuchFieldException, IllegalAccessException{
        double amount = 1000.0;
        double pR = 5.0;
        double result = setUp(amount, pR);
        assertEquals(result, 5);

        amount = -20.0;
        pR = 0;
        result = setUp(amount, pR);
        assertEquals(result, 0.5);

        amount = 100;
        pR = 30;
        result = setUp(amount, pR);
        assertEquals(result, 10);

        Patrimonio patrimonio = new Patrimonio(amount, pR);
        double final_amount = patrimonio.startAutoMenagement(-1, 5, 10);
        assertEquals(final_amount, amount);
        final_amount = patrimonio.startAutoMenagement(3, 5, 10);
        assertEquals(final_amount, amount);
        final_amount = patrimonio.startAutoMenagement(10, 15, 10);
        assertEquals(final_amount, amount);
        final_amount = patrimonio.startAutoMenagement(30, 5, 0);
        assertEquals(final_amount, amount);
    }
}