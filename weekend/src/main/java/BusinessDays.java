import java.time.LocalDate;

public class BusinessDays {
    private final LocalDate source;

    public BusinessDays(LocalDate source) {
        this.source = source;
    }

    public LocalDate previousDate() {
        return null;
    }
}
