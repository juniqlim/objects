import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CachedHolidayTest {
    @Test
    void test() throws IOException {
        new CachedHoliday("20221028").holidays().forEach(x -> System.out.println("x = " + x));
    }

    @Test
    void 공휴일확인() {
        Assertions.assertEquals(new CachedHoliday("20221028").isHoliday(), false);
    }
}
