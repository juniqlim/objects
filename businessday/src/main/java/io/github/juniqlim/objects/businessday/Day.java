package io.github.juniqlim.objects.businessday;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Day {
    private final CachedComfirmHoliday cachedComfirmHoliday;

    public Day() {
        this.cachedComfirmHoliday = new CachedComfirmHoliday();
    }

    public boolean isHoliday(String date) throws IOException {
        return isHoliday(LocalDate.parse(date, DateTimeFormatter.BASIC_ISO_DATE));
    }

    public boolean isHoliday(LocalDate date) throws IOException {
        return isWeekend(date) || isPublicHoliday(date);
    }

    private boolean isWeekend(LocalDate date) {
        int value = date.getDayOfWeek().getValue();
        return value == 6 || value == 7;
    }

    private boolean isPublicHoliday(LocalDate date) throws IOException {
        return cachedComfirmHoliday.isHoliday(date);
    }
}
