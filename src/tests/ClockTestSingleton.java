package tests;

import com.company.Clock;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class ClockTestSingleton {

    @Test
    void getInstanceTest() throws NoSuchFieldException, IllegalAccessException {
        Clock clock = Clock.getInstance(10);
        Clock clock1 = Clock.getInstance(3);
        assertEquals(clock, clock1);

        Class c = clock.getClass();
        Field maxtick = c.getDeclaredField("maxTick");
        maxtick.setAccessible(true);

        assertEquals(10, maxtick.get(clock));
    }
}



