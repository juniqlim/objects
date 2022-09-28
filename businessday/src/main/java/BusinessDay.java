import java.time.LocalDate;

public class BusinessDay {
    private final LocalDate source;

    public BusinessDay(LocalDate source) {
        this.source = source;
    }

    public LocalDate previousDate() {
        return null;
    }
}
