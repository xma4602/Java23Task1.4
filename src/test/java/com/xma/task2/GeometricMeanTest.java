package com.xma.task2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GeometricMeanTest extends ArrayStatisticsTest{

    @Test
    void testGeometricMeanEmpty() {
        ArrayStatistics stat = new ArrayStatistics(arrEmpty);
        assertThrows(IllegalStateException.class, stat::geometricMean);
    }

    @Test
    void testGeometricMeanOne() {
        ArrayStatistics stat = new ArrayStatistics(arrOne);
        assertEquals(arrOne[0], stat.geometricMean());
    }

    @Test
    void testGeometricMeanTree() {
        ArrayStatistics stat = new ArrayStatistics(arrTree);
        assertEquals(1.4422495703074083, stat.geometricMean());
    }

    @Test
    void testGeometricMeanTen() {
        ArrayStatistics stat = new ArrayStatistics(arrTen);
        assertEquals(7.965445976266273, stat.geometricMean());
    }


    @Test
    void testGeometricMeanBig() {
        ArrayStatistics stat = new ArrayStatistics(arrTen);
        stat.geometricMean();
    }

}
