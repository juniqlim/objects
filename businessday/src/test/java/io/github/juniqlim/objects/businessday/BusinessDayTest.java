package io.github.juniqlim.objects.businessday;

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

        Assertions.assertEquals(
            new BusinessDay(LocalDate.parse("20221004", DateTimeFormatter.BASIC_ISO_DATE)).previousDate(),
            LocalDate.parse("20220930", DateTimeFormatter.BASIC_ISO_DATE));

        Assertions.assertEquals(
            new BusinessDay(LocalDate.parse("20220913", DateTimeFormatter.BASIC_ISO_DATE)).previousDate(),
            LocalDate.parse("20220908", DateTimeFormatter.BASIC_ISO_DATE));

        Assertions.assertEquals(
            new BusinessDay(LocalDate.parse("20220930", DateTimeFormatter.BASIC_ISO_DATE)).previousDate(),
            LocalDate.parse("20220929", DateTimeFormatter.BASIC_ISO_DATE));
    }
}
