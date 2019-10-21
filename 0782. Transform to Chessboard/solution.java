/**
 * Other's solution
 *
 * Time Complexity: O(N^2)
 */
class Solution {
    public int movesToChessboard(int[][] board) {
        int N = board.length, rowSum = 0, colSum = 0, rowSwap = 0, colSwap = 0;
		
		// In a valid chess board, there are 2 and only 2 kinds of rows and one is inverse to the other.
		// For example if there is a row 01010011 in the board, any other row must be either 01010011 or 10101100.
		// The same for columns
		// A corollary is that, any rectangle inside the board with corners top left, top right, bottom left,
		// bottom right must be 4 zeros or 2 ones 2 zeros or 4 zeros.
		for (int i = 0; i < N; i++) {
		    for (int j = 0; j < N; j++) {
			    if ((board[0][0] ^ board[0][j] ^ board[i][0] ^ board[i][j]) == 1)
                    return -1;
			}
		}
		
		// Another important property is that every row and column has half ones.
		// Assume the board is N * N:
		// If N = 2*K, every row and every column has K ones and K zeros.
		// If N = 2*K + 1, every row and every column has K ones and K + 1 zeros or K + 1 ones and K zeros.
		for (int i = 0; i < N; i++) {
		    // a swap contains 2 row changes or 2 column changes
		    rowSum += board[0][i]; // count of row change
			colSum += board[i][0]; // count of column change
			
			// Assume the left top cell should start as '1',
			// the valid case of the left top cell start as '0' could be handled at the last step
			// by using subtraction and Math.Min()
			if (board[0][i] == i % 2) colSwap++;
			if (board[i][0] == i % 2) rowSwap++;
		}
		if ((rowSum != N/2) && (rowSum != (N + 1)/2)) return -1;
		if ((colSum != N/2) && (colSum != (N + 1)/2)) return -1;
		
		// A row should be moved into '01010' or '10101' and the number of swaps should be counted.
		// In case of N even, take the minimum swaps, because both are possible.
		// In case of N odd, I take the even swaps. Because when we make a swap, we move 2 columns
		// or 2 rows at the same time.
		// So col swaps and row swaps should be same here.
		// Use '100' as example, it is impossible to do an odd number of colSwap to get to '101',
		// so we must go for '010', which is an even number of colSwap.
		if (N % 2 == 1) {
		    if (colSwap % 2 == 1) colSwap = N - colSwap;
			if (rowSwap % 2 == 1) rowSwap = N - rowSwap;
		} else {
		    colSwap = Math.min(colSwap, N - colSwap);
			rowSwap = Math.min(rowSwap, N - rowSwap);
		}
		
		// a swap contains 2 row changes or 2 column changes
		return rowSwap / 2 + colSwap / 2;
    }
}
