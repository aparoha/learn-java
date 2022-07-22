package basics.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/*
    Lets assume we have an application and a service and also assume service is slow so we want to restrict concurrent calls to service
    e.g. we are only allowed to make 3 concurrent calls to the service at any time
    Assume application is makine service calls with 50 threads

        Request ----> Application ----> Service


 */
public class _02_SemaphoreExamples {

    static class Task implements Runnable {
        @Override
        public void run() {
            //This might be called 50 times concurrently!!
            //IO call to slow service and rest of processing
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(50);
        IntStream.of(1000).forEach(i -> service.execute(new Task()));
        service.shutdown();
        service.awaitTermination(1, TimeUnit.MINUTES);
    }
}
