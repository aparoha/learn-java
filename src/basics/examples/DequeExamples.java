package basics.examples;

import java.util.ArrayDeque;
import java.util.Deque;

/*
    1. The Java Deque interface, java.util.Deque, represents a double ended queue, meaning a queue where you can add and remove elements
       to and from both ends of the queue.
    2. The name Deque is an abbreviation of Double Ended Queue.
    3. We can use a Deque as both a queue and a stack
    4. The Java Deque interface extends the Java Queue interface. That means that you can use all the Java Queue methods when working
       with a Deque. The Deque interface does not extend the Java Stack interface, but the Deque interface defines methods that enable
       you to do the same operations you would normally do on a stack (push, peek, pop)
    5. Implementations
        Since Java Deque is an interface you need to instantiate a concrete implementation of the interface in order to use it.
        You can choose between the following Deque implementations in the Java Collections API:
            java.util.LinkedList
            java.util.ArrayDeque

        The LinkedList class is a pretty standard Deque and Queue implementation. It uses a linked list internally to model a queue or a
        deque.
        The Java ArrayDeque class stores its elements internally in an array. If the number of elements exceeds the space in the array,
        a new array is allocated, and all elements moved over. In other words, the ArrayDeque grows as needed, even if it stores its
        elements in an array.
    6. Add element to Deque
            add() - Inserts the specified element at the end of this deque.
            addLast() - Inserts the specified element at the end of this deque.
            addFirst() - Inserts the specified element at the front of this deque.
            offer()
            offerFirst()
            offerLast()
    7. Remove element from Deque
            remove() - Retrieves and removes the head of the queue represented by this deque., throws exception if empty
            removeFirst() - removes first element (removes head), throws exception if empty
            removeLast() - removes last element (removes tail), throws exception if empty
            poll()
            pollFirst()
            pollLast()


 */
public class DequeExamples {

    public static void main(String[] args) {

        // Use Deque as stack
        Deque<Integer> stack = new ArrayDeque<>();
        // Methods throw exception when operation fails
        stack.addLast(1);
        stack.addLast(2);
        stack.addLast(3);
        stack.addLast(4);
        stack.addLast(5);
        stack.getLast();
        stack.removeLast();
        System.out.println(stack);

        //Methods that return null or false when the operation fails
        stack.offerLast(6);
        stack.offerLast(7);
        stack.peekLast();
        stack.peekLast();
        System.out.println(stack);


        // Use Deque as Queue
        Deque<Integer> queue = new ArrayDeque<>();
        queue.addFirst(1);
        queue.addFirst(2);
        queue.addFirst(3);
        queue.addFirst(4);
        queue.addFirst(5);
        queue.getFirst();
        queue.removeFirst();
        System.out.println(queue);

        queue.offerFirst(6);
        queue.offerFirst(7);
        queue.peekFirst();
        queue.peekFirst();
        System.out.println(queue);

    }
}
