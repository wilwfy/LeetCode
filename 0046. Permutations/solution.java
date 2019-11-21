/**
 * Other's solution of Backtracking
 *
 * Time Complexity: O(n * n!). Because the O(n!) is for the permutations, and O(n) is for the contains() function.
 * Space Complexity: O(n!).
 */
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) return res;
        backtrack(nums, res, new ArrayList<Integer>());
        return res;
    }
    
    public void backtrack(int[] nums, List<List<Integer>> list, List<Integer> tmpList) {
        if (tmpList.size() == nums.length) {
            list.add(new ArrayList<Integer>(tmpList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (tmpList.contains(nums[i])) continue;
                tmpList.add(nums[i]);
                backtrack(nums, list, tmpList);
                
                // Remove previous setting for next new setting
                tmpList.remove(tmpList.size()-1);
            }
        }
    }
}


/**
 * Other's solution of Backtracking with boolean array visited
 *
 * Time Complexity: O(n!). Because the O(n!) is for the permutations, and O(1) is for the boolean array visited[].
 * Space Complexity: O(n!).
 */
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) return res;
        backtrack(nums, new boolean[nums.length], res, new ArrayList<Integer>());
        return res;
    }
    
    public void backtrack(int[] nums, boolean[] visited, List<List<Integer>> list, List<Integer> tmpList) {
        if (tmpList.size() == nums.length) {
            list.add(new ArrayList<Integer>(tmpList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                //if (tmpList.contains(nums[i])) continue;
                // the contains() function takes O(n) time complexity,
                // so use boolean array to reduce it to O(1)
                if (visited[i] == true) continue;
                
                visited[i] = true;
                tmpList.add(nums[i]);
                backtrack(nums, visited, list, tmpList);
                
                // Remove previous setting for next new setting
                tmpList.remove(tmpList.size()-1);
                visited[i] = false;
            }
        }
    }
}
