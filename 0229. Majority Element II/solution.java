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


/**
 * Other's solution of Boyer-Moore Majority Vote algorithm
 *
 * There is a great article (http://goo.gl/64Nams) that helps understand this fantastic algorithm
 *
 * The essential concepts is you keep a counter for the majority number X. If you find a number Y that is not X, the
 * current counter should deduce 1. The reason is that if there is 5 X and 4 Y, there would be one (5-4) more X than Y.
 * This could be explained as "4 X being paired out by 4 Y".
 *
 * And since the requirement is finding the majority for more than ceiling of [n/3], the answer would be less than or
 * equal to two numbers.
 * So we can modify the algorithm to maintain two counters for two majorities.
 *
 * Time: O(n)
 * Space: O(1) for calculation.
 */
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        Integer major1 = null, major2 = null, cnt1 = 0, cnt2 = 0;
        for (Integer num : nums) {
            if (num.equals(major1)) {
                cnt1++;
            } else if (num.equals(major2)) {
                cnt2++;
            } else if (cnt1 == 0) {
                major1 = num;
                cnt1 = 1;
            } else if (cnt2 == 0) {
                major2 = num;
                cnt2 = 1;
            } else {
                cnt1--;
                cnt2--;
            }
        }
        
        cnt1 = cnt2 = 0;
        for (Integer num : nums) {
            if (num.equals(major1)) cnt1++;
            else if (num.equals(major2)) cnt2++;
        }
        
        List<Integer> result = new ArrayList<>();
        if (cnt1 > nums.length / 3) result.add(major1);
        if (cnt2 > nums.length / 3) result.add(major2);
        return result;
    }
