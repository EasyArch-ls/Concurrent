package Test.myReentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/***
 * @Author: lisheng
 * @Date: 2020/6/10
 * @Time: 下午5:13
 * @Description:公平锁与非平锁
 ***/
public class MyTest2 {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock reentrantLock=new ReentrantLock(false);
        reentrantLock.lock();
        for (int i = 0; i <5000 ; i++) {
            new Thread(() -> {
                reentrantLock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + "Running");
                } finally {
                    reentrantLock.unlock();
                }
            }, "t" + i).start();
        }
            TimeUnit.SECONDS.sleep(1);
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"start");
                reentrantLock.lock();
                try {
                    System.out.println(Thread.currentThread().getName()+"Running");
                }finally {
                    reentrantLock.unlock();
                }
            },"强行插入").start();
            reentrantLock.unlock();
    }
}
