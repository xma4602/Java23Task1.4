package com.xma.task2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MedianTest extends ArrayStatisticsTest{

    @Test
    void testMedianEmpty() {
        ArrayStatistics stat = new ArrayStatistics(arrEmpty);
        assertThrows(IllegalStateException.class, stat::median);
    }

    @Test
    void testMedianOne() {
        ArrayStatistics stat = new ArrayStatistics(arrOne);
        assertThrows(IllegalStateException.class, stat::median);
    }

    @Test
    void testMedianTree() {
        ArrayStatistics stat = new ArrayStatistics(arrTree);
        assertEquals(1, stat.median());
    }

    @Test
    void testMedianTen() {
        ArrayStatistics stat = new ArrayStatistics(arrTen);
        assertEquals(13, stat.median());
    }


    @Test
    void testMedianBig() {
        ArrayStatistics stat = new ArrayStatistics(arrTen);
        stat.median();
    }
}
