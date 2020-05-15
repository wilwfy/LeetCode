/**
 * My solution of Backtracking with extra space
 */
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


/**
 * Other's solution of Backtracking without extra space
 */
class Solution {
    public boolean exist(char[][] board, String word) {
        for(int i = 0; i < board.length; i++)
            for(int j = 0; j < board[0].length; j++){
                if(exist(board, i, j, word, 0))
                    return true;
            }
        return false;
    }
    private boolean exist(char[][] board, int i, int j, String word, int ind){
        if(ind == word.length()) return true;
        if(i > board.length-1 || i <0 || j<0 || j >board[0].length-1 || board[i][j]!=word.charAt(ind))
            return false;
        board[i][j]='*';
        boolean result =    exist(board, i-1, j, word, ind+1) ||
                            exist(board, i, j-1, word, ind+1) ||
                            exist(board, i, j+1, word, ind+1) ||
                            exist(board, i+1, j, word, ind+1);
        board[i][j] = word.charAt(ind);
        return result;
    }
}


/**
 * Other's solution of Backtracking without extra space
 */
class Solution {
    public boolean exist(char[][] board, String word) {
        char[] w = word.toCharArray();
        for (int y=0; y<board.length; y++) {
        	for (int x=0; x<board[y].length; x++) {
        		if (exist(board, y, x, w, 0)) return true;
        	}
        }
        return false;
    }
    
    private boolean exist(char[][] board, int y, int x, char[] word, int i) {
    	if (i == word.length) return true;
    	if (y<0 || x<0 || y == board.length || x == board[y].length) return false;
    	if (board[y][x] != word[i]) return false;
    	board[y][x] ^= 256;
    	boolean exist = exist(board, y, x+1, word, i+1)
    		|| exist(board, y, x-1, word, i+1)
    		|| exist(board, y+1, x, word, i+1)
    		|| exist(board, y-1, x, word, i+1);
    	board[y][x] ^= 256;
    	return exist;
    }
}
