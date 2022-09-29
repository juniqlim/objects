import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BusinessDayTest {
    @Test
    public void test() throws IOException {
        Assertions.assertEquals(
            new BusinessDay(LocalDate.parse("20220926", DateTimeFormatter.BASIC_ISO_DATE)).previousDate(),
            LocalDate.parse("20220923", DateTimeFormatter.BASIC_ISO_DATE));
    }
}
