package threading.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
    In Java, one thread is equivalent to one OS thread. Creating thread is an expensive operation. The preferred approach is to have a pool of threads
    with fixed number and submit 1000 tasks to the pool. Thread will pick up task, execute them, finish them and will pick up another task

    Creates a thread pool that reuses a fixed number of threads operating off a shared unbounded queue. (LinkedBlockingQueue)
    At any point, at most nThreads threads will be active processing tasks. If additional tasks are submitted when all threads are active,
    they will wait in the queue until a thread is available. If any thread terminates due to a failure during execution prior to shutdown,
    a new one will take its place if needed to execute subsequent tasks. The threads in the pool will exist until it is explicitly shutdown.

    What is an ideal pool size?
        Depends on type of task we want to execute

    Task Type       Ideal Pool Size         Considerations

    CPU Bound       CPU core count          How many other applications (or other executors/threads are running on the same CPU)
    IO Bound        High                    Exact number will depend on the rate of task submissions and average task wait time. Too many threads will
                                            increase memory consumption


 */
public class _01_FixedThreadPoolExample {

    public static void main(String[] args){
        runIOBoundExample();
        runCPUBoundExample();
    }

    private static void runCPUBoundExample() {
        int coreCount = Runtime.getRuntime().availableProcessors();
        ExecutorService cpuService = Executors.newFixedThreadPool(coreCount);
        for(int i = 0; i < 100; i++) {
            cpuService.execute(new AsynchronousTask());
        }
        cpuService.shutdown();
        System.out.println("Thread Name: " + Thread.currentThread().getName());
    }

    private static void runIOBoundExample() {
        // create the pool
        ExecutorService service = Executors.newFixedThreadPool(100);
        // submit the task for execution
        for(int i = 0; i < 100; i++) {
            service.execute(new AsynchronousTask());
        }
        service.shutdown();
        System.out.println("Thread Name: " + Thread.currentThread().getName());
    }

    static class AsynchronousTask implements Runnable {
        @Override
        public void run() {
            System.out.println("Thread Name: " + Thread.currentThread().getName());
        }
    }
}
