/**
 * My solution
 *
 * Time: O(n)
 * Space: O(1)
 */
class Solution {
    public boolean checkStraightLine(int[][] coordinates) {
        if (coordinates.length <= 2) return true;
        int deltaX = coordinates[1][0] - coordinates[0][0];
        int deltaY = coordinates[1][1] - coordinates[0][1];
        for (int i = 2; i < coordinates.length; i++) {
            int newDeltaX = coordinates[i][0] - coordinates[0][0];
            int newDeltaY = coordinates[i][1] - coordinates[0][1];
            if (deltaX * newDeltaY != deltaY * newDeltaX)
                return false;
        }
        return true;
    }
}
