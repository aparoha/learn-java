package datastructures.graphs.design;

/*
    Your task is to implement an algorithm that can find the way out of a maze.

[
[1,1,1,1,1],
[1,2,0,1,1],
[1,1,0,1,1],
[1,1,0,0,0],
[1,1,1,1,3],
]
So we have a map like this

integer 1 represents walls
integer 2 is the starting point
integer 3 is the destination (so this is what we are looking for)
integer 0 represents the states we can consider (so the solution contains one or more 0 states)

So the solution should be something like this (S represents the states in the solution set):

[
[-,-,-,-,-],
[-,2,S,-,-],
[-,-,S,-,-],
[-,-,S,S,S],
[-,-,-,-,3],
]

 */
public class MazeEscape {

    private int[][] maze;
    private boolean[][] visited;
    private int startRow;
    private int startColumn;

    public MazeEscape(int[][] maze, int startRow, int startColumn) {
        this.maze = maze;
        this.visited = new boolean[maze.length][maze.length];
        this.startRow = startRow;
        this.startColumn = startColumn;
    }

    public void findWay() {
        if(dfs(startRow, startColumn))
            System.out.println("Solution exists....");
        else
            System.out.println("No solution exists......");
    }

    private boolean dfs(int x, int y) {
        // Base case - reached destination (last row, last column)
        if (x == maze.length - 1 && y == maze.length - 1)
            return true;
        if (isFeasible(x, y)) {
            visited[x][y] = true;
            // visit next cells in 4 directions
            if(dfs(x + 1, y))
                return true;
            if(dfs(x - 1, y))
                return true;
            if(dfs(x, y + 1))
                return true;
            if(dfs(x, y - 1))
                return true;
            //backtrack
            visited[x][y] = false;
            return false;
        }
        return false;
    }

    private boolean isFeasible(int x, int y) {
        // Check vertical dimension
        if (x < 0 || x > maze.length - 1)
            return false;

        // Check horizontal dimension
        if (y < 0 || y > maze.length - 1)
            return false;

        // Already considered that state
        if (visited[x][y])
            return false;

        // Obstacle
        if (maze[x][y] == 1)
            return false;

        return true;
    }

    public static void main(String[] args) {
        //integer 1 represents walls
        //integer 2 is the starting point
        //integer 3 is the destination (so this is what we are looking for)
        int[][] map = {
                {1, 1, 1, 1, 1, 1},
                {2, 1, 0, 0, 0, 1},
                {0, 1, 0, 1, 0, 1},
                {0, 1, 0, 1, 0, 0},
                {0, 1, 0, 1, 1, 0},
                {0, 0, 0, 1, 0, 0}
        };
        MazeEscape mazeEscape = new MazeEscape(map, 1, 0);
        mazeEscape.findWay();;
    }
}
