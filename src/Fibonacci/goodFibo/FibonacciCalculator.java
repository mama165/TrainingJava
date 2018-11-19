package Fibonacci.goodFibo;

public class FibonacciCalculator  implements IFibonacciCalculator {

    public  int calculate(int n) {
        if (n < 2) return (n);
        return (calculate(n - 2) + calculate(n - 1));
    }
}
