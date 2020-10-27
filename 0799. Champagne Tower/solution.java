/**
 * Official solution of Simulation
 *
 * Intuition
 * 
 * Instead of keeping track of how much champagne should end up in a glass,
 * keep track of the total amount of champagne that flows through a glass.
 * For example, if poured = 10 cups are poured at the top, then the total
 * flow-through of the top glass is 10; the total flow-through of each glass
 * in the second row is 4.5, and so on.
 * 
 * Algorithm
 * 
 * In general, if a glass has flow-through X, then Q = (X - 1.0) / 2.0 quantity
 * of champagne will equally flow left and right. We can simulate the entire pour
 * for 100 rows of glasses. A glass at (r, c) will have excess champagne flow
 * towards (r+1, c) and (r+1, c+1).
 *
 * Time Complexity: O(R^2), where R is the number of rows. As this is fixed, we can consider this
 *                  complexity to be O(1).
 * Space Complexity: O(R^2), or O(1) by the reasoning above.
 */
class Solution {
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[][] cups = new double[100][100]; // 0 <= query_glass <= query_row < 100
        cups[0][0] = (double)poured;
        for (int row = 0; row < query_row; row++) {
            for (int col = 0; col <= row; col++) {
                double q = (cups[row][col] - 1.0) / 2.0; // half of the excess liquid
                if (q > 0) {
                    cups[row + 1][col] += q;
                    cups[row + 1][col + 1] += q;
                }
            }
        }
        return Math.min(1.0, cups[query_row][query_glass]);
    }
}
