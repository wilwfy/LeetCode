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

/**
 * Other's solution of Sorting
 *
 * Sort reversely by the units then apply greedy algorithm.
 *
 * Time: O(nlogn)
 * Space: O(logn) - quicksort
 */
class Solution {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        int boxes = 0;
        for (int[] box : boxTypes) {
            if (truckSize >= box[0]) {
                boxes += box[0] * box[1];
                truckSize -= box[0];
            }else {
                boxes += truckSize * box[1];
                return boxes;
            }
        }
        return boxes;
    }
}
