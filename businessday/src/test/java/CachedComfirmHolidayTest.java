import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CachedComfirmHolidayTest {
    @Test
    void test() throws IOException {
        CachedComfirmHoliday cachedHoliday = new CachedComfirmHoliday();
        Assertions.assertEquals(cachedHoliday.isHoliday("20221028"), false);
        Assertions.assertEquals(cachedHoliday.isHoliday("20221003"), true); // 개천절
        Assertions.assertEquals(cachedHoliday.isHoliday("20220913"), false);
        Assertions.assertEquals(cachedHoliday.isHoliday("20220912"), true); // 추석 대체휴일
        Assertions.assertEquals(cachedHoliday.isHoliday("20220815"), true); // 광복절
        Assertions.assertEquals(cachedHoliday.isHoliday("20220717"), false);
    }
}
