package datastructures;

import java.util.Arrays;

/*
        1. String is immutable
        2. java.lang.String
        3. StringBuffer - mutable, thread safe using synchronized
        4. StringBuilder - mutable, not thread safe
        5. In String literals, if the String already exists, the new reference variable will be pointing to the already existing literal.
          Even the String already exists or not, a new String object will be created.
          6. Always use equals method to compare content of 2 string

 */
public class StringFunctions {

    public static void main(String[] args) {
        String test = "ABC tech";
//
//        // Iterate over it
//        for (char c : test.toCharArray())
//            System.out.println(c);
//
//        // Another way
//        for (int i = 0; i < test.length(); i++)
//            System.out.println(test.charAt(i));
//
//        StringBuffer sb = new StringBuffer();
//        sb.append("ABC");
//        System.out.println(sb);
//
//
//        // String literal
//        String str = "Edureka";
//        String str1 = "Edureka";
//        str = "New Edureka";
//        System.out.println(str);
//        System.out.println(str1);
//
//        // String gobject
//        String s1 = new String("Hello World");
//        String s2 = new String ("Hello World");

        // String methods
        String testStr = "Hello, My name is Abhay";
        System.out.println(testStr.equals(test));
        System.out.println("ABHAY".equalsIgnoreCase("abhay"));
        System.out.println(testStr.length());
        System.out.println(testStr.charAt(5));
        System.out.println(testStr.toLowerCase());
        System.out.println(testStr.toUpperCase());
        System.out.println(testStr.replace("a", "@"));
        System.out.println(testStr.contains("my"));
        System.out.println(testStr.endsWith("Abhay"));
        System.out.println("abhay".equals("abhay"));
        System.out.println("abhay".compareTo("abhay"));
        System.out.println("abhay" == "abhay");


        // Substring
        String anotherStr = "John, Jennie, Jim, Jack, Joe";
        System.out.println(anotherStr.substring(5));
        System.out.println(anotherStr.substring(6, 11));

        // Split
        String splitStr = "abhay, tom, simon";
        String[] splitted = splitStr.split(",");
        System.out.println(Arrays.toString(splitted));

        // Immutable
        String imStr = new String("Hello");
        imStr.concat(" World");
        System.out.println(imStr);
    }
}
