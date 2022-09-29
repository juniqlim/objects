import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CachedHoliday {
    private final Holiday holiday;
    private final List<List<Integer>> cached = new ArrayList<>(1);

    public CachedHoliday(LocalDate sourceDate) {
        this.holiday = new Holiday(sourceDate);
    }

    public CachedHoliday(String yearMonth) {
        this.holiday = new Holiday(yearMonth);
    }

    public CachedHoliday(String year, String month) {
        this.holiday = new Holiday(year, month);
    }

    public List<Integer> holidays() throws IOException {
        if (cached.isEmpty()) {
            cached.add(holiday.holidays());
        }
        return cached.get(0);
    }

    public boolean isHoliday() {
        return false;
    }
}
