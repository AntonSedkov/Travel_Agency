package by.epam.tagency.util;

import java.time.*;

public class DateTimeUtil {

    private DateTimeUtil() {
    }

    public static LocalDate convertLocalDateFromLong(Long timeSec) {
        Instant instant = Instant.ofEpochSecond(timeSec);
        LocalDate date = LocalDate.ofInstant(instant, ZoneOffset.UTC);
        return date;
    }

    public static long convertLongFromLocalDate(LocalDate date) {
        long timeSec = date.toEpochSecond(LocalTime.MIN, ZoneOffset.UTC);
        return timeSec;
    }

    public static LocalDateTime convertLocalDateTimeFromLong(long timeSec) {
        Instant instant = Instant.ofEpochSecond(timeSec);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
        return dateTime;
    }

    public static long convertLongFromLocalDateTime(LocalDateTime dateTime) {
        long timeSec = dateTime.toEpochSecond(ZoneOffset.UTC);
        return timeSec;
    }

}