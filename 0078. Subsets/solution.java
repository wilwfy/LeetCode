/**
 * Other's solution of Backtracking
 */
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) return res;
        //Arrays.sort(nums);
        backtrack(0, nums, res, new LinkedList<Integer>());
        return res;
    }
    
    public void backtrack(int start, int[] nums, List<List<Integer>> list, List<Integer> tmpList) {
        list.add(new LinkedList<Integer>(tmpList));

        for (int i = start; i < nums.length; i++) {
            tmpList.add(nums[i]);
            backtrack(i+1, nums, list, tmpList);
            tmpList.remove(tmpList.size()-1);
        }
    }
}


/**
 * Other's faster solution without Backtracking
 *
 * Time Complexity: O(2^n).
 *                  or each number we go through, we double the size of result, thus the next for loop will take twice as long.
 *                  But not every inner loop has a result of full size of 2^n. Actually the whole time complexity is:
 *                  1+2^1+2^2+……+2^n = 2^(n+1)-1. So it is O(2^(n+1)-1) = O(2^n).
 * Space Complexity:
 */
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
    
    // While iterating through all numbers, for each new number, we can either pick it or not pick it
    // 1, if pick, just add current number to every existing subset.
    // 2, if not pick, just leave all existing subsets as they are.
    // We just combine both into our result.
    // 
    // For example, {1,2,3} intially we have an emtpy set as result [ [ ] ]
    // Considering 1, if not use it, still [ ], if use 1, add it to [ ], so we have [1] now
    // Combine them, now we have [ [ ], [1] ] as all possible subset
    // 
    // Next considering 2, if not use it, we still have [ [ ], [1] ], if use 2, just add 2 to each previous subset, we have [2], [1,2]
    // Combine them, now we have [ [ ], [1], [2], [1,2] ]
    // 
    // Next considering 3, if not use it, we still have [ [ ], [1], [2], [1,2] ], if use 3, just add 3 to each previous subset, we have [ [3], [1,3], [2,3], [1,2,3] ]
    // Combine them, now we have [ [ ], [1], [2], [1,2], [3], [1,3], [2,3], [1,2,3] ]

        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) return res;
        
        res.add(new ArrayList<Integer>());
        
        for (int num: nums) {
            int n = res.size();
            for (int i = 0; i < n; i++) {
                List<Integer> subset = new ArrayList<>(res.get(i));
                subset.add(num);
                res.add(subset);
            }
        }
        
        return res;
    }
}
