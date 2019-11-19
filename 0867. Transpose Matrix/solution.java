/**
 * Time Complexity: O(R * C), where R and C are the number of rows and columns in the given matrix A.
 * Space Complexity: O(R * C), the space used by the answer.
 */
class Solution {
    public int[][] transpose(int[][] A) {
        int M = A.length, N = A[0].length;
        int[][] A_T = new int[N][M];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                A_T[j][i] = A[i][j];
            }
        }
        return A_T;
    }
}
