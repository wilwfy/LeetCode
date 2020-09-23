/**
 * My solution with HashMap
 *
 * Time: O(n)
 * Space: O(n)
 */
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        Map<Integer, Integer> cntMap = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        for (int num: nums)
            cntMap.put(num, cntMap.getOrDefault(num, 0) + 1);
        for (Integer key: cntMap.keySet()) {
            if (cntMap.get(key) > nums.length / 3) res.add(key);
            if (res.size() == 2) break;
        }
        return res;
    }
}
