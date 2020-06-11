package Test.myReentrantLock;


/***
 * @Author: lisheng
 * @Date: 2020/6/10
 * @Time: 下午3:48
 * @Description:查看栈帧局部变量的slot是否可以重用
 ***/

public class MyTest {
    public static void main(String[] args) {
        {
            byte[] placeholder = new byte[64 * 1024 * 1024];
        }
        int a=0;
        System.gc();
    }
}
