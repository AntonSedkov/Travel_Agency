package a.a;

import java.lang.reflect.Field;
import java.time.Instant;
import java.util.*;

public class aMain {


    public static void main2(String[] args) {

        Instant instant = Instant.now();
        System.out.println(instant.toString());
        long nn = instant.toEpochMilli();
        System.out.println(nn);
        Instant time = Instant.ofEpochMilli(nn);
        System.out.println(time);
        System.out.println(time.toString());
        System.out.println(time.toEpochMilli());

    }

    public static void main(String[] args) throws Exception {

/*
        ArrayList<String> strings = new ArrayList<>();

        System.out.println(getCapacity(strings));

        for (int i = 0; i < 13; i++) {
            strings.add("" + i);
        }

        int n = 3;  // difference
        int m = 2; //start pos

        System.out.println(getCapacity(strings));
        System.out.println(Arrays.toString(strings.toArray()));
        for (int i = m; i < strings.size() - n; i++) {
            strings.set(m, strings.get(m + n));
        }*/

      /*  strings.addAll(m, strings.subList(m + n, strings.size()));
        System.out.println(Arrays.toString(strings.toArray()));
        for (int i = strings.size()-1; i > m + n; i--) {
            strings.remove(i);
        }*/
        /*System.out.println(Arrays.toString(strings.toArray()));
        System.out.println(getCapacity(strings));
        strings.trimToSize();
        System.out.println(getCapacity(strings));

        List<String> losa = new ArrayList<>();
        losa.sort(Comparator.naturalOrder());

        List.copyOf(new ArrayDeque<>());*/
       /* ArrayList<String> assew = new ArrayList<>();
        assew.add("asd1");
        assew.add("asd2");
        assew.add("asd3");
        assew.add("asd4");
        assew.add("asd5");
        System.out.println(assew.toString());
        int i=0;
        while (i<3){
            assew.remove(i++);
        }
        System.out.println(assew.toString());

        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(4, "four");

        System.out.println(findValue(map, 3));
        System.out.println(map.size());
        System.out.println("blank" + findValue(map, 7));
        System.out.println(map.size());
    }

    static String findValue(Map<Integer, String> map, Integer key) {
        String defaultValue = "";
        map.putIfAbsent(key, defaultValue);
        String value = map.get(key);
        return value;
    }


    static int getCapacity(List al) throws Exception {
        Field field = ArrayList.class.getDeclaredField("elementData");
        field.setAccessible(true);
        return ((Object[]) field.get(al)).length;
    }*/

    }
}
