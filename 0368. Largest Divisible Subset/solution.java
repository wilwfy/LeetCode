/**
 * Other's solution of DP
 *
 * Intuition:
 * Use DP to track max Set and pre index.
 * using an array of Arraylist to store current valid subsets for every element in the original list.
 *
 * Classic DP solution similar to LIS, O(n^2)
 */
class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        if (nums.length == 0) return new ArrayList<Integer>();
        
        Arrays.sort(nums);
        
        List<Integer>[] dp = (ArrayList<Integer>[]) new ArrayList[nums.length];
        int maxIndex = 0, maxSize = -1;
        int finalIndex = 0, finalSize = -1;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = new ArrayList<Integer>();
            for (int j = i - 1; j >= 0; j--) {
		// Because the nums[] is sorted, so
		// if nums[i] can be divided by nums[j], it can also be divided by every element in dp[j]
		// Find a previous nums[j] that has most element in
                if (nums[i] % nums[j] == 0) {
                    if (dp[j].size() > maxSize) {
                        maxSize = dp[j].size();
                        maxIndex = j;
                    }
                }
            }

            // if maxSize not equal to -1, which means divisor for nums[i] is found, 
	    // add the one with most element in.
            if (maxSize != -1) dp[i].addAll(dp[maxIndex]);
            
            // add nums[i] itself to dp
            dp[i].add(nums[i]);
		
            if (dp[i].size() > finalSize) {
                finalSize = dp[i].size();
                finalIndex = i;
            }
		
            maxIndex = 0;
            maxSize = -1;
        }
        return dp[finalIndex];
    }
}


/**
 * Other's solution of DP
 */
class Solution {
    // if we sort the array, every element in a divisibleSubset can be divisible by the element just before it.
    // for any element k, its largestDivisibleSubset that ends with k can be formed in the following way: 
    // use element k after any one of the previous elements that is divisble
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int[] l = new int[nums.length]; // the length of largestDivisibleSubset that ends with element i
        int[] prev = new int[nums.length]; // the previous index of element i in the largestDivisibleSubset ends with element i
        
        Arrays.sort(nums);
        
        int max = 0;
        int index = -1;
        for (int i = 0; i < nums.length; i++){
            l[i] = 1;
            prev[i] = -1;
            for (int j = i - 1; j >= 0; j--){
                if (nums[i] % nums[j] == 0 && l[j] + 1 > l[i]){
                    l[i] = l[j] + 1;
                    prev[i] = j;
                }
            }
            if (l[i] > max){
                max = l[i];
                index = i;
            }
        }
        
        List<Integer> res = new ArrayList<Integer>();
        while (index != -1){
            res.add(nums[index]);
            index = prev[index];
        }
        
        return res;
    }
}
