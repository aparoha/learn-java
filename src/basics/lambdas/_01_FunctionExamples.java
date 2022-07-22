package basics.lambdas;

import java.util.function.Function;

public class _01_FunctionExamples {
    public static void main(String[] args) {
        Function<Long, Long> adder = input -> input + 3;
        Long result = adder.apply((long) 4);
        System.out.println("result = " + result);
    }
}
