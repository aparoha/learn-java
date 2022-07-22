package threading.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class _04_ExecutorWithReturnValuesExample {

    public static void main(String[] args) {
        // create the pool
        ExecutorService service = Executors.newFixedThreadPool(10);

        // submit the tasks for execution
        List<Future> allFutures = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Future<Integer> future = service.submit(new AsynchronousTask());
            allFutures.add(future);
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
