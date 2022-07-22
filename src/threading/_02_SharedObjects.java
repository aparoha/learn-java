package threading;

/*
        java thread stack heap
        -----------------------

        1. Each thread has a thread stack (local memory area owned by thread)
        2. Heap area can be accessed by all threads, so it we want to shared data between threads so the only way to share
           it via heap
        3. What is stored in thread stack?
           Local variables - byte, short, char, int, long, float, double, references to objects(actual object is in heap)
           String myLocalString = "Text";
           myLocalString in thread stack and "Text" in heap
        4.


 */
public class _02_SharedObjects {

    /*
    Thread 1 : 997394
    Thread 2 : 1886036
     */
    public static void main(String[] args) {

        // Each thread is sharing single runnable object
        // Each thread will create its own copy of local variable i (in its thread stack)  exists inside run method of Runnable
        // Each thread will share common count field in heap
        MyOject myOject = new MyOject();
        Runnable runnable = new MyRunnable(myOject);

        Thread thread1 = new Thread(runnable, "Thread 1");
        Thread thread2 = new Thread(runnable, "Thread 2");

        thread1.start();
        thread2.start();
    }
}
