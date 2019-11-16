/**
 * Solution of using Left and Right product lists
 *
 * Time complexity : O(N) where N represents the number of elements in the input array. We use one iteration to construct the array L,
 *                   one to construct the array R and one last to construct the answer array using L and R.
 * Space complexity : O(N) used up by the two intermediate arrays that we constructed to keep track of product of elements to the left
 *                   and right.
 */
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] leftProd = new int[nums.length];
        int[] rightProd = new int[nums.length];
        int[] outProd = new int[nums.length];
        
        leftProd[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            leftProd[i] = nums[i-1] * leftProd[i-1];
        }
        
        rightProd[nums.length-1] = 1;
        for (int i = nums.length-2; i >= 0; i--) {
            rightProd[i] = nums[i+1] * rightProd[i+1];
        }
        
        for (int i = 0; i < nums.length; i++) {
            outProd[i] = leftProd[i] * rightProd[i];
        }
        return outProd;
    }
}


/**
 * Solution of O(1) space approach
 *
 * Time complexity : O(N) where N represents the number of elements in the input array. We use one iteration to construct the array L,
 *                   one to update the array answer.
 * Space complexity : O(1) since don't use any additional array for our computations. The problem statement mentions that using the
 *                   answer array doesn't add to the space complexity.
 */
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] outProd = new int[n];
        
        outProd[0] = 1;
        for (int i = 1; i < n; i++) {
            outProd[i] = nums[i-1] * outProd[i-1];
        }
        
        int rightProd = 1;
        for (int i = n-1; i >= 0; i--) {
            outProd[i] = outProd[i] * rightProd;
            rightProd *= nums[i];
        }
        
        return outProd;
    }
}
