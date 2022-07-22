package basics.collections;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class _02_ListExamples {

    public static void main(String[] args) {

        // create list
        List<String> crunchifyList = new ArrayList<>();

        // add 4 different values to list
        crunchifyList.add("Facebook");
        crunchifyList.add("Paypal");
        crunchifyList.add("Google");
        crunchifyList.add("Yahoo");

        List<String> cities = new ArrayList() {{
            add("New York");
            add("Rio");
            add("Tokyo");
        }};

        // Other way to define list is - we will not use this list :)
        List<String> crunchifyListNew = Arrays.asList("Facebook", "Paypal", "Google", "Yahoo");

        // Using collections API
        List<String> list = new ArrayList<>();
        Collections.addAll(list, "Facebook", "Paypal", "Google", "Yahoo");

        // Creating the list
        List<String> unmodifiableList = Collections.unmodifiableList(
                Arrays.asList("Facebook", "Paypal", "Google", "Yahoo"));

        // creates immutable lists, so you can't modify such list
        List<String> immutableList = List.of("one", "two", "three");

        // if we want mutable list we can copy content of immutable list
        // to mutable one for instance via copy-constructor (which creates shallow copy)
        List<String> mutableList = new ArrayList<>(List.of("one", "two", "three"));

        List<String> fromStreams = Stream.of("one", "two", "three").collect(Collectors.toList());

        // The streams collectors make no guarantee on the type of the List returned by toList().
        // To ensure the returned List is ArrayList, we can use toCollection(Supplier)
        List<String> fromStreamsArrayList = Stream.of("C", "C++", "Java")
                .collect(Collectors.toCollection(ArrayList::new));

        // Simple For loop
        System.out.println("==============> 1. Simple For loop Example.");
        for (int i = 0; i < crunchifyList.size(); i++) {
            System.out.println(crunchifyList.get(i));
        }

        // New Enhanced For loop
        System.out.println("\n==============> 2. New Enhanced For loop Example..");
        for (String temp : crunchifyList) {
            System.out.println(temp);
        }

        // Iterator - Returns an iterator over the elements in this list in proper sequence.
        System.out.println("\n==============> 3. Iterator Example...");
        Iterator<String> crunchifyIterator = crunchifyList.iterator();
        while (crunchifyIterator.hasNext()) {
            System.out.println(crunchifyIterator.next());
        }

        // ListIterator - traverse a list of elements in either forward or backward order
        // An iterator for lists that allows the programmer to traverse the list in either direction, modify the list during iteration,
        // and obtain the iterator's current position in the list.
        System.out.println("\n==============> 4. ListIterator Example...");
        ListIterator<String> crunchifyListIterator = crunchifyList.listIterator();
        while (crunchifyListIterator.hasNext()) {
            System.out.println(crunchifyListIterator.next());
        }

        // while loop
        System.out.println("\n==============> 5. While Loop Example....");
        int i = 0;
        while (i < crunchifyList.size()) {
            System.out.println(crunchifyList.get(i));
            i++;
        }

        // Iterable.forEach() util: Returns a sequential Stream with this collection as its source
        System.out.println("\n==============> 6. Iterable.forEach() Example....");
        crunchifyList.forEach((temp) -> {
            System.out.println(temp);
        });

        // collection Stream.forEach() util: Returns a sequential Stream with this collection as its source
        System.out.println("\n==============> 7. Stream.forEach() Example....");
        crunchifyList.stream().forEach((crunchifyTemp) -> System.out.println(crunchifyTemp));
    }
}
