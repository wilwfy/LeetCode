/**
 * Other's solution of Backtracking with boolean array
 *
 * Time Complexity: O(n!). Because the O(n!) is for the permutations, and O(1) is for the boolean array used[].
 * Space Complexity: O(n!).
 */
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) return res;
        Arrays.sort(nums);
        backtrack(nums, new boolean[nums.length], res, new ArrayList<Integer>());
        return res;
    }
    
    public void backtrack(int[] nums, boolean[] used, List<List<Integer>> list, List<Integer> tmpList) {
        if (tmpList.size() == nums.length) {
            list.add(new ArrayList<Integer>(tmpList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (used[i] ||
                    //重复的数组只能出现在父子节点递归调用栈，不能在兄弟节点递归调用栈中。
                    // if i > 0 && nums[i] == nums[i -1] represent we encounter repeat num, OK, attention please: we now visiting nums[i] 
                    // when nums[i - 1] is the parent node of the current node nums[i] in the recursive path, then used[i - 1] should be "true". 
                    // when nums[i - 1] not in the recursive path, and i - 1 < i, it only can be the "brother node" of current node nums[i - 1],
                    // that is used[i - 1] == false. when used[i - 1] == false, that means nums[i-1] and nums[i] are the brother with same
                    // parent. if a parent has two children same, this will make the permutation result repeat. eg. nums = [1, 2, 2]
                    (i > 0 && nums[i] == nums[i-1] && !used[i-1]))
                    continue;
                
                used[i] = true;
                tmpList.add(nums[i]);
                backtrack(nums, used, list, tmpList);
                
                // After the backtracking, undo the last setting for the next setting
                tmpList.remove(tmpList.size()-1);
                used[i] = false;
            }
        }
    }
}
