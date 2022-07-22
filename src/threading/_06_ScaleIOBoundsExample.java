package threading;

import java.util.concurrent.*;

import static java.lang.System.currentTimeMillis;

//https://medium.com/swlh/scaling-up-io-tasks-795df1e29d7e
public class _06_ScaleIOBoundsExample {

    public static void main(String[] args) {
//        var naiveIO = new NaiveIO();
//        long startTime = currentTimeMillis();
//        for (int i = 0; i < 5; i++) {
//            naiveIO.dbCall1();
//            naiveIO.dbCall2();
//            naiveIO.dbCall3();
//        }
//        System.out.printf("completed IO calls in %d ms\n", currentTimeMillis() - startTime);

//        var asyncIO = new NaiveIO();
//        long startTime = currentTimeMillis();
//        for (int i = 0; i < 5; i++) {
//            CompletableFuture.runAsync(asyncIO::dbCall1);
//            CompletableFuture.runAsync(asyncIO::dbCall2);
//            CompletableFuture.runAsync(asyncIO::dbCall3);
//        }
//        try {
//            ForkJoinPool.commonPool().awaitTermination(2, TimeUnit.MINUTES);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.printf("completed IO calls in %d ms\n", currentTimeMillis() - startTime);

        var scalableIO = new NaiveIO();
        long startTime = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(16);
        for (int i = 0; i < 5; i++) {
            CompletableFuture.runAsync(scalableIO::dbCall1, executor);
            CompletableFuture.runAsync(scalableIO::dbCall2, executor);
            CompletableFuture.runAsync(scalableIO::dbCall3, executor);
        }

        executor.shutdown();
        try {
            executor.awaitTermination(2, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("completed IO calls in %d ms\n", System.currentTimeMillis() - startTime);
    }

    public static class NaiveIO {

        int dbCallCount;

        private void genericDbCall(int seconds) {
            try {
                Thread.sleep(seconds * 1000);
                System.out.printf("Completed db call #%d\n", ++dbCallCount);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void dbCall1() {
            genericDbCall(1);
        }

        public void dbCall2() {
            genericDbCall(2);
        }

        public void dbCall3() {
            genericDbCall(3);
        }
    }
}
