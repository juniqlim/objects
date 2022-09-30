package io.github.juniqlim.objects.businessday;

import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DayTest {
    @Test
    void test() throws IOException {
        Day day = new Day();
        Assertions.assertFalse(day.isHoliday("20220929"));
        Assertions.assertTrue(day.isHoliday("20220925")); // 주말
        Assertions.assertTrue(day.isHoliday("20221003")); // 개천절
        Assertions.assertFalse(day.isHoliday("20220913"));
        Assertions.assertTrue(day.isHoliday("20220912")); // 추석 대체휴일
        Assertions.assertTrue(day.isHoliday("20220815")); // 광복절
        Assertions.assertFalse(day.isHoliday("20211231"));
        Assertions.assertTrue(day.isHoliday("20211004")); // 개천절 대체
        Assertions.assertTrue(day.isHoliday("20220101")); // 새해첫날
        Assertions.assertTrue(day.isHoliday("20220102")); // 주말
    }
}
