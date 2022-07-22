package threading.executor;

/*

        Core pool size - initial size / base size of the pool
        Current pool size - Threads added / deleted based on pool type and keepAlive time
        Max pool size - upper threshold of pool size

        Differences

        Parameter       FixedThreadPool         CachedThreadPool        ScheduledThreadPool     SingleThreaded

        corePoolSize    constructor-arg         0                       constructor-arg         1
        maxPoolSize     same as corePoolSize    Integer.MAX_Value       Integer.MAX_Value       1
        keepAliveTime   N/A                     60 seconds              60 seconds              0 seconds

        Special method - Core pool threads are never killed unless allowCoreThreadTimeOut(boolean value) is set to true

        Types of queues

        Pool                    Queue Type                      Why?

        FixedThreadPool         LinkedBlockingQueue             Threads are limited, thus unbounded queue to store all tasks
        SingleThreadExecutor    LinkedBlockingQueue
        CachedThreadPool        SynchronousQueue
        ScheduledThreadPool     DelayedWorkQueue
        Custom                  ArrayBlockingQueue


 */

public class _03_AdvanceExecutorsTopicsExample {
}
