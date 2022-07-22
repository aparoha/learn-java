package threading;

/*
    Perform possible asynchronous (non-blocking) computation and trigger dependant computations which could also be asynchronous

 */

import java.util.Random;
import java.util.concurrent.*;

public class _05_CompletableFutureExamples {

    public static void main(String[] args) {
        runCallableWithFuture();
    }

    private static void runCallableWithFuture() {
        // create the pool
        ExecutorService service = Executors.newFixedThreadPool(10);
        Future<Integer> future = service.submit(new AsynchronousTask()); //submit not execute
        try {
            Integer result = future.get(); //blocking...problem
            System.out.println("Result from the task is " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    static class AsynchronousTask implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            Thread.sleep(3000);
            return new Random().nextInt();
        }
    }
}
