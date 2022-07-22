package datastructures.grids;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

/*
        There are many ways to traverse a grid. The method I prefer for traversing a grid can be summarized into two functions:
        1. A recursive function that uses two direction vectors to traverse the grid.
        2. A validator function that makes sure that the generated coordinate is within the bounds of the grid.
        3. Grid traversal requires very trivial data structures, a boolean matrix to keep track of the visited cells and two direction vectors to generate neighboring coordinates.
        4. A direction vector is an array that consists of integers that are used to denote the neighbors of a cell. We use two direction vectors namely x and y, that will be used
           to generate the neighbors of a cell in the matrix

                                  [X-1,Y-1]         [X, Y-1]          [X+1,Y-1]
                                  [X-1, Y]          [X,   Y]          [X+1,  Y]
                                  [X-1, Y+1]        [X, Y+1]          [X+1, Y+1]

            This gives us following direction vectors
            X = [0,1,1,1,0,-1,-1,-1]
            Y = [-1,-1,0,1,1,1,0,-1]



            How to represent coordinates of grid in BFS?
            --------------------------------------------

            1. Create class Coordinate with x, y properties
            2. Use string with delimiter - "x,y"
            3. Represent coordinate with array - [x,y]
            4. integer x,y -> x (convert a 2d mapping to 1d mapping)
                    1. No of columns = cols
                        2D to 1D = x * cols + y
                    2. 1D to 2D (when poll from queue)
                        row = index / cols
                        col = index % cols


 */
public class TwoDGridTraversal {

    public static void main(String[] args) {
        int rows = 3, columns = 3;
        int[][] directions = new int[][] {
                {0, -1},
                {1,  0},
                {0,  1},
                {-1,  0}
        };
        int[][] grid = { { -1, 2, 3 },
                         {  0, 9, 8 },
                         {  1, 0, 1 }
                        };
        boolean[][] visited = new boolean[rows][columns];
        dfs(0, 0, grid, visited, rows, columns, directions);
        System.out.println("\n");
        bfs(grid);
    }

    private static void bfs(int[][] grid) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        int[][] directions = new int[][] {
                {0, -1},
                {1,  0},
                {0,  1},
                {-1,  0}
        };
        int rows = grid.length;
        int columns = grid[0].length;
        BiFunction<Integer, Integer, Integer> towDToOneD = (x, y) -> x * columns + y;
        queue.add(towDToOneD.apply(0, 0));
        visited.add(towDToOneD.apply(0, 0));
        while (!queue.isEmpty()) {
            Integer current = queue.poll();
            int currentRow = current / columns;
            int currentColumn = current % columns;
            System.out.print(grid[currentRow][currentColumn] + " ");
            for (int[] direction : directions) {
                int rowDir = currentRow + direction[0];
                int columnDir = currentColumn + direction[1];
                if(isValid(rows, columns, rowDir, columnDir) && !visited.contains(towDToOneD.apply(rowDir, columnDir))) {
                    queue.add(towDToOneD.apply(rowDir, columnDir));
                    visited.add(towDToOneD.apply(rowDir, columnDir));
                }
            }
        }
    }

    private static void dfs(int currentRow, int currentColumn, int[][] grid, boolean visited[][], int rows, int columns, int[][] directions) {
        // Mark the current cell as visited
        visited[currentRow][currentColumn] = true;
        System.out.print(grid[currentRow][currentColumn] + " ");
        for (int[] direction : directions) {
            int rowDir = currentRow + direction[0];
            int columnDir = currentColumn + direction[1];
            if(isValid(rows, columns, rowDir, columnDir) && !visited[rowDir][columnDir])
                dfs(rowDir, columnDir, grid, visited, rows, columns, directions);
        }
    }

    private static boolean isValid(int rows, int columns, int rowDir, int columnDir) {
        return rowDir >= 0 && rowDir < rows && columnDir >= 0 && columnDir < columns;
    }
}
