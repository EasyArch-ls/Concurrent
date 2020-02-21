package Test.one;

import java.util.concurrent.*;

/**
 * Demo class
 *
 * @author ls
 * @date 20-2-21
 */
public class MyTest {
    public static void main(String[] args) throws InterruptedException {

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder("aaaa");
        ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024),namedThreadFactory);
        Person person=new Person();
        singleThreadPool.execute(person);
        Thread.sleep(3500);
        person.stop();
        singleThreadPool.shutdownNow();

    }
}
class Person implements Runnable{
    private Thread thread;

    public void start(){
           thread=Thread.currentThread();
           System.out.println(thread.getName());
           while (true){
               if (thread.isInterrupted()){
                   System.out.println("被打断");
                   break;
               }
               try {
                   //阻塞（sleep，wait，json）状态被打断时 interrupt依然为假
                   Thread.sleep(100);
                   System.out.println("正常");
               } catch (InterruptedException e) {
                   e.printStackTrace();
                   //重新设置打断标记
                   thread.interrupt();
               }

           }
       }

    public void stop(){
        thread.interrupt();
    }


    @Override
    public void run() {
        start();
    }
}