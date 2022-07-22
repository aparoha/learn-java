package leetcode.graph;

/*

Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume
all four edges of the grid are all surrounded by water.

Example 1:

Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1
Example 2:

Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] is '0' or '1'.

 */

import datastructures.unionfind.DisjointSet;

import java.util.function.BiFunction;

public class NumberOfIslands_200 {

    public static int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0)
            return 0;
        int rows = grid.length, columns = grid[0].length;
        int[][] directions = new int[][] {{0, -1}, {1,  0}, {0,  1}, {-1,  0}};
        int count = 0;
        for (int row = 0; row < rows; row++){
            for (int column = 0; column < columns; column++){
                if (grid[row][column] == '1'){
                    countIslands(grid, row, column, rows, columns, directions);
                    count++;
                }
            }
        }
        return count;
    }

    private static void countIslands(char[][] grid, int row, int column, int rows, int columns, int[][] directions){
        grid[row][column] = '0';
        for (int[] direction : directions) {
            int rowDir = row + direction[0];
            int columnDir = column + direction[1];
            if(isValid(rows, columns, rowDir, columnDir) && grid[rowDir][columnDir] == '1')
                countIslands(grid, rowDir, columnDir, rows, columns, directions);
        }
    }

    private static boolean isValid(int rows, int columns, int rowDir, int columnDir) {
        return rowDir >= 0 && rowDir < rows && columnDir >= 0 && columnDir < columns;
    }

    public static int numIslands2(char[][] grid) {
        if(grid == null || grid.length == 0)
            return 0;
        int rows = grid.length, columns = grid[0].length;
        int[][] directions = new int[][] {{0, -1}, {1,  0}, {0,  1}, {-1,  0}};
        BiFunction<Integer, Integer, Integer> towDToOneD = (x, y) -> x * columns + y;
        DisjointSet<Integer> ds = new DisjointSet<>();
        for (int row = 0; row < rows; row++){
            for (int column = 0; column < columns; column++){
                if (grid[row][column] == '1'){
                    ds.makeSet(towDToOneD.apply(row, column));
                    for (int[] direction : directions) {
                        int rowDir = row + direction[0];
                        int columnDir = column + direction[1];
                        if(isValid(rows, columns, rowDir, columnDir) && grid[rowDir][columnDir] == '1') {
                            ds.union(towDToOneD.apply(row, column), towDToOneD.apply(rowDir, columnDir));
                        }
                    }
                }
            }
        }
        return ds.getNumComponents();
    }

    public static void main(String[] args) {
        char[][] grid = new char[][]  {
                    {'1','1','0','0','0'},
                    {'1','1','0','0','0'},
                    {'0','0','1','0','0'},
                    {'0','0','0','1','1'}
                };
        System.out.println(numIslands2(grid));
    }
}
