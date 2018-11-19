package Fibonacci.goodFibo;

import java.time.Duration;
import java.time.Instant;

public class TimedInvocation <T> {

    public T invoke(InvocationPoint invocationPoint) throws Exception {
        long timeElapsed;
        Instant start = Instant.now();
        T result = (T) invocationPoint.invoke();
        Instant end = Instant.now();
        timeElapsed = Duration.between(start, end).toMillis();
        System.out.println("Fibonacci took : " + timeElapsed + " to execute");
        return result;
    }
}
