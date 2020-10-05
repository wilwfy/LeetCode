/**
 * My solution of Brute Force with HashSet
 *
 * Time: O(N^2)
 * Space: O(N)
 */
class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        int res = intervals.length;
        Set<Pair<Integer, Integer>> intervalSet = new HashSet<Pair<Integer, Integer>>();
        for (int i = 0; i < intervals.length; i++) {
            if (intervalSet.isEmpty()) {
                intervalSet.add(new Pair<>(intervals[i][0], intervals[i][1]));
                continue;
            }
            
            boolean covered = false;
            Iterator<Pair<Integer, Integer>> iter = intervalSet.iterator();
            while (iter.hasNext()) {
                Pair<Integer, Integer> p = iter.next();
                Integer start = p.getKey(), end = p.getValue();
                if (start <= intervals[i][0] && end >= intervals[i][1]) {
                    covered = true;
                    break; // the i-th interval is already covered by the previous interval which is currently iterated
                } else if (start >= intervals[i][0] && end <= intervals[i][1]) {
                    iter.remove(); // remove the previous interval which is currently iterated
                }
            }
            if (!covered) intervalSet.add(new Pair<>(intervals[i][0], intervals[i][1]));
        }
        return intervalSet.size();
    }
}


/**
 * Other's solution of Sort
 *
 * Intuition
 * Imagine that, after removing all covered intervals,
 * all intervals must have different bounds,
 * After sorting, their left and right bound are increasing at the same time.
 * 
 * 
 * Test Case
 * Here are some useful small test cases for debugging.
 * [[1,2],[1,3]]
 * [[1,3],[1,8],[5,8]]
 * [[1,6],[4,6],[4,8]]
 * 
 * 
 * Solution 1, sort
 * Sort the array, and note the previous left and right bound.
 * For evert interval v,
 * if v[0] > left && v[1] > right,
 * It's a new uncovered interval,
 * so we increment ++res.
 * 
 * Complexity: time O(NlogN), space O(1)
 */
class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        int res = 0, left = -1, right = -1;
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        for (int[] v: intervals) {
            if (v[0] > left && v[1] > right) {
                res++;
                left = v[0];
            }
            right = Math.max(right, v[1]);
        }
        return res;
    }
}


/**
 * Another solution of Sort
 *
 * Solution 2, sort left ascending and right decending
 * In this solution, we sort on left first.
 * When left are same, we sort right in decending order.
 * 
 * For example: [[1,5],[1,4],[1,3],[1,2]]
 * 
 * Complexity: time O(NlogN), space O(1)
 */
class Solution {
    public int removeCoveredIntervals2(int[][] intervals) {
        int res = 0, right = 0;
        Arrays.sort(intervals, (a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);
        for (int[] v : intervals) {
            if (v[1] > right) {
                ++res;
                right = v[1];
            }
        }
        return res;
    }
}
