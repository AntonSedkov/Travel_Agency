package by.epam.travel_agency.util;

import java.time.*;

public class DateTimeUtil {

    public static Instant convertInstantFromLong(long timeSec) {
        Instant time = Instant.ofEpochSecond(timeSec);
        return time;
    }

    public static long convertLongFromInstant(Instant time) {
        long timeSec = time.getEpochSecond();
        return timeSec;
    }

    public static long convertLongFromLocalDate(LocalDate date) {
        Instant instant = date.atStartOfDay(ZoneId.systemDefault()).toInstant();
        long timeSec = convertLongFromInstant(instant);
        return timeSec;
    }

    public static LocalDate convertLocalDateFromLong(Long timeSec) {
        Instant instant = convertInstantFromLong(timeSec);
        LocalDate date = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
        return date;
    }

    public static LocalDateTime countDateTimeFromInstant(Instant instant) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return dateTime;
    }

    public static long convertLongFromLocalDateTime(LocalDateTime dateTime) {
        long timeSec = dateTime.toEpochSecond(ZoneOffset.UTC);
        Instant instant = dateTime.toInstant(ZoneOffset.UTC);
        return timeSec;
    }

    public static long countLongFromLocalDate(LocalDate date) {
        Instant instant = date.atStartOfDay(ZoneId.systemDefault()).toInstant();        // ZoneId.systemDefault() - default time-zone of the JVM
        long timeSec = date.toEpochSecond(LocalTime.MIN, ZoneOffset.UTC);                                 // ZoneOffset.UTC - UTC zone
        return timeSec;
    }
}// ZoneId.systemDefault() - default time-zone of the JVM.
// ZoneOffset.UTC - UTC zone
