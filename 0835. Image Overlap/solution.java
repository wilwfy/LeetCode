/**
 * Official solution of Shift and Count
 *
 * One important insight is that shifting one matrix to a direction is equivalent to shifting the other matrix
 * to the opposite direction, in the sense that we would have the same overlapping zone at the end.
 * 
 * Algorithm
 * 
 * Based on the above intuition, we could implement the solution step by step. First we define the
 * function shift_and_count(x_shift, y_shift, M, R) where we shift the matrix M with reference to
 * the matrix R with the shifting coordinate (x_shift, y_shift) and then we count the overlapping
 * ones in the overlapping zone.
 * - The algorithm is organized as a loop over all possible combinations of shifting coordinates (x_shift, y_shift).
 * - More specifically, the ranges of x_shift and y_shift are both [0, N-1] where NN is the width of the matrix.
 * - At each iteration, we invoke the function shift_and_count() twice to shift and count the overlapping zone,
 *   first with the matrix B as the reference and vice versa.
 *
 * Complexity Analysis
 * 
 * Let N be the width of the matrix.
 * 
 * First of all, let us calculate the number of all possible shiftings, (i.e. the number of overlapping zones).
 * 
 * For a matrix of length N, we have 2(N−1) possible offsets along each axis to shift the matrix. Therefore,
 * there are in total 2(N-1) * 2(N-1) = 4(N-1)^2 possible overlapping zones to calculate.
 * 
 * Time Complexity: O(N^4).
 *                  As discussed before, we have in total 4(N-1)^2 possible overlapping zones.
 *                  The size of the overlapping zone is bounded by O(N^2).
 *                  Since we iterate through each overlapping zone to find out the overlapping ones,
 *                  the overall time complexity of the algorithm would be 4(N-1)^2 * O(N^2) = O(N^4).
 * Space Complexity: O(1). As one can see, a constant space is used in the above algorithm.
 */
class Solution {
    protected int shiftAndCount(int xShift, int yShift, int[][] M, int[][] R) {
        int count = 0;
        int rRow = 0;
        // count the cells of ones in the overlapping zone.
        for (int mRow = yShift; mRow < M.length; ++mRow) {
            int rCol = 0;
            for (int mCol = xShift; mCol < M.length; ++mCol) {
                if (M[mRow][mCol] == 1 && M[mRow][mCol] == R[rRow][rCol])
                    count += 1;
                rCol += 1;
            }
            rRow += 1;
        }
        return count;
    }

    public int largestOverlap(int[][] A, int[][] B) {
        int maxOverlaps = 0;

        for (int yShift = 0; yShift < A.length; ++yShift)
            for (int xShift = 0; xShift < A.length; ++xShift) {
                // move one of the matrice up and left and vice versa.
                // (equivalent to move the other matrix down and right)
                maxOverlaps = Math.max(maxOverlaps, shiftAndCount(xShift, yShift, A, B));
                maxOverlaps = Math.max(maxOverlaps, shiftAndCount(xShift, yShift, B, A));
            }

        return maxOverlaps;
    }
}


/**
 * Official solution of Linear Transformation
 *
 * the key insight is that all the cells in the same overlapping zone would share the same linear
 * transformation vector.
 * 
 * Algorithm
 * 
 * The algorithm can be implemented in two overall steps.
 * 
 * First, we filter out those non-zero cells in each matrix respectively.
 * 
 * Second, we do a cartesian product on the non-zero cells. For each pair of the products, we calculate
 * the corresponding linear transformation vector as V_ab = (X_b - X_a, Y_b - Y_a). Then, we count the
 * number of the pairs that have the same transformation vector, which is also the number of ones in
 * the overlapping zone.
 *
 * Time Complexity: O(N^4).
 *                  In the first step, we filter out the non-zero cells in each matrix, which would take O(N^2) time.
 *                  In the second step, we enumerate the cartesian product of non-zero cells between the two matrices,
 *                  which would take O(M_a * M_b) time. In the worst case, both M_a and M_b would be up to N^2, i.e.
 *                  matrix filled with ones.
 *                  To sum up, the overall time complexity of the algorithm would be O(N^2) + O(N^2 * N^2) = O(N^4).
 *                  Although this approach has the same time complexity as the previous approach, it should run faster
 *                  in practice, since we ignore those zero cells.
 * Space Complexity: O(N^2).
 *                   We kept the indices of non-zero cells in both matrices. In the worst case, we would need the
 *                   O(N^2) space for the matrices filled with ones.
 */
class Solution {
    protected List<Pair<Integer, Integer>> non_zero_cells(int[][] M) {
        List<Pair<Integer, Integer>> ret = new ArrayList<>();
        for (int row = 0; row < M.length; ++row)
            for (int col = 0; col < M.length; ++col)
                if (M[row][col] == 1)
                    ret.add(new Pair<>(row, col));
        return ret;
    }

    public int largestOverlap(int[][] A, int[][] B) {

        List<Pair<Integer, Integer>> A_ones = non_zero_cells(A);
        List<Pair<Integer, Integer>> B_ones = non_zero_cells(B);

        int maxOverlaps = 0;
        HashMap<Pair<Integer, Integer>, Integer> groupCount = new HashMap<>();

        for (Pair<Integer, Integer> a : A_ones)
            for (Pair<Integer, Integer> b : B_ones) {
                Pair<Integer, Integer> vec =
                    new Pair<>(b.getKey() - a.getKey(), b.getValue() - a.getValue());

                if (groupCount.containsKey(vec)) {
                    groupCount.put(vec, groupCount.get(vec) + 1);
                } else {
                    groupCount.put(vec, 1);
                }
                maxOverlaps = Math.max(maxOverlaps, groupCount.get(vec));
            }

        return maxOverlaps;
    }
}


/**
 * Official solution of Imagine Convolution
 *
 * Intuition
 * As it turns out, the number of overlapped ones in an overlapping zone is equal to the result of
 * performing a convolution operation between two matrices.
 * 
 * Rather than manually counting the number of overlapping ones, we could perform the convolution
 * operation instead.
 * 
 * Algorithm
 * 
 * Overall, we enumerate all possible kernels (by shifting the matrix B), and then perform the convolution
 * operation to count the overlapping ones. The algorithm can be broke down into the following steps:
 * 
 * - First of all, we extend both the width and height of the matrix B to N + 2(N-1) = 3N-2N+2(N−1)=3N−2, and
 *   pad the extended cells with zeros, as follows:
 * 
 * - From the extended and padded matrix B, we then can extract the kernel one by one.
 * 
 * - For each kernel, we perform the convolution operation with the matrix A to count the number of overlapping ones.
 * 
 * - At the end, we return the maximal value of overlapping ones.
 *
 * Complexity Analysis
 * 
 * Let N be the width of the matrix.
 * 
 * Time Complexity: O(N^4).
 *                  We iterate through (2N-1) * (2N-1) number of kernels. For each kernel, we perform
 *                  a convolution operation, which takes O(N^2) time.
 *                  To sum up, the overall time complexity of the algorithm would be (2N-1) * (2N-1) * O(N^2) = O(N^4).
 * Space Complexity: O(N^2).
 *                   We extend the matrix B to the size of (3N-2) * (3N-2), which would require the space of O(N^2).
 */
class Solution {

    protected int convolute(int[][] A, int[][] kernel, int xShift, int yShift) {
        int result = 0;
        for (int row = 0; row < A.length; ++row)
            for (int col = 0; col < A.length; ++col)
                result += A[row][col] * kernel[row + yShift][col + xShift];
        return result;
    }

    public int largestOverlap(int[][] A, int[][] B) {

        int N = A.length;
        int[][] B_padded = new int[3 * N - 2][3 * N - 2];
        for (int row = 0; row < N; ++row)
            for (int col = 0; col < N; ++col)
                B_padded[row + N - 1][col + N - 1] = B[row][col];

        int maxOverlaps = 0;
        for (int xShift = 0; xShift < 2 * N - 1; ++xShift)
            for (int yShift = 0; yShift < 2 * N - 1; ++yShift) {
                maxOverlaps = Math.max(maxOverlaps,
                    convolute(A, B_padded, xShift, yShift));
            }

        return maxOverlaps;
    }
}
