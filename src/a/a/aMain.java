package a.a;

import java.time.Instant;

public class aMain {
    public static void main(String[] args) {

        Instant instant = Instant.now();
        System.out.println(instant.toString());
        long nn = instant.toEpochMilli();
        System.out.println(nn);
        Instant time = Instant.ofEpochMilli(nn);
        System.out.println(time);
        System.out.println(time.toString());
        System.out.println(time.toEpochMilli());

    }
}
