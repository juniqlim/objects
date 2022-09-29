import java.io.IOException;
import java.time.LocalDate;

public class BusinessDay {
    private final LocalDate source;

    public BusinessDay(LocalDate source) {
        this.source = source;
    }

    public LocalDate previousDate() throws IOException {
        Day day = new Day();
        LocalDate yesterDay;
        do {
            yesterDay = source.minusDays(1);
        } while (day.isHoliday(yesterDay));
        return yesterDay;
    }
}
