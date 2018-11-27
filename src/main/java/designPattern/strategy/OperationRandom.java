package designPattern.strategy;

public class OperationRandom implements Strategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 + num2 * 25 - num2 + num1;
    }
}
