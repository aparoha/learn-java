package basics.collections;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/*
        1. Queue - its an interface and it extends Collection interfACE
        2. Classes that implement Queue
            ArrayDeque
            LinkedList
            PriorityQueue
        3. Interfaces that extend Queue
            Deque
            BlockingQueue
            BlockingDeque
        4. In queues, elements are stored and accessed in First In, First Out manner. That is, elements are added from the behind
           and removed from the front.
         5. Common methods
            add - Insert in queue, returns true or exception     offer - Insert in queue, returns true / false
            element - returns the head or exception              peek - returns the head. returns null when queue is empty
            remove - returns and removes the head else exception poll - returns and removes the head. returns null when queue is empty



 */
public class _01_QueuesExamples {

    public static void main(String[] args) {
//        createQueueWithLinkedList();
//        createQueueWithPriorityQueue();
        playWithPriorityQueue();
    }

    private static void createQueueWithLinkedList() {
        // Creating Queue using the LinkedList class
        Queue<Integer> numbers = new LinkedList<>();

        // offer elements to the Queue
        numbers.offer(1);
        numbers.offer(2);
        numbers.offer(3);
        System.out.println("Queue: " + numbers);

        // Access elements of the Queue
        int accessedNumber = numbers.peek();
        System.out.println("Accessed Element: " + accessedNumber);

        // Remove elements from the Queue
        int removedNumber = numbers.poll();
        System.out.println("Removed Element: " + removedNumber);

        System.out.println("Updated Queue: " + numbers);
    }

    private static void createQueueWithPriorityQueue() {
        // Creating Queue using the PriorityQueue class
        Queue<Integer> numbers = new PriorityQueue<>();

        // offer elements to the Queue
        numbers.offer(5);
        numbers.offer(1);
        numbers.offer(2);
        System.out.println("Queue: " + numbers);

        // Access elements of the Queue
        int accessedNumber = numbers.peek();
        System.out.println("Accessed Element: " + accessedNumber);

        // Remove elements from the Queue
        int removedNumber = numbers.poll();
        System.out.println("Removed Element: " + removedNumber);

        System.out.println("Updated Queue: " + numbers);
    }

    private static void playWithPriorityQueue() {
        PriorityQueue<Integer> pq = new PriorityQueue<>() {{
            offer(15);
            offer(2);
            offer(26);
            offer(9);
            offer(97);
        }};
        System.out.println("Sorted Ascending order");
        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }

        // How to pass comparator?
        // Comparator.reverseOrder()
        // (a, b) -> b.compareTo(a)
        // Create custom comparator class
        /*
           pq = new PriorityQueue<>(new CustomComparator()) {{
            offer(15);
            offer(2);
            offer(26);
            offer(9);
            offer(97);
        }};
         */
        pq = new PriorityQueue<>(Comparator.reverseOrder()) {{
            offer(15);
            offer(2);
            offer(26);
            offer(9);
            offer(97);
        }};
        System.out.println("Reverse Sorted Descending order");
        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }
    }

    static class CustomComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer number1, Integer number2) {
            int value =  number1.compareTo(number2);
            // elements are sorted in reverse order
            return Integer.compare(0, value);
        }
    }
}
