import java.io.IOException;
import java.time.LocalDate;

public class BusinessDay {
    private final LocalDate source;

    public BusinessDay(LocalDate source) {
        this.source = source;
    }

    public LocalDate previousDate() throws IOException {
        Day day = new Day();
        LocalDate yesterDay = source.minusDays(1);
        while (day.isHoliday(yesterDay)) {
            yesterDay = yesterDay.minusDays(1);
        }
        return yesterDay;
    }
}
