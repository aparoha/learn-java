package basics.collections;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
    https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html

    java streams vs reactive streams

    1. In OOP, we encapsulate moving parts but in functional programming we eliminate moving parts
    2. Imperative style - tell me what and also tell me how to do it
    3. Declarative style - tell me what and *not* how
    4. Functional style - declarative + high order functions

    1. Lazy operations achieve efficiency. It is a way not to work on stale data. Lazy operations might be useful in the situations where
       input data is consumed gradually rather than having whole complete set of elements beforehand

 */
public class StreamsExamples {

    public static void main(String[] args) {
        // Print list of doubled even numbers
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(numbers
                .stream()
                .filter(e -> e % 2 == 0)
                .map(e -> e * 2)
                .collect(Collectors.toList())
        );

        // Iterate over string
        String testStr = "Hi Houston, My name is Abhay";
//        for(char c : testStr.toCharArray()){
//            System.out.println(c);
//        }
//        for(int i = 0; i < testStr.length(); i++) {
//            System.out.println(testStr.charAt(i));
//        }

        testStr.chars()
                .mapToObj(c -> String.valueOf((char) c)).forEach(System.out::println);

//        testStr.chars().forEach(System.out::println);
    }
}
