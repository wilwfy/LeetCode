/**
 * Other's solution by using Priority Queue
 *
 * Basic idea: Use min_heap to keep track on next minimum pair sum, and we only need to maintain K possible candidates
 *             in the data structure.
 * Some observations: For every numbers in nums1, its best partner(yields min sum) always strats from nums2[0] since
 *                    arrays are all sorted; And for a specific number in nums1, its next candidate sould be [this 
 *                    specific number] + nums2[current_associated_index + 1], unless out of boundary.
 *
 * Time Complexity: O(kLogk) since que.size <= k and we do at most k loop.
 * Space Complexity: k
 */
class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        
        // no pairs to form, just return an empty res list
        if ((nums1.length == 0) || (nums2.length == 0) || (k == 0)) return res;
        
        // min queue, sorted by pair sum
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a,b) -> (a[0] + a[1]) - (b[0] + b[1])
        );
        
        // offer initial pairs {num1, num2, index_of_num2}
        int len1 = nums1.length, len2 = nums2.length;
        for (int i = 0; i < Math.min(len1, k); i++)
            pq.offer(new int[]{nums1[i], nums2[0], 0});

        // get 1st k elem into result, each time, offer potential better pairs into queue
        // if there are not enough pair, just return all pairs
        for (int i = 0; i < Math.min(len1 * len2, k); i++) {
            // get the best pair and put into res
            int[] cur = pq.poll();
            res.add(Arrays.asList(cur[0], cur[1]));
            
            // next better pair could with be A: {after(num1), num2} or B: {num1. after(num2)}
            // for A, we've already added top possible k into queue, so A is either in the queue already, or not qualified
            // for B, it might be a better choice, so we offer it into queue
            if (cur[2] < len2 - 1) { // still at least one elem after num2 in array nums2
                int idx = cur[2] + 1;
                pq.offer(new int[]{cur[0], nums2[idx], idx});
            }
        }
        return res;
    }
}
