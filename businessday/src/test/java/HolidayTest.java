import java.io.IOException;
import org.junit.jupiter.api.Test;

class HolidayTest {
    @Test
    public void test() throws IOException {
        new Holiday("202209").holidays().forEach(x -> System.out.println("x = " + x));
    }
}