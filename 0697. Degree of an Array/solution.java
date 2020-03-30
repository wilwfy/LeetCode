/*
 * My solution of Counting Sort and HashMap
 */
class Solution {
    public int findShortestSubArray(int[] nums) {
        if ((nums == null) || (nums.length == 0)) return 0;
        if (nums.length == 1) return 1;
        
        int[] cnt = new int[50000];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            cnt[nums[i]]++;
            List<Integer> idxList = map.getOrDefault(nums[i], new ArrayList<Integer>());
            idxList.add(i);
            map.put(nums[i], idxList);
        }
        
        List<Integer> max_num = new ArrayList<>(List.of(0));
        int max_cnt = cnt[0];
        for (int i = 0; i < cnt.length; i++) {
            if (cnt[i] == 0) continue;
            if (cnt[i] > max_cnt) {
                max_cnt = cnt[i];
                max_num = new ArrayList<>(List.of(i));
            } else if (cnt[i] == max_cnt)
                max_num.add(i);
        }
        
        int smallestLen = Integer.MAX_VALUE;
        for (Integer num: max_num) {
            //System.out.println(num);
            List<Integer> indexList = map.get(num);
            smallestLen = Math.min(smallestLen, indexList.get(indexList.size()-1) - indexList.get(0) + 1);
        }
        return smallestLen;
    }
}


/*
 * Official solution of HashMap
 *
 * For each element x that occurs the maximum number of times, right[x] - left[x] + 1 will be our candidate answer, and
 * we'll take the minimum of those candidates.
 *
 * Time Complexity: O(N), where N is the length of nums. Every loop is through O(N) items with (1) work inside the for-block.
 * Space Complexity: O(N), the space used by left, right, and count.
 */
class Solution {
    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> left = new HashMap(),
            right = new HashMap(), count = new HashMap();

        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            if (left.get(x) == null) left.put(x, i);
            right.put(x, i);
            count.put(x, count.getOrDefault(x, 0) + 1);
        }

        int ans = nums.length;
        int degree = Collections.max(count.values());
        for (int x: count.keySet()) {
            if (count.get(x) == degree) {
                ans = Math.min(ans, right.get(x) - left.get(x) + 1);
            }
        }
        return ans;
    }
}
