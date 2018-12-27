package CrackingTheCodingInterview.DynamicProgramming;

public class Foo {

    public static int fibonacciRecursive(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    public static int fibonacci(int n) {
        return fibonacci(n, new int[n + 1]);
    }

    private static int fibonacci(int i, int[] memo) {
        if (i == 0 || i == 1) return i;

        if (memo[i] == 0) {
            memo[i] = fibonacci(i - 1, memo) + fibonacci(i - 2, memo);
        }

        return memo[i];
    }

    public static void main(String[] args) {
        System.out.println(fibonacci(50));
    }
}
