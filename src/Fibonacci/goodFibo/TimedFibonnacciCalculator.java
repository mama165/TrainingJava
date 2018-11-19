package Fibonacci.goodFibo;

public class TimedFibonnacciCalculator implements IFibonacciCalculator {
    private final FibonacciCalculator calculator;


    public TimedFibonnacciCalculator(FibonacciCalculator calculator) {
        this.calculator = calculator;

    }

    @Override
    public int calculate(int n) throws Exception {
        return (int) new TimedInvocation().invoke(
                () -> calculator.calculate(n)
        );
    }
}