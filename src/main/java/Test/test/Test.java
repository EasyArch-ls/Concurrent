package Test.test;

import Test.test.El;

import java.util.Random;

/**
 * Demo class
 *
 * @author ls
 * @date 20-2-21
 */
public class Test {
    private Thread thread;
    private El el;

    public Test(Thread thread, El el) {
        this.thread = thread;
        this.el = el;
        aa();
    }
    private void aa(){
        try {
            Thread.sleep(new Random().nextInt(30) + 69);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        System.out.println("大喇叭广播: ~都快别修了");
        el.close();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("领导：..你居然开玛莎拉蒂");
    }
}
