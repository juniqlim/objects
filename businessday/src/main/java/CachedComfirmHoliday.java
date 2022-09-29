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
        String yyyyMM = date.format(DateTimeFormatter.ofPattern("yyyyMM"));
        List<LocalDate> holidays = cached.get(yyyyMM);
        if (holidays == null) {
            cached.put(yyyyMM, new Holiday(date).holidays());
        }
        return cached.get(yyyyMM).stream()
            .filter(holiday -> holiday.equals(date))
            .findFirst()
            .isPresent();
    }
}
