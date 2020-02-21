package Test.test;

/**
 * Demo class
 *
 * @author ls
 * @date 20-2-21
 */
public class El implements Runnable {

    private volatile boolean closeThread = false;
    private int i;

    // 终止请求
    public void shutdownRequest() {
        closeThread = true;
        Thread.currentThread().interrupt();
    }

    public void close() {
        this.closeThread = true;
    }

    public boolean isCloseThread() {
        return closeThread;
    }

    // 具体动作
    public final void run() {
        try {
            while (!closeThread) {
                System.out.println("维修进度" + ++i + "%");
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
        } finally {
            try {
                overFun();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    // 终止处理
    private void overFun() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println(">>>>>正在收拾工具");
        Thread.sleep(1000);
        System.out.println(">>>>>正在把玛莎拉蒂开出车库");
        Thread.sleep(1000);
        System.out.println(">>>>>正在用玛莎拉蒂车载电话呼叫领导的手机");
        Thread.sleep(1000);
        System.out.println(">>>>>我:领导你好,土豪我今天的总维修进度 " + i + "%");
    }
    public static void main(String[] args) {
        System.out.println("开始修电脑");
        try {
            El e = new El();
            Thread t = new Thread(e);
            t.start();
            Test test=new Test(t,e);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
