/*
 * Other's solution with Two Pass and Stack
 *
 * Time complexity : O(n). Only two traversals of the nums array are done. Further, atmost 2n elements are pushed and popped
 *                   from the stack.
 * Space complexity : O(n). A stack of size n is used. res array of size n is used.
 */
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        if (nums.length == 0) return new int[]{};
        
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Stack<Integer> stack = new Stack<>(); // stack of array index
        for (int i = 0; i < 2*n; i++) {
            // After first round of 0 ~ n-1, get next greater element on right side
            // After second round of n ~ 2n-1, get next greater element in circular array
            int idx = i%n;
            while (!stack.isEmpty() && (nums[stack.peek()] < nums[idx]))
                res[stack.pop()] = nums[idx];
            
            // Don't need store the index in the second round
            if (i < n) stack.push(idx);
        }
        return res;
    }
}


/*
 * Other's optimized solution
 *
 * he algorithm works because at the end of round 1, stack[bottom] is always the index of the max value in nums[], and stack[top] is
 * always "0", and therefore, the "next greater element" of each element from nums[0] through nums[stack[bottom]] has been confirmed.
 * Besides, for any element to the right of the "max value" (stack[bottom]), if its "next greater element" is not found in round 1, it
 * is only necessary to compare it with nums[0] through nums[stack[bottom]].
 *
 * NOTE: since "-1" is allowed in nums[], the res[] should store the indices instead of values, otherwise, there is no way to tell if
 * res[i] == -1 means the next greater element does not exist or the next greater element is "-1". Take [3, -2, -1] for example.
 * And the algorithm can also be further optimized as follows.
 */
public class Solution {

    public int[] nextGreaterElements(int[] nums) {
        
        int[] res = new int[nums.length];
        
        Deque<Integer> stack = new ArrayDeque<>();
        
        for (int i = nums.length -1; i >= 0; --i) {
             while(!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
                 stack.pop();
             }    
             // The stack is either empty, when no "greater element" is found to the right of nums[i],
             // or contains the next greater element of nums[i] at the top.
             res[i] = stack.isEmpty() ? -1 : stack.peek();
            
             // Push i into stack, so that nums[i-1] will compare with nums[i] first, before falling back to 
             // the next greater element of nums[i]
             stack.push(i);
        }
        
        // At the end of round one, the stack[top] is always "0",
        // and stack[bottom] is always the index of the first greatest value in nums[],
        // and it's obviously that the "next greater element" of each element from nums[0] through nums[stack[bottom]]
        // has been confirmed.
        // So it is only necessary to iterate nums[nums.length - 1] through nums[stack[bottom] + 1] in round 2 
        // to find those greater elements that have not been found in round 1.
        // And for any element nums[i], if its "next greater element" is not found in round 1, it will only
        // be necessary to compare it with nums[0]...nums[stack[bottom]], and any element to the right of
        // nums[stack[bottom]] can be ignored, because nums[stack[bottom]] is the greatest one. 
        int j = stack.isEmpty() ? -1 : stack.peekLast();
        for (int i = nums.length -1; i > j; i--) {
            while(res[i] == -1 && !stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
                stack.pop();
            }
            
            if (res[i] == -1){
                res[i] = stack.isEmpty() ? -1 : stack.peek();
            }

            // No need to push i now, because in round 1, an element has been compared to all elements to its right
            
            // What about the popped elements? Do we need push them back?
            // It won't be necessary, because if nums[i] is greater then stack[top], it will pop the stack till stack[k], where
            // nums[i] < stack[k]. Assume there is an element nums[j], where j < i, and the greater element of nums[j] is not   
            // found in round 1 either. 
            // Because in round 1, nums[j] has been compared with all elements to its right, and none of them is greater than it,             
            // which means nums[i] is not greater than nums[j], so any elements popped by nums[i], will also be popped by nums[j]
            // it just might pop more elements than nums[i] does.
            // And since stack[bottom] is the greatest value, it is the bottom line for all elements.
        }
        
        return Arrays.stream(res).map(e->e!=-1 ? nums[e] : -1).toArray();
    }
}
