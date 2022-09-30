package io.github.juniqlim.objects.businessday;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

public class CachedComfirmHoliday {
    private final HashMap<String, List<LocalDate>> cached = new HashMap<>();

    public boolean isHoliday(String date) throws IOException {
        return isHoliday(LocalDate.parse(date, DateTimeFormatter.BASIC_ISO_DATE));
    }

    public boolean isHoliday(LocalDate date) throws IOException {
        String year = date.format(DateTimeFormatter.ofPattern("yyyy"));
        List<LocalDate> holidays = cached.get(year);
        if (holidays == null) {
            cached.put(year, new Holiday(date).holidays());
        }
        return cached.get(year).stream()
            .anyMatch(holiday -> holiday.equals(date));
    }
}
