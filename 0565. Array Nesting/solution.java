/*
 * My solution based on visited set.
 *
 * Time Complexity: O(n).
 * Space Complexity: O(n).
 */
class Solution {
    public int arrayNesting(int[] nums) {
        if (nums.length <= 1) return nums.length;
        
        int maxCount = 0;
        Set<Integer> visitedSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (visitedSet.contains(nums[i]))
                continue;
            else
                visitedSet.add(nums[i]);
            
            Set<Integer> idxSet = new HashSet<>();
            int cnt = 0;
            int idx = i;
            
            while (!idxSet.contains(idx)) {
                cnt++;
                idxSet.add(idx);
                idx = nums[idx];
                visitedSet.add(idx);
            }
            if (cnt > maxCount) maxCount = cnt;
        }
        return maxCount;
    }
}
