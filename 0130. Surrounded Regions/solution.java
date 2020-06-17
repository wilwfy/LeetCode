/**
 * Other's solution of DFS + boundary cell turning
 *
 * Time: O(m*n)
 * Space: O(1)
 */
class Solution {
    public void solve(char[][] board) {
        if (board.length == 0 || board[0].length == 0) return;
        if (board.length < 3 || board[0].length < 3) return;
        int m = board.length, n = board[0].length;
	    //Any 'O' connected to a boundary can't be turned to 'X', so ...
	    //Start from first and last column, turn 'O' to '*'.
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') boundaryDFS(board, i, 0);
            if (board[i][n-1] == 'O') boundaryDFS(board, i, n-1);
        }
        //Start from first and last row, turn '0' to '*'
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') boundaryDFS(board, 0, j);
            if (board[m-1][j] == 'O') boundaryDFS(board, m-1, j);
        }
        //post-prcessing, turn 'O' to 'X', '*' back to 'O', keep 'X' intact.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                else if (board[i][j] == '*')
                    board[i][j] = 'O';
            }
        }
        return;
    }
    
    //Use DFS algo to turn internal however boundary-connected 'O' to '*';
    public void boundaryDFS(char[][] board, int row, int col) {
        if (row < 0 || row > board.length - 1 || col < 0 || col > board[0].length - 1 || board[row][col] != 'O') return;
        
        board[row][col] = '*';
        boundaryDFS(board, row - 1, col);
        boundaryDFS(board, row + 1, col);
        boundaryDFS(board, row, col - 1);
        boundaryDFS(board, row, col + 1);
    }
}
