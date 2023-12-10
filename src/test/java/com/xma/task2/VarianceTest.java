package com.xma.task2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VarianceTest extends ArrayStatisticsTest {

    @Test
    void testVarianceEmpty() {
        ArrayStatistics stat = new ArrayStatistics(arrEmpty);
        assertThrows(IllegalStateException.class, stat::variance);
    }

    @Test
    void testVarianceOne() {
        ArrayStatistics stat = new ArrayStatistics(arrOne);
        assertEquals(0, stat.variance());
    }

    @Test
    void testVarianceTree() {
        ArrayStatistics stat = new ArrayStatistics(arrTree);
        assertEquals(2.666666666666667, stat.variance());
    }

    @Test
    void testVarianceTen() {
        ArrayStatistics stat = new ArrayStatistics(arrTen);
        assertEquals(499.6, stat.variance());
    }


    @Test
    void testVarianceBig() {
        ArrayStatistics stat = new ArrayStatistics(arrTen);
        stat.variance();
    }
}
