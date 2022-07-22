package threading;

/*
        There are 4 ways to create a thread
        1. Create a sub-class extending Thread class - not recommended
        2. Create a class implementing Runnable interface
        3. Create runnable as anonymous class
        4. Implement Runnable as lambda

        How to stop a thread?
        The stop method provided by Thread class is deprecated so using it will turn code to unreliable state
        Better to manage stopping yourself

        If we'll not mark thread as daemon thread then it will be alive even after main thread finished working

        How one thread will wait for another thread? call join method

        Threads in JAVA are managed by underlying OS where JVM is running on and these threads are called OS level threads.
        OS level threads are really heavy
        Project Loom - fibers focusing on creating lightweight threads
 */
public class _01_ThreadExamples {

    //1
    public static class MyThread extends Thread {
        public void run() {
            System.out.println("MyThread running");
            System.out.println("MyThread finished");
        }
    }

    //2
    public static class MyRunnablele implements Runnable {
        @Override
        public void run() {
            System.out.println("MyRunnablele running");
            System.out.println("MyRunnablele finished");
        }
    }

    public static class StoppableRunnable implements Runnable {
        private boolean stopRequested = false;
        public synchronized void requestStop() {
            this.stopRequested = true;
        }
        public synchronized boolean isStopRequested() {
            return this.stopRequested;
        }

        private void sleep(long millis) {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            System.out.println("StoppableRunnable Running");
            while(!isStopRequested()) {
                sleep(1000);
                System.out.println("...");
            }
            System.out.println("StoppableRunnable Stopped");
        }
    }

    public static void main(String[] args) {

        // 1 - create a class and extend thread class
        MyThread thred1 = new MyThread();
        thred1.start();

        // 2 - create a class implementing runnable interface
        Thread thread2 = new Thread(new MyRunnablele());
        thread2.start();

        //3 - Implement runnable interface as anonymous class
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable running");
                System.out.println("Runnable finished");
            }
        };
        Thread thread3 = new Thread(runnable);
        thread3.start();

        //4 - Implement runnable interface with lambda
        Runnable runnableLambda = () -> {
            System.out.println("Lambda running");
            System.out.println("Lambda finished");
        };
        Thread thread4 = new Thread(runnableLambda);
        thread4.start();

        //Start multiple threads - no order gurantee
        Runnable anotherLambda = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " running");
        };
        Thread thread5 = new Thread(anotherLambda, "The Thread 1"); // passing name of thread and retrieving it inside runnable
        thread5.start();
        Thread thread6 = new Thread(anotherLambda, "The Thread 2"); // passing name of thread and retrieving it inside runnable
        thread6.start();

        //How to stop a thread
        StoppableRunnable stoppableRunnable = new StoppableRunnable();
        Thread thread7 = new Thread(stoppableRunnable, "The Stoppable Thread");
        thread7.start();
        sleep(5000);
        System.out.println("Requesting stop");
        stoppableRunnable.requestStop();

        //JVM will be alive even if main thread stopped but some other thread running (not true when thread is Daemon)
        // Main thread waiting for thread to finish
        Runnable worker = () -> {
            while(true) {
                sleep(1000);
                System.out.println("Running");
            }
        };
        Thread thread8 = new Thread(worker);
        thread8.setDaemon(true); //setting it make JVM to exit even if thread operation is still pending
        thread8.start();
        sleep(3100);

        //It is also possible for one thread to wait for another thread to terminate
        Runnable waitRunnable = () -> {
            for(int i = 0; i < 5; i++) {
                sleep(1000);
                System.out.println("Running");
            }
        };
        Thread waitThread = new Thread(waitRunnable);
        waitThread.setDaemon(true);
        waitThread.start();

        //Main thread will wait (block) for waitThread
        try {
            waitThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

