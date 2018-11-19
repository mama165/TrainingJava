package Fibonacci;
import java.time.Duration;
import java.time.Instant;

public class BadFibo {

    public static void main(String... args) {
        System.out.println(calculate(10));
    }

    public static int calculate(int n) {
        Instant start = Instant.now();
        Instant end = Instant.now();
        long timeElapsed;

        if (n < 2) {
            timeElapsed = Duration.between(start, end).toMillis();
            System.out.println("Fibonacci took : " + timeElapsed);
            return (n);
        }
        return (calculate(n - 2) + calculate(n - 1));
    }
}
