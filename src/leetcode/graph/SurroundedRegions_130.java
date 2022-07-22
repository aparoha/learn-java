package leetcode.graph;

/*
        Given an m x n matrix board containing 'X' and 'O', capture all regions that are 4-directionally surrounded by 'X'.
        A region is captured by flipping all 'O's into 'X's in that surrounded region.

        Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
        Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
        Explanation: Notice that an 'O' should not be flipped if:
        - It is on the border, or
        - It is adjacent to an 'O' that should not be flipped.
        The bottom 'O' is on the border, so it is not flipped.
        The other three 'O' form a surrounded region, so they are flipped.

        Example 2:

        Input: board = [["X"]]
        Output: [["X"]]

        Constraints:

        m == board.length
        n == board[i].length
        1 <= m, n <= 200
        board[i][j] is 'X' or 'O'.
 */
public class SurroundedRegions_130 {

    public void solve(char[][] board) {
        /*
            1. Capture un surrounded region (O's in boundaries) and mark them as some temporary character i.e. (O -> T)
            2. Capture surrounded region (where O exists), mark these O's as X (O -> X)
            3. Un capture un surrounded region (T -> O)
         */

        if(board == null || board.length == 0)
            return;
        int rows = board.length, columns = board[0].length;
        int[][] directions = new int[][] {{0, -1}, {1,  0}, {0,  1}, {-1,  0}};
        for (int row = 0; row < rows; row++){
            for (int column = 0; column < columns; column++){
                if ((row == 0 || row == rows-1 || column == 0 || column == columns-1)
                        && board[row][column] == 'O'){
                    capture(board, row, column, rows, columns, directions);
                }
            }
        }

        for (int row = 0; row < rows; row++){
            for (int column = 0; column < columns; column++){
                if (board[row][column] == 'T')
                    board[row][column] = 'O';
                else if (board[row][column] == 'O')
                    board[row][column] = 'X';
            }
        }
    }

    private void capture(char[][] board, int row, int column, int rows, int columns,
                         int[][] directions){
        board[row][column] = 'T';
        for (int[] direction : directions) {
            int rowDir = row + direction[0];
            int columnDir = column + direction[1];
            if(isValid(rows, columns, rowDir, columnDir) && board[rowDir][columnDir] == 'O')
                capture(board, rowDir, columnDir, rows, columns, directions);
        }
    }

    private boolean isValid(int rows, int columns, int rowDir, int columnDir) {
        return rowDir >= 0 && rowDir < rows && columnDir >= 0 && columnDir < columns;
    }
}
