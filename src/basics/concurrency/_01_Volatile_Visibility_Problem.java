package basics.concurrency;


/*
    1. Volatile keyword is used to modify the value of a variable by different threads.
    2. It is also used to make classes thread safe.
    3. It solves variable visibility problem
    4. It is used to mark a Java variable as "being stored in main memory". Every read of a volatile variable will be read from the computer's
       main memory, and not from the CPU cache, and that every write to a volatile variable will be written to main memory, and not just to the
       CPU cache
    5. In a multithreaded application where the threads operate on non-volatile variables, each thread may copy variables from main memory into
       a CPU cache while working on them, for performance reasons. If your computer contains more than one CPU, each thread may run on a different
       CPU. That means, that each thread may copy the variables into the CPU cache of different CPUs
    6. With non-volatile variables there are no guarantees about when the Java Virtual Machine (JVM) reads data from main memory into CPU caches,
       or writes data from CPU caches to main memory.

       Example - 2 threads, one is writing  shared variable and other is reading
       Output without volatile

        writer: changed value to = 1
        reader: value of c = 1
        writer: changed value to = 2
        writer: changed value to = 3
        writer: changed value to = 4
        writer: changed value to = 5

        Process finished with exit code 0

        Output with volatile -> static volatile int c;

        writer: changed value to = 1
        reader: value of c = 1
        writer: changed value to = 2
        reader: value of c = 2
        writer: changed value to = 3
        reader: value of c = 3
        reader: value of c = 4
        writer: changed value to = 4
        reader: value of c = 5
        writer: changed value to = 5

        Process finished with exit code 0

        In the scenario given above, where one thread (T1) modifies the counter, and another thread (T2) reads the counter
        (but never modifies it), declaring the counter variable volatile is enough to guarantee visibility for T2 of writes
        to the counter variable.

        If, however, both T1 and T2 were incrementing the counter variable, then declaring the counter variable volatile would
        not have been enough
 */
public class _01_Volatile_Visibility_Problem {

    static volatile int counter;

    public static void main (String[] args) {

        Thread thread1 = new Thread(() -> {
            int temp = 0;
            while (true) {
                if (temp != counter) {
                    temp = counter;
                    System.out.println("reader: value of c = " + counter);
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                counter++;
                System.out.println("writer: changed value to = " + counter);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // sleep enough time to allow reader thread to read pending changes (if it can!).
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //exit the program otherwise other threads will be keep waiting on c to change.
            System.exit(0);
        });

        thread1.start();
        thread2.start();
    }

}

