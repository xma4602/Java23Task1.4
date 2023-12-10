package com.xma.task2;

import org.junit.jupiter.api.BeforeEach;

import java.util.Random;

public class ArrayStatisticsTest {
    int[] arrEmpty;
    int[] arrOne;
    int[] arrTree;
    int[] arrTen;
    int[] arrBig;
    Random random = new Random(System.currentTimeMillis());

    @BeforeEach
    void setUp() {
        arrEmpty = new int[]{};
        arrOne = new int[]{13};
        arrTree = new int[]{1, 3, 1};
        arrTen = new int[]{1, 3, 3, 7, 13, 13, 13, 17, 19, 23};
        arrBig = new int[100_000];
        for (int i = 0; i < arrBig.length; i++) {
            arrBig[i] = random.nextInt(101);
        }
    }
}
