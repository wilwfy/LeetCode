/**
 * My solution with Sort
 *
 * Time: O(nlogn)
 * Space: O(n)
 */
class Solution {
    public int findMinDifference(List<String> timePoints) {
        int n = timePoints.size();
        int[] minutes = new int[n];
        for (int i = 0; i < n; i++) {
            String[] time = timePoints.get(i).split(":");
            minutes[i] = Integer.valueOf(time[0]) * 60 + Integer.valueOf(time[1]);
            //System.out.println(minutes[i]);
        }
        Arrays.sort(minutes);
        int minDiff = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) { // The number of time points in the given list is at least 2
            minDiff = Math.min(minDiff, minutes[i] - minutes[i - 1]);
        }
        // check the difference between the earliest and latest timepoints by crossing the 0 click
        minDiff = Math.min(minDiff, 24 * 60 + minutes[0] - minutes[n - 1]);
        return minDiff;
    }
}


/**
 * Other's solution with Sort
 *
 * Time: O(nlogn)
 * Space: O(n)
 */
public class Solution {
    public int findMinDifference(List<String> timePoints) {
        int mm = Integer.MAX_VALUE;
        List<Integer> time = new ArrayList<>();
        
        for(int i = 0; i < timePoints.size(); i++){
            Integer h = Integer.valueOf(timePoints.get(i).substring(0, 2));
            time.add(60 * h + Integer.valueOf(timePoints.get(i).substring(3, 5)));
        }
        
        Collections.sort(time, (Integer a, Integer b) -> a - b);
        
        for(int i = 1; i < time.size(); i++){
            System.out.println(time.get(i));
            mm = Math.min(mm, time.get(i) - time.get(i-1));
        }
        
        int corner = time.get(0) + (1440 - time.get(time.size()-1));
        return Math.min(mm, corner);
    }
}


/**
 * Other's solution based on Bucket
 *
 * the problem is another version of finding the shortest distances between two elements in a circular array.
 * There is only 24 * 60 = 1440 possible time points. Just create a boolean array, each element stands for if we see that time point or not.
 *
 * Time: O(nlogn)
 * Space: O(n)
 */
class Solution {
    public int findMinDifference(List<String> timePoints) {
        boolean[] mark = new boolean[24 * 60];
        for (String time : timePoints) {
            String[] t = time.split(":");
            int h = Integer.parseInt(t[0]);
            int m = Integer.parseInt(t[1]);
            if (mark[h * 60 + m]) return 0; // meet a same time like one previously marked, so just return 0
            mark[h * 60 + m] = true;
        }
        
        int prev = 0, min = Integer.MAX_VALUE;
        int first = Integer.MAX_VALUE, last = Integer.MIN_VALUE;
        for (int i = 0; i < 24 * 60; i++) {
            if (mark[i]) {
                if (first != Integer.MAX_VALUE) {
                    min = Math.min(min, i - prev);
                }
                first = Math.min(first, i); // first - the earliest time
                last = Math.max(last, i); // last - the latest time
                prev = i;
            }
        }
        
        min = Math.min(min, (24 * 60 + first - last));
        
        return min;
    }
}
