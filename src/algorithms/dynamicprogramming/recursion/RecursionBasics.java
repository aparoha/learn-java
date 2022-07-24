package algorithms.dynamicprogramming.recursion;

import java.util.Arrays;

/*
    1. Recursion is based on principal of mathematical induction
    2. Figure of the smallest case
    3. Always assume the subproblem can be solved
    4. Solve the current problem assuming subproblem solution exists
 */
public class RecursionBasics {

    // TC - O(N)
    // SC - O(N)
    public static int factorial(int n) {
        // base case
        if (n == 0)
            return 1;
        // recursive case
        int ans = n * factorial(n -1);
        return ans;
    }

    // TC - O(2^N)
    // SC - O(N)
    public static int fib(int n) {
        // base case
        if (n == 0 || n == 1)
            return n;
        int ans = fib(n - 1) + fib(n -2);
        return ans;
    }

    public static boolean isSorted (int[] arr, int index, int n) {
        if (index == n - 1)
            return true;
        if (arr[index] < arr[index + 1] && isSorted(arr, index + 1, n))
            return true;
        return false;
    }

    public static void printIncreasing(int n) {
        if (n == 0)
            return;
        printIncreasing(n - 1);
        System.out.println(n);
    }

    public static void printDecreasing(int n) {
        if (n == 0)
            return;
        System.out.println(n);
        printDecreasing(n - 1);
    }

    // TC - O(N)
    // SC - O(N)
    public static int power(int a, int n) {
        if (n == 0)
            return 1;
        int ans = a * power(a, n - 1);
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(factorial(5));
        System.out.println(fib(7));
        System.out.println(isSorted(new int[] { 1, 2, 3, 4, 5, 6, 7}, 0, 7));
        printIncreasing(5);
        printDecreasing(5);
        System.out.println(power(2, 3));
    }
}
