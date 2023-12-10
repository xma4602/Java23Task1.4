package com.xma.task2;

import org.junit.jupiter.api.Test;

public class SampleTest extends ArrayStatisticsTest {

    @Test
    void testSample() throws Exception {
        ArrayStatistics stat = new ArrayStatistics(arrBig);
        int[] result = stat.sample(500_000);
        int count = 0;
        for (int i = 0; i < result.length; i++) {
            int index = indexOf(result[i], arrBig);
            if (index < 0) throw new Exception("Неизвестный элемент массива");
            if (index == i) count++;
        }
        System.out.printf("Позиции элементов совпали %d раз(а)%n", count);
    }

    private int indexOf(int item, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == item) return i;
        }
        return -1;
    }

}
