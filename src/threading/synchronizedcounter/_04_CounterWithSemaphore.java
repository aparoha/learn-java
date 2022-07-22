package threading.synchronizedcounter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


/*
    Synchronized
    1. Visibility Guarantee
    - When a thread enters a synchronized block, all variables visible to the thread are refreshed from main memory.
    - When a thread exits a synchronized block, all variables visible to the thread are written back to main memory.
    2. Happens Before Guarantee

 */

public class _04_CounterWithSemaphore implements Runnable {
    Semaphore semaphore;
    private static int counter = 0;
    private static final int limit = 1000;
    private static final int threadPoolSize = 5;

    public _04_CounterWithSemaphore(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    public static void main(String[] args) {
        Semaphore sharedSemaphore = new Semaphore(1);
        ExecutorService executorService = Executors.newFixedThreadPool(threadPoolSize);
        for (int i = 0; i < threadPoolSize; i++) {
            executorService.submit(new _04_CounterWithSemaphore(sharedSemaphore));
        }
        executorService.shutdown();
    }

    @Override
    public void run() {
        while (counter < limit) {
            increaseCounter();
        }
    }

    private void increaseCounter() {
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " : " + counter);
            counter++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}
