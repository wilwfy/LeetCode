/**
 * Official solution of Layer-by-Layer
 *
 * Intuition
 * The answer will be all the elements in clockwise order from the first-outer layer, followed by the elements from the second-outer layer, and so on.
 *
 * A diagram about the algorithm is at:
 *  https://github.com/wilwfy/LeetCode/blob/master/0054.%20Spiral%20Matrix/54_algorithm.png
 *
 * Time Complexity: O(N), where N is the total number of elements in the input matrix. We add every element in the matrix to our final answer.
 * Space Complexity: O(1) without considering the output array, since we don't use any additional data structures for our computations.
 *                   O(N) if the output array is taken into account.
 */
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix.length == 0) return res;
        
        int r1 = 0, r2 = matrix.length - 1;
        int c1 = 0, c2 = matrix[0].length - 1;
        while (r1 <= r2 && c1 <= c2) {
            for (int c = c1; c <= c2; c++)
                res.add(matrix[r1][c]);
            for (int r = r1 + 1; r <= r2; r++)
                res.add(matrix[r][c2]);
            if (r1 < r2 && c1 < c2) {
                for (int c = c2 - 1; c > c1; c--)
                    res.add(matrix[r2][c]);
                for (int r = r2; r > r1; r--)
                    res.add(matrix[r][c1]);
            }
            r1++;
            c1++;
            r2--;
            c2--;
        }
        return res;
    }
}
