package companies.grab;

import java.util.*;

/*
    498. Diagonal Traverse

    Given an m x n mat mat, return an array of all the elements of the array in a diagonal order.

    Input: mat = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,4,7,5,3,6,8,9]
Example 2:

Input: mat = [[1,2],[3,4]]
Output: [1,2,3,4]
 */
public class DiagonalTraverse {

    public int[] findDiagonalOrder(int[][] mat) {

        /*
            Key observation - sum of indices on all diagonals are equal

            [1,2,3]
            [4,5,6]
            [7,8,9]

            2, 4 are on the same diagonal, and they share the index sum of 1. (2 is matrix[0][1] and 4 is in matrix[1][0]).
            3,5,7 are on the same diagonal, and they share the sum of 2. (3 is matrix[0][2], 5 is matrix[1][1], and 7 is matrix [2][0]).

            if you can loop through the matrix, store each element by the sum of its indices in a dictionary, you have a collection of
            all elements on shared diagonals.

            0 -> [1]
            1 -> [2, 4]
            2 -> [7, 5, 3]
            3 -> [6, 8]
            4 -> [9]

            build your answer (a list) by elements on diagonals. To capture the 'zig zag' or 'snake' phenomena of this problem, simply reverse ever other diagonal level.
            So check if the level is divisible by 2.

         */
        if (mat == null || mat.length == 0)
            return new int[0];

        Map<Integer, List<Integer>> map = new LinkedHashMap<>();
        int rows = mat.length;
        int cols = mat[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int indicesSum = i + j;
                if (!map.containsKey(indicesSum)) {
                    map.put(indicesSum, new ArrayList<>());
                }
                map.get(indicesSum).add(mat[i][j]);
            }
        }

        int[] result = new int[rows * cols];
        int idx = 0;
        for (int i : map.keySet()) {
            List<Integer> list = map.get(i);
            if (i % 2 == 0) {
                Collections.reverse(list);
            }
            for (int number : list)
                result[idx++] = number;
        }
        return result;
    }

    public static int[] findDiagonalOrder2(int[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return new int[0];

        int row = 0, col = 0, rows = matrix.length, columns = matrix[0].length;
        int[] output = new int[rows * columns];

        for (int pos = 0; pos < rows * columns; pos++) {
            output[pos] = matrix[row][col];
            // The direction is always up when the sum of row & col is even
            if ((row + col) % 2 == 0) {
                // For last column, go down
                if (col == columns - 1) {
                    row++;
                }
                // For first row & non-last columns, go right
                else if (row == 0) {
                    col++;
                }
                // For not first row & non-last columns, go up and to the right
                else {
                    row--;
                    col++;
                }
            } else {
                // The direction is always down when the sum of row & col is odd
                // For last row, go right
                if (row == rows - 1) {
                    col++;
                }
                //  For non-last row & first column, go down
                else if (col == 0) {
                    row++;
                }
                // For non-last row & non-first column, go down and to the left
                else {
                    row++;
                    col--;
                }
            }
        }
        return output;
    }

    public static void main(String[] args) {
        int[][] matrx = new int[][] {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[] result = findDiagonalOrder2(matrx);
    }
}
