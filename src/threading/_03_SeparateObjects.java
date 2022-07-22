package threading;

public class _03_SeparateObjects {

    /*
        threading.MyOject@3be6a61c
        threading.MyOject@3be6a61c
        Thread 2 : 1000000
        Thread 1 : 1000000
     */
    public static void main(String[] args) {

        // Each thread has its own runnable object in heap
        // Each thread will create its own copy of local variable i (in its thread stack)  exists inside run method of Runnable
        // Each thread has its own count field as each thread has its own runnable object

        MyOject myOject = new MyOject();

        Runnable runnable1 = new MyRunnable(myOject);
        Runnable runnable2 = new MyRunnable(myOject);

        Thread thread1 = new Thread(runnable1, "Thread 1");
        Thread thread2 = new Thread(runnable2, "Thread 2");

        thread1.start();
        thread2.start();
    }
}
