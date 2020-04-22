/**
 * // This is the BinaryMatrix's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface BinaryMatrix {
 *     public int get(int x, int y) {}
 *     public List<Integer> dimensions {}
 * };
 */

/*
 * Time: O(m + n).  Worst case.
 * Space: O(1)
 */
class Solution {
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dimensions = binaryMatrix.dimensions();
        int m = dimensions.get(0), n = dimensions.get(0);
        int leftMostCol = -1;
        int y = n-1, x = 0;
        while ((y >= 0) && (x < m)) {
            if (binaryMatrix.get(x, y) == 0)
                x++;
            else {
                leftMostCol = y;
                y--;
            }
        }
        return leftMostCol;
    }
}
