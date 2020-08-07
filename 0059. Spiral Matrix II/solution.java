/**
 * My solution based on 0054 Spiral Matrix
 *
 * Time Complexity: O(N), where N is the total number of elements in the input matrix. We add every element in the matrix to our final answer.
 * Space Complexity: O(1) without considering the output array, since we don't use any additional data structures for our computations.
 *                   O(N) if the output array is taken into account.
 */
class Solution {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int cnt = 1;
        int topRow = 0, botRow = n - 1;
        int leCol = 0, riCol = n - 1;
        while (topRow <= botRow && leCol <= riCol) {
            for (int j = leCol; j <= riCol; j++)
                res[topRow][j] = cnt++;
            for (int i = topRow + 1; i <= botRow; i++)
                res[i][riCol] = cnt++;
            if (topRow < botRow) {
                for (int j = riCol - 1; j >= leCol; j--)
                    res[botRow][j] = cnt++;
            }
            if (leCol < riCol) {
                for (int i = botRow - 1; i > topRow; i--)
                    res[i][leCol] = cnt++;
            }
            topRow++;
            leCol++;
            botRow--;
            riCol--;
        }
        return res;
    }
}
