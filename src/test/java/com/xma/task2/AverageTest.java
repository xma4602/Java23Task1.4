package com.xma.task2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AverageTest extends ArrayStatisticsTest {

    @Test
    void testAverageEmpty() {
        ArrayStatistics stat = new ArrayStatistics(arrEmpty);
        assertThrows(IllegalStateException.class, stat::average);
    }

    @Test
    void testAverageOne() {
        ArrayStatistics stat = new ArrayStatistics(arrOne);
        assertEquals(arrOne[0], stat.average());
    }

    @Test
    void testAverageTree() {
        ArrayStatistics stat = new ArrayStatistics(arrTree);
        assertEquals(5.0 / 3, stat.average());
    }

    @Test
    void testAverageTen() {
        ArrayStatistics stat = new ArrayStatistics(arrTen);
        assertEquals(11.2, stat.average());
    }


    @Test
    void testAverageBig() {
        ArrayStatistics stat = new ArrayStatistics(arrTen);
        stat.average();
    }
}
