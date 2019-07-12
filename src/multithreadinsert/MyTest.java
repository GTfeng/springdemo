package multithreadinsert;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MyTest {

//    static CountDownLatch cdl=new CountDownLatch(10);

    public static int threads = 20;

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
//        for (int i = 0; i < threads; i++) {
//            new InsertThread().start();
//        }


        final CyclicBarrier barrier = new CyclicBarrier(1);
        for (int i = 0; i < threads; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    InsertThread insertThread = new InsertThread();
                    insertThread.start();
                    try {
                        System.out.println("子线程执行");
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        barrier.await();

        System.out.println("写Excel:---------------------------------------");

        InsertThread insertThread = new InsertThread();
//        insertThread.writeExcel(insertThread.perInsert, insertThread.insertTimes);
        InsertThread.writeExcel(insertThread.perInsert, insertThread.insertTimes);
    }
}
