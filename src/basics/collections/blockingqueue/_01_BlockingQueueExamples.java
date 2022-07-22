package basics.collections.blockingqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/*

    1. Its an interface where we can enqueue elements at the end and dequeue from the beginning.
    2. The Java BlockingQueue interface, java.util.concurrent.BlockingQueue, represents a queue which is thread safe to put elements
    into, and take elements out of from. In other words, multiple threads can be inserting and taking elements concurrently from a
    Java BlockingQueue, without any concurrency issues arising.
    3. The term blocking queue comes from the fact that the Java BlockingQueue is capable of blocking the threads that try to insert or
    take elements from the queue. For instance, if a thread tries to take an element and there are none left in the queue, the thread
    can be blocked until there is an element to take. Whether or not the calling thread is blocked depends on what methods you call on
    the BlockingQueue.
    4. A BlockingQueue is typically used to have one thread produce objects, which another thread consumes
    5. The producing thread will keep producing new objects and insert them into the BlockingQueue, until the queue reaches some upper
    bound on what it can contain. It's limit, in other words. If the blocking queue reaches its upper limit, the producing thread is
    blocked while trying to insert the new object. It remains blocked until a consuming thread takes an object out of the queue.
    6. The consuming thread keeps taking objects out of the BlockingQueue to processes them. If the consuming thread tries to take an
    object out of an empty queue, the consuming thread is blocked until a producing thread puts an object into the queue.
    8. methods

                Throws Exception	Special Value	Blocks	    Times Out
        Insert	add(o)	            offer(o)	    put(o)	    offer(o, timeout, timeunit)
        Remove	remove(o)	        poll()	        take()	    poll(timeout, timeunit)
        Examine	element()	        peek()
    7. Classes implementing BlockingQueue
        ArrayBlockingQueue
        DelayQueue
        LinkedBlockingQueue
        LinkedBlockingDeque
        LinkedTransferQueue
        PriorityBlockingQueue
        SynchronousQueue

    8. It is not possible to insert null into a BlockingQueue. If you try to insert null, the BlockingQueue will throw a NullPointerException
 */
public class _01_BlockingQueueExamples {

    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        // put will block until there is space inside BlockingQueue for the element
        try {
            blockingQueue.put("1");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // add (it will not block) instead will throw IllegalStateException if no space available in the BlockingQueue
        try {
            blockingQueue.add("2");
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

        // offer - not block either, returns false if no space
        boolean wasEnqueued = blockingQueue.offer("3");

        // offer with timeout - blocks for time if no space then returns false if still no space
        try {
            boolean wasEnqueued2 = blockingQueue.offer("4", 1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // take blocks until an element becomes available
        try {
            String element = blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // poll returns null if no element is available
        String element2 = blockingQueue.poll();

        // poll with timeout - blocks until timeout for an element to become available
        // If no element is available before that time, null is returned
        try {
            String element3 = blockingQueue.poll(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // removes element from queue
        boolean wasRemoved = blockingQueue.remove("1");

        // There is a way to drain queue from beginning in provided collection
        List<String> destination = new ArrayList<>();
        blockingQueue.drainTo(destination);

        // 2 methods to get peek element
        String element1 = blockingQueue.peek();

        try {
            String anotherElement = blockingQueue.element();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }

        int size = blockingQueue.size();
        int capacity = blockingQueue.remainingCapacity();
        boolean containsElement = blockingQueue.contains("1");

    }
}
