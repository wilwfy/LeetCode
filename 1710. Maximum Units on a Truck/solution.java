/**
 * My solution of Sorting
 *
 * Time: O(nlogn + Max(n*m, truckSize))
 * Space: O(1)
 */
class Solution {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> (a[1] - b[1]));
        int max_units = 0;
        for (int i = boxTypes.length - 1; i >= 0; i--) {
            while (truckSize > 0 && boxTypes[i][0] > 0) {
                max_units += boxTypes[i][1];
                truckSize--;
                boxTypes[i][0]--;
            }
            if (truckSize == 0) break;
        }
        return max_units;
    }
}
