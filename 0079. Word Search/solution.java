class Solution {
    public boolean exist(char[][] board, String word) {
        if ((board == null) || (board.length == 0)) return false;
        int[][] visited = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (helper(board, visited, i, j, word))
                        return true;
                }
            }
        }
        return false;
    }
    
    private boolean helper(char[][] board, int[][] visited, int row, int col, String word) {
        if ((row < 0) || (row >= board.length) || (col < 0) || (col >= board[0].length) ||
            (visited[row][col] == 1)) return false;
        if (board[row][col] != word.charAt(0)) return false;
        if (word.length() == 1) return true;
        visited[row][col] = 1;
        boolean res = false;
        res = res || helper(board, visited, row, col - 1, word.substring(1));
        res = res || helper(board, visited, row, col + 1, word.substring(1));
        res = res || helper(board, visited, row - 1, col, word.substring(1));
        res = res || helper(board, visited, row + 1, col, word.substring(1));
        visited[row][col] = 0;
        return res;
    }
}
