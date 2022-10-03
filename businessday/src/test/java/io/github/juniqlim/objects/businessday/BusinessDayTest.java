package io.github.juniqlim.objects.businessday;

import java.io.IOException;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BusinessDayTest {
    @Test
    public void 입력한_날짜_기준_이전_영업일_조회() throws IOException {
        Assertions.assertEquals(
            new BusinessDay(LocalDate.parse("2022-09-26")).previousDate(),
            LocalDate.parse("2022-09-23"));

        Assertions.assertEquals(
            new BusinessDay(LocalDate.parse("2022-10-04")).previousDate(),
            LocalDate.parse("2022-09-30"));

        Assertions.assertEquals(
            new BusinessDay(LocalDate.parse("2022-09-13")).previousDate(),
            LocalDate.parse("2022-09-08"));

        Assertions.assertEquals(
            new BusinessDay(LocalDate.parse("2022-09-30")).previousDate(),
            LocalDate.parse("2022-09-29"));
    }
}
