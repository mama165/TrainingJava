package Fibonacci.goodFibo;

@FunctionalInterface
public interface InvocationPoint <T> {
    T invoke() throws Exception;
}
