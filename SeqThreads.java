/**
 * Created by yizhiw on 4/30/2017.
 */
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SeqThreads {
    private static class thread1 implements Runnable {
        public void run() {
            System.out.println("This is thread 1");
        }
    }

    private static class thread2 implements Runnable {
        public void run() {
            System.out.println("This is thread 2");
        }
    }

    private static class thread3 implements Runnable {
        public void run() {
            System.out.println("This is thread 3");
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new thread1());
        Thread t2 = new Thread(new thread2());
        Thread t3 = new Thread(new thread3());

        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.submit(t2);
        executor.submit(t3);
        executor.submit(t1);
    }
}