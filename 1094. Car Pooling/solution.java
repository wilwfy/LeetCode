/**
 * My solution of Boundary Check (Time Stamp)
 *
 * Intuition
 * Same as 253. Meeting Rooms II
 *         731. My Calendar II
 * Track the change of capacity in time order.
 * 
 * Explanation
 * Save all time points and the change on current capacity
 * Sort all the changes on the key of time points.
 * Track the current capacity and return false if negative
 *
 * Complexity Analysis
 * Assume N is the length of trips.
 * Time complexity: O(Nlog(N)) since we need to iterate over trips and sort our timestamp. Iterating
 *                  costs O(N), and sorting costs O(Nlog(N)), and adding together we have O(N) + O(Nlog(N)) = O(Nlog(N)).
 * Space complexity: O(N) since in the worst case we need O(N) to store timestamp.
 */
class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        if (trips == null || trips.length == 0) return true;
        TreeMap<Integer, Integer> loadDeltaMap = new TreeMap<>();
        for (int[] trip: trips) {
            loadDeltaMap.put(trip[1], loadDeltaMap.getOrDefault(trip[1], 0) + trip[0]);
            loadDeltaMap.put(trip[2], loadDeltaMap.getOrDefault(trip[2], 0) - trip[0]);
        }
        int curLoad = 0;
        for (int load: loadDeltaMap.values()) {
            curLoad += load;
            if (curLoad > capacity) return false;
        }
        return true;
    }
}


/**
 * Official solution of Bucket Sort
 * 
 * Intuition
 * 
 * Note that in the problem there is a interesting constraint:
 * 0 <= trips[i][1] < trips[i][2] <= 1000
 * What pops into the mind is Bucket Sort, which is a sorting algorithm in O(N) time but requires
 * some prior knowledge for the range of the data.
 * 
 * We can use it instead of the normal sorting in this method.
 * 
 * What we do is initial 1001 buckets, and put the number of passengers changed in corresponding
 * buckets, and collect the buckets one by one.
 * 
 * Algorithm
 * 
 * We will initial 1001 buckets, iterate trip, and save the number of passengers changed at i mile
 * in the i-th bucket.
 *
 *
 * Complexity Analysis
 * Assume N is the length of trip.
 * Time complexity: O(max(N, 1001)) since we need to iterate over trips and then iterate over our 1001 buckets.
 * Space complexity : O(1001) = O(1) since we have 1001 buckets.
 */
class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        int[] timestamp = new int[1001];
        for (int[] trip : trips) {
            timestamp[trip[1]] += trip[0];
            timestamp[trip[2]] -= trip[0];
        }
        int ued_capacity = 0;
        for (int number : timestamp) {
            ued_capacity += number;
            if (ued_capacity > capacity) {
                return false;
            }
        }
        return true;
    }
}
