/**
 * Other's solution of Backtracking
 *
 * Time: ?
 * Space: ?
 */
class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        helper(new ArrayList<Integer>(), 0, nums, res);
        return res;
    }
    
    private void helper(ArrayList<Integer> list, int start, int[] nums, List<List<Integer>> res) {
        if (list.size() > 1) res.add(new ArrayList<Integer>(list));
        Set<Integer> usedSet = new HashSet<>();
        for (int i = start; i < nums.length; i++) {
            if (usedSet.contains(nums[i])) continue;
            if (list.size() == 0 || nums[i] >= list.get(list.size() - 1)) {
                usedSet.add(nums[i]);
                list.add(nums[i]);
                helper(list, i + 1, nums, res);
                list.remove(list.size() - 1);
            }
        }
    }
}
