import java.io.IOException;
import org.junit.jupiter.api.Test;

class CachedHolidayTest {
    @Test
    void test() throws IOException {
        new CachedHoliday("20221028").holidays().forEach(x -> System.out.println("x = " + x));
    }
}
