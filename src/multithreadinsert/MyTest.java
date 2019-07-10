package multithreadinsert;

import java.util.concurrent.CountDownLatch;

public class MyTest {

    static CountDownLatch cdl=new CountDownLatch(10);

    public static int threads = 20;

    public static void main(String[] args) {
        for (int i = 0; i < threads; i++) {
            new InsertThread().start();
        }


        InsertThread insertThread = new InsertThread();
        insertThread.writeExcel();
    }
}
