package basics.collections.blockingqueue;

import java.util.Random;
import java.util.concurrent.*;

/*
        What is the problem?
        ---------------------

        1. One or more producers will produce some kind of items or objects and they will be placed in some storage area
        2. One or more consumers will get the items out of storage area and process them
        3. If there is no item in the storage area, consumers will have to wait, whenever producer adds that item then consumer can proceed
        4. If there is no space in storage area, then producer will wait until some item consumed by consumer and space will become available

        What we want?

        1. Simple queue of fixed capacity
        2. Multiple producer threads
        3. Multiple consumer threads
        4. If queue is full, producer threads will go to wait state
        5. If queue is empty and there is nothing to consume then consumer threads will go to wait state


 */
public class _02_ProducerConsumerExample {

/* Different producers produces a stream of integers continuously to a shared queue,
which is shared between all Producers and consumers */


    /* Different consumers consume data from shared queue, which is shared by both producer and consumer threads */


    public static void main(String[] args) {
        BlockingQueue<Integer> sharedQueue = new LinkedBlockingQueue<Integer>();

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
