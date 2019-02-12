package ExampleAbstractClasses;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.time.Duration;
import java.time.Instant;

public class JdkProxyDemo {

    interface ISession {
        void doCalcul(String s) throws InterruptedException;
    }

    static class Original implements ISession {
        public void doCalcul(String s) throws InterruptedException {
            System.out.println(s);
            Thread.sleep(5000);
        }
    }

    static class ExecutionTimeHandler implements InvocationHandler {
        private final ISession original;

        public ExecutionTimeHandler(ISession original) {
            this.original = original;
        }

        public Object invoke(Object proxy, Method method, Object[] args)
                throws IllegalAccessException, IllegalArgumentException,
                InvocationTargetException {
            Instant start = Instant.now();
            method.invoke(original, args);
            Instant end = Instant.now();
            System.out.println("took : " + Duration.between(start, end).toMillis() / 1000 + "s");
            return null;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Original original = new Original();
        ExecutionTimeHandler handler = new ExecutionTimeHandler(original);
        ISession isession = (ISession) Proxy.newProxyInstance(ISession.class.getClassLoader(),
                new Class[]{ISession.class},
                handler);
        isession.doCalcul("Waiting 5 seconds...");
    }
}
