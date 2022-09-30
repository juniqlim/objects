package io.github.juniqlim.objects.businessday;

import io.github.juniqlim.objects.businessday.CachedComfirmHoliday;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CachedComfirmHolidayTest {
    @Test
    void test() throws IOException {
        CachedComfirmHoliday cachedHoliday = new CachedComfirmHoliday();
        Assertions.assertFalse(cachedHoliday.isHoliday("20221028"));
        Assertions.assertTrue(cachedHoliday.isHoliday("20221003")); // 개천절
        Assertions.assertFalse(cachedHoliday.isHoliday("20211231"));
        Assertions.assertTrue(cachedHoliday.isHoliday("20220101")); // 새해첫날
    }
}
