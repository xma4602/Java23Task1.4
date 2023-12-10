package com.xma;

import java.time.LocalDate;

public class DatesRange {
    public static LocalDate[] range(String startDate, String endDate) {
        return range(LocalDate.parse(startDate), LocalDate.parse(endDate));
    }

    public static LocalDate[] range(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            return new LocalDate[0];
        }
        LocalDate[] dates = new LocalDate[startDate.until(endDate).getDays() + 1];
        dates[0] = startDate;
        dates[dates.length - 1] = endDate;
        for (int i = 1; i < dates.length; i++) {
            dates[i] = dates[i - 1].plusDays(1);
        }
        return dates;
    }
}
