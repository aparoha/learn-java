package basics.lambdas;

import java.util.function.Consumer;
import java.util.function.Function;

// Hello: ABC BlaBlBla
public class LambdaExamples {

    static String thirdText = "Hey, a third text";

    public static void main(String[] args) {
        //Effectively final rule applies to local variable only
        final String otherText = "Hello: ";
        //Variable Capture
        Consumer<String> consumer = text -> System.out.println(otherText + text + " " + thirdText);
        thirdText = "BlaBlBla";
        consumer.accept("ABC");
    }
}
