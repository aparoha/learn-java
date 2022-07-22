package basics.collections;

import java.util.Stack;

public class _03_StackExamples {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < 5; i++) {
            stack.push(i);
        }
        for(int i = 0; i < 5; i++) {
            Integer y = stack.pop();
            System.out.println(y);
        }
    }
}
