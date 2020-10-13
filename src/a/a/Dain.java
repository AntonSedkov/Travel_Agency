package a.a;

public class Dain {
    static int[] str = {1,2};

    public static void changeIt(int[] s) {
        s[1] = 5;
    }
    public static void main(String[] args) {
        changeIt(str);
        System.out.println(str[1]);



    }
}
