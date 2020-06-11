package Test.myReentrantLock;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Demo class
 *
 * @author ls
 * @date 20-2-22
 */
public class ThreadFactoryBuilder implements ThreadFactory {
    private String name;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    public ThreadFactoryBuilder(String name) {
        this.name = name;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread( Thread.currentThread().getThreadGroup(),r,
                name+ threadNumber.getAndIncrement(),
                0);
        if (t.isDaemon()) {

            t.setDaemon(false);
        }
        if (t.getPriority() != Thread.NORM_PRIORITY) {

            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;

    }
}
