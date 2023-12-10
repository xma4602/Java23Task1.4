package com.xma.task2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ModeTest extends ArrayStatisticsTest {

    @Test
    void testModeEmpty() {
        ArrayStatistics stat = new ArrayStatistics(arrEmpty);
        assertThrows(IllegalStateException.class, () -> stat.mode(1));
    }

    @Test
    void testModeOne() {
        ArrayStatistics stat = new ArrayStatistics(arrOne);
        assertEquals(arrOne[0], stat.mode(1)[0]);
    }

    @Test
    void testModeTree() {
        ArrayStatistics stat = new ArrayStatistics(arrTree);
        assertEquals(1, stat.mode(1)[0]);
    }

    @Test
    void testModeTen() {
        ArrayStatistics stat = new ArrayStatistics(arrTen);
        assertEquals(13, stat.mode(1)[0]);
    }


    @Test
    void testModeBig() {
        ArrayStatistics stat = new ArrayStatistics(arrTen);
        stat.mode(3);
    }

}
