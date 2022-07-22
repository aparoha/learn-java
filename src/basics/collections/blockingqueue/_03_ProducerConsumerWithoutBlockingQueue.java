package basics.collections.blockingqueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
        2 ways

        1. Locks and condition
        2. Wait notify
 */
public class _03_ProducerConsumerWithoutBlockingQueue {

    public static class Producer implements Runnable {
        private final MyBlockingQueue<Integer> sharedQueue;
        private int threadNo;
        private Random random = new Random();
        public Producer(MyBlockingQueue<Integer> sharedQueue,int threadNo) {
            this.threadNo = threadNo;
            this.sharedQueue = sharedQueue;
        }
        @Override
        public void run() {
            // Producer produces a continuous stream of numbers for every 200 milli seconds
            while (true) {
                try {
                    int number = random.nextInt(1000);
                    System.out.println("Produced:" + number + ":by thread:"+ threadNo);
                    sharedQueue.put(number); // thread blocks if queue full
                    Thread.sleep(1000);
                } catch (InterruptedException err) {
                    err.printStackTrace();
                }
            }
        }
    }

    public static class Consumer implements Runnable {
        private final MyBlockingQueue<Integer> sharedQueue;
        private int threadNo;
        public Consumer (MyBlockingQueue<Integer> sharedQueue,int threadNo) {
            this.sharedQueue = sharedQueue;
            this.threadNo = threadNo;
        }
        @Override
        public void run() {
            // Consumer consumes numbers generated from Producer threads continuously
            while(true){
                int num = sharedQueue.take(); // thread blocks if queue empty
                System.out.println("Consumed: "+ num + ":by thread:"+threadNo);
            }
        }
    }

    public static class MyBlockingQueue<E> {

        private Queue<E> queue;
        private int max = 16;
        private ReentrantLock lock = new ReentrantLock(true);
        private Condition notEmpty = lock.newCondition();
        private Condition notFull = lock.newCondition();

        public MyBlockingQueue(int size) {
            queue = new LinkedList<>();
            this.max = size;
        }

        public void put(E e) {
            lock.lock();
            try {
                if(queue.size() == max) { //blocks until queue has atleast 1 empty slot to add item
                    notFull.await(); // wait for not full
                }
                queue.add(e);
                notEmpty.signalAll(); // signal for not empty
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public E take() {
            lock.lock();
            try {
                while(queue.size() == 0) { // blocks until queue has atleast 1 item to take
                    notEmpty.await(); // wait for not empty
                }
                E item = queue.remove();
                notFull.signalAll();
                return item;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            return null;
        }
    }

    //http://www.cse.lehigh.edu/~spear/cse398/tutorials/jconcur.html
    //https://medium.com/@jgwest/concurrency-pattern-keep-your-java-objects-safe-from-multithreaded-meddlers-1065bbd2a27d
    //https://www.topcoder.com/thrive/articles/Concurrency%20Patterns%20-%20Active%20Object%20and%20Monitor%20Object
    //https://www.dre.vanderbilt.edu/~schmidt/POSA/POSA2/conc-patterns.html
    public static void main(String[] args) {
        MyBlockingQueue<Integer> sharedQueue = new MyBlockingQueue<Integer>(5);

        ExecutorService pes = Executors.newFixedThreadPool(2);
        ExecutorService ces = Executors.newFixedThreadPool(2);

        pes.submit(new Producer(sharedQueue, 1));
        pes.submit(new Producer(sharedQueue, 2));
        ces.submit(new Consumer(sharedQueue, 1));
        ces.submit(new Consumer(sharedQueue, 2));

        pes.shutdown();
        ces.shutdown();
    }
}
