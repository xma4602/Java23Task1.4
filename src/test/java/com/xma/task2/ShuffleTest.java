package com.xma.task2;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShuffleTest extends ArrayStatisticsTest {

    @Test
    void testShuffle() {
        ArrayStatistics stat = new ArrayStatistics(arrBig);
        int[] result = stat.shuffle();

        Map<Integer, Integer> nums1 = new TreeMap<>();
        for (var item : arrBig) {
            nums1.merge(item, 1, (x, y) -> x + 1);
        }
        Map<Integer, Integer> nums2 = new TreeMap<>();
        for (var item : result) {
            nums2.merge(item, 1, (x, y) -> x + 1);
        }

        assertEquals(nums1, nums2);
    }

}
