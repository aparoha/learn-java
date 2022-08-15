package companies.grab;

/*
Given a 2-D matrix mat[][] the task is to count the number of connected components in the matrix.
A connected component is formed by all equal elements that share some common side with at least
one other element of the same component.

Examples:


Input: mat[][] = {"bbba",
                  "baaa"}
Output: 2

The two connected components are:
bbb
b

AND

  a
aaa

Input: mat[][] = {"aabba",
                  "aabba",
                  "aaaca"}
Output: 4

Time Complexity: O(row * cols)
Auxiliary Space: O(row * cols)
 */


public class ConnectedComponents {

    static int connectedComponents(char[][] matrix) {
        int connectedComp = 0;
        int rows = matrix.length;
        int columns = matrix[0].length;
        boolean[][] visited = new boolean[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (!visited[i][j]) {
                    DFS(matrix, i, j, matrix[i][j], rows, columns, visited);
                    connectedComp++;
                }
            }
        }
        return connectedComp;
    }

    private static void DFS(char[][] matrix, int row, int col, char c, int rows, int columns, boolean[][] visited) {
        // These arrays are used to get row and column numbers of 4 neighbours of a given cell
        int rowNbr[] = {-1, 1, 0, 0};
        int colNbr[] = {0, 0, 1, -1};

        // Mark this cell as visited
        visited[row][col] = true;

        // Recur for all connected neighbours
        for (int k = 0; k < 4; ++k) {
            if (isSafe(matrix, row + rowNbr[k], col + colNbr[k], c, rows, columns, visited)) {
                DFS(matrix, row + rowNbr[k], col + colNbr[k], c, rows, columns, visited);
            }
        }
    }

    private static boolean isSafe(char[][] matrix, int row, int col, char c, int rows, int columns, boolean[][] visited)
    {
        return (row >= 0 && row < rows) &&
                (col >= 0 && col < columns) &&
                (matrix[row][col] == c &&
                        !visited[row][col]);
    }

    public static void main(String[] args) {
        char[][] matrix = {
                {'a', 'a', 'b', 'b', 'a'},
                {'a', 'a', 'b', 'b', 'a'},
                {'a', 'a', 'a', 'c', 'a'}
        };
        System.out.println(connectedComponents(matrix));
    }
}
