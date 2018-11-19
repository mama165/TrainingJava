package Fibonacci;

public class Fibo {

    public static void main(String... args) {
        System.out.println(calculate(10));
    }

    public static int calculate(int n) {
        if (n < 2) return (n);
        return (calculate(n - 2) + calculate(n - 1));
    }
}
