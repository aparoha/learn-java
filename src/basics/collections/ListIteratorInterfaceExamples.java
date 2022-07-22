package basics.collections;

import java.util.ArrayList;
import java.util.ListIterator;

/*

      1. The ListIterator interface of the Java collections framework provides the functionality to access elements of a list.
      2. It is bidirectional. This means it allows us to iterate elements of a list in both the direction.
      3. It extends the Iterator interface.
      4. The List interface provides a listIterator() method that returns an instance of the ListIterator interface.
      5. The ListIterator interface provides methods that can be used to perform various operations on the elements of a list.
            hasNext() - returns true if there exists an element in the list
            next() - returns the next element of the list
            nextIndex() returns the index of the element that the next() method will return
            previous() - returns the previous element of the list
            previousIndex() - returns the index of the element that the previous() method will return
            remove() - removes the element returned by either next() or previous()
            set() - replaces the element returned by either next() or previous() with the specified element
 */
public class ListIteratorInterfaceExamples {

    public static void main(String[] args) {
        // Creating an ArrayList
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(3);
        numbers.add(2);
        System.out.println("ArrayList: " + numbers);

        // Creating an instance of ListIterator
        ListIterator<Integer> iterate = numbers.listIterator();

        // Using the next() method
        int number1 = iterate.next();
        System.out.println("Next Element: " + number1);

        // Using the nextIndex()
        int index1 = iterate.nextIndex();
        System.out.println("Position of Next Element: " + index1);

        // Using the hasNext() method
        System.out.println("Is there any next element? " + iterate.hasNext());
    }
}
