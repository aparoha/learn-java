package leetcode.graph;

public class WordSearch_79 {

    public boolean exist(char[][] board, String word) {
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                if(board[i][j] == word.charAt(0) && dfs(board, word, i, j, 0)){
                    return true;
                }
            }
        }

        return false;
    }

    public boolean dfs(char[][] board, String word, int i, int j, int k){
        int[] di={-1,0,1,0};
        int[] dj={0,1,0,-1};
        char t = board[i][j];
        board[i][j]='#';
        for(int m=0; m<4; m++){
            int pi=i+di[m];
            int pj=j+dj[m];
            if (isValidMove(board, pi, pj)
                    && board[pi][pj] == word.charAt(k + 1)
                    && dfs(board, word, pi, pj, k + 1)
            || k == word.length() - 1) {
                return true;
            }
        }
        board[i][j]=t;
        return false;
    }

    private boolean isValidMove(char[][] board, int pi, int pj) {
        return pi >= 0 && pi < board.length && pj >= 0 && pj < board[0].length;
    }
}
