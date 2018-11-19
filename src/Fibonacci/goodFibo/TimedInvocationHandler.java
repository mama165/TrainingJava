package Fibonacci.goodFibo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TimedInvocationHandler implements InvocationHandler {

    private final Object target;

    public TimedInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(target, args);
    }
}
