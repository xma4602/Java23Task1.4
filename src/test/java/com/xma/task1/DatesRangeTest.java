package com.xma.task1;

import com.xma.task1.DatesRange;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class DatesRangeTest {


    String[] startDates = {
            "2023-11-12",
            "2023-02-28",
            "2020-05-28",
    };

    String[] endDates = {
            "2023-11-11",
            "2023-02-28",
            "2020-06-02",
    };
    String[][] resultDates = {
            {},
            {"2023-02-28"},
            {"2020-05-28", "2020-05-29", "2020-05-30", "2020-05-31", "2020-06-01", "2020-06-02"},
    };

    @Test
    public void testRange() {
        for (int i = 0; i < startDates.length; i++) {
            String[] dates = Arrays.stream(DatesRange.range(startDates[i], endDates[i]))
                    .map(LocalDate::toString)
                    .toArray(String[]::new);
            assertArrayEquals(resultDates[i], dates);
        }
    }
}