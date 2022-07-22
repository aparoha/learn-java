package threading.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*

    1. FixedThreadPool
    2. CachedThreadPool
    3. ScheduledThreadPool
    4. SingleThreadedExecutor

    CachedThreadPool

    - No fixed number of threads
    - There is no queue to hold number of tasks, instead a blocking queue is replace by something called synchronous queue which has space only for
      single item
    - Whenever we submit any task to this pool, it will search of any of threads which are previously created but if no thread is available, it will
      create new thread and will add to the pool
    - If thread is ideal for 60 seconds then kills the thread


    Creates a thread pool that creates new threads as needed, but will reuse previously constructed threads when they are available.
    These pools will typically improve the performance of programs that execute many short-lived asynchronous tasks. Calls to execute will
    reuse previously constructed threads if available. If no existing thread is available, a new thread will be created and added to the pool.
    Threads that have not been used for sixty seconds are terminated and removed from the cache. Thus, a pool that remains idle for long enough
    will not consume any resources. Note that pools with similar properties but different details (for example, timeout parameters) may be created
    using ThreadPoolExecutor constructors.

    ScheduledThreadPool

    - Schedules the task to run based on time delay - and re-trigger for fixed rate / fixedDelay
    - More threads are created if required
    - Add all submitted tasks in delay queue. Its a speical kind of queue wherein the submitted tasks may not be sequential
    - The tasks will be distributed on the basis of when the tasks will be executed, so if we submit 2 kinds of tasks (one should run after 10 seconds
      and other after after 10 minutes then 10 seconds will come to the front and 10 minutes after that

      Creates a thread pool that can schedule commands to run after a given delay, or to execute periodically.
      Params: corePoolSize – the number of threads to keep in the pool, even if they are idle

      SingleThreadedExecutor
      - Same as fixed thread pool executor, the difference is it has only one thread in pool
      - Recreates the thread if killed because of the task
      - Fetch new task from the blocking queue and executes it
      - Will make sure to run all tasks sequentially

 */
public class _02_PoolsExample {

    public static void main(String[] args){
        runCachedThreadPool();
        runScheduledThreadPool();
    }

    private static void runScheduledThreadPool() {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
        // task to run after 10 second delay
        service.schedule(new AsynchronousTask(), 10, TimeUnit.SECONDS);
        // task to run repeatedly every 10 seconds
        service.scheduleAtFixedRate(new AsynchronousTask(), 15, 10, TimeUnit.SECONDS);
        // task to run repeatedly 10 seconds after previous task completes
        service.scheduleWithFixedDelay(new AsynchronousTask(), 15, 10, TimeUnit.SECONDS);
    }

    private static void runCachedThreadPool() {
        // create the pool
        ExecutorService service = Executors.newCachedThreadPool();
        // submit the task for execution
        for(int i = 0; i < 100; i++) {
            service.execute(new _01_FixedThreadPoolExample.AsynchronousTask());
        }
        service.shutdown();
        System.out.println("Thread Name: " + Thread.currentThread().getName());
    }

    static class AsynchronousTask implements Runnable {
        @Override
        public void run() {
            // short lived tasks
            System.out.println("Thread Name: " + Thread.currentThread().getName());
        }
    }

}
