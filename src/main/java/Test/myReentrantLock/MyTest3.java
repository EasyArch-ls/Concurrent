package Test.myReentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/***
 * @Author: lisheng
 * @Date: 2020/6/10
 * @Time: 下午5:24
 * @Description: ReentrantLock中分区域加锁与唤醒实例 condition.await()或者condition.signal();
 *               必须在lock情况下,非公平状态下lock时会执行setExclusiveOwnerThread()与signal下的
 *               isHeldExclusively()对应;
 ***/
public class MyTest3 {
    public static boolean b=false;
   public static boolean b1=false;
    public static ReentrantLock lock=new ReentrantLock(true);
    public static Condition condition=lock.newCondition();
    public static Condition condition1=lock.newCondition();
    public static void main(String[] args) {
        new Thread(()->{
            lock.lock();
            try {
                while (!b){
                    System.out.println("1,stop");
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("1.,run");
            }finally {
                lock.unlock();
            }

        }).start();
        new Thread(()->{
            lock.lock();
            try {
                while (!b1){
                    System.out.println("12,stop");
                    try {
                        condition1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("12.,run");
            }finally {
                lock.unlock();
            }

        }).start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            lock.lock();
            try {
                b=true;
                condition.signal();
            }finally {
                lock.unlock();
            }
        }).start();

    }
}
