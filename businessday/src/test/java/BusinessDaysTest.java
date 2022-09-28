import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BusinessDaysTest {
    @Test
    public void test() {
        Assertions.assertEquals(
            new BusinessDays(LocalDate.parse("20220926", DateTimeFormatter.BASIC_ISO_DATE)).previousDate(),
            LocalDate.parse("20220923", DateTimeFormatter.BASIC_ISO_DATE));
    }
}
