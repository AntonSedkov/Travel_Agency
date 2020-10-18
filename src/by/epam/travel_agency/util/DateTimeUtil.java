package by.epam.travel_agency.util;

import java.time.Instant;

public class DateTimeUtil {

    public static Instant countDateFromLong(long timeSec) {
        Instant time = Instant.ofEpochSecond(timeSec);
        return time;
    }

    public static long countLongFromDate(Instant time) {
        long timeSec = time.getEpochSecond();
        return timeSec;
    }

}