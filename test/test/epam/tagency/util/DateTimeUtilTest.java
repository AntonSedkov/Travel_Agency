package test.epam.tagency.util;

import by.epam.tagency.util.DateTimeUtil;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.testng.Assert.assertEquals;

public class DateTimeUtilTest {

    @Test
    public void testConvertLocalDateFromLong() {
        long incomeLong = 1578614400;
        LocalDate expected = LocalDate.of(2020, 1, 10);
        LocalDate actual = DateTimeUtil.convertLocalDateFromLong(incomeLong);
        assertEquals(actual, expected);
    }

    @Test
    public void testConvertLongFromLocalDate() {
        LocalDate incomeDate = LocalDate.of(2020, 1, 10);
        long expected = 1578614400;
        long actual = DateTimeUtil.convertLongFromLocalDate(incomeDate);
        assertEquals(actual, expected);
    }

    @Test
    public void testConvertLocalDateTimeFromLong() {
        long incomeLong = -2208173370L;
        LocalDateTime expected = LocalDateTime.of(1900, 1, 10, 10, 30, 30);
        LocalDateTime actual = DateTimeUtil.convertLocalDateTimeFromLong(incomeLong);
        assertEquals(actual, expected);
    }

    @Test
    public void testConvertLongFromLocalDateTime() {
        LocalDateTime incomeDateTime = LocalDateTime.of(1900, 1, 10, 10, 30, 30);
        long expected = -2208173370L;
        long actual = DateTimeUtil.convertLongFromLocalDateTime(incomeDateTime);
        assertEquals(actual, expected);
    }

    @Test
    public void testConvertBeginingLocalDateTimeFromLong() {
        long incomeLong = 0;
        LocalDateTime expected = LocalDateTime.of(1970, 1, 1, 00, 00, 00);
        LocalDateTime actual = DateTimeUtil.convertLocalDateTimeFromLong(incomeLong);
        assertEquals(actual, expected);
    }

}