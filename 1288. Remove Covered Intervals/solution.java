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
