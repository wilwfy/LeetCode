/**
 * Official solution based on Dynamic Programming.
 *
 * Intuition:
 *           In brute force, we iterate over the left and right parts again and again just to find the highest bar size upto
 *           that index. But, this could be stored. Voila, dynamic programming.
 * Algorithm:
 *           Find maximum height of bar from the left end upto an index i in the array left_max.
 *           Find maximum height of bar from the right end upto an index i in the array right_max.
 *           Iterate over the height array and update ans: Add min(max_left[i], max_right[i]) − height[i] to ans
 *
 * Time complexity: O(n).
 *                  We store the maximum heights upto a point using 2 iterations of O(n) each.
 *                  We finally update ans using the stored values in O(n).
 * Space complexity: O(n) extra space.
 *                   Additional O(n) space for left_max and right_max arrays.
 */
class Solution {
    public int trap(int[] height) {
        if ((height == null) || (height.length == 0)) return 0;
        int res = 0;
        int len = height.length;
        
        int[] maxLeft = new int[len];
        maxLeft[0] = height[0];
        for (int i = 1; i < len; i++) {
            maxLeft[i] = Math.max(maxLeft[i-1], height[i]);
        }
        
        int[] maxRight = new int[len];
        maxRight[len - 1] = height[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            maxRight[i] = Math.max(maxRight[i+1], height[i]);
        }
        
        // The bars at both ends can not retain water
        //for (int i = 0; i < len; i++) {
        for (int i = 1; i < len - 1; i++) {
            res += Math.max(Math.min(maxLeft[i], maxRight[i]) - height[i], 0);
        }
        return res;
    }
}


/**
 * Official solution by using Stack
 *
 * Intuition:
 *           Instead of storing the largest bar upto an index as in Approach 2, we can use stack to keep track of the
 *           bars that are bounded by longer bars and hence, may store water. Using the stack, we can do the calculations
 *           in only one iteration.
 *           We keep a stack and iterate over the array. We add the index of the bar to the stack if bar is smaller than
 *           or equal to the bar at top of stack, which means that the current bar is bounded by the previous bar in the
 *           stack. If we found a bar longer than that at the top, we are sure that the bar at the top of the stack is bounded
 *           by the current bar and a previous bar in the stack, hence, we can pop it and add resulting trapped water to ans.
 * Algorithm:
 *           Use stack to store the indices of the bars.
 *           Iterate the array:
 *               While stack is not empty and height[current]>height[st.top()]
 *                   It means that the stack element can be popped. Pop the top element as top.
 *                   Find the distance between the current element and the element at top of stack, which is to be filled.
 *                   distance=current−st.top()−1
 *                   Find the bounded height bounded_height=min(height[current],height[st.top()])−height[top]
 *                   Add resulting trapped water to answer ans+=distance×bounded_height
 *               Push current index to top of the stack
 *               Move current to the next position
 *
 * Time complexity: O(n).
 *                  Single iteration of O(n) in which each bar can be touched at most twice(due to insertion and deletion
 *                  from stack) and insertion and deletion from stack takes O(1) time.
 * Space complexity: O(n). Stack can take upto O(n) space in case of stairs-like or flat structure.
 */
class Solution {
    public int trap(int[] height) {
        if ((height == null) || (height.length == 0)) return 0;
        int res = 0;
        Stack<Integer> sta = new Stack<>();
        int current = 0, len = height.length;
        
        while (current < len) {
            while (!sta.isEmpty() && (height[current] > height[sta.peek()])) {
                int top = sta.pop();
                if (sta.isEmpty())
                    break;
                int distance = current - sta.peek() - 1;
                int bounded_height = Math.min(height[current], height[sta.peek()]) - height[top];
                res += distance * bounded_height;
            }
            sta.push(current++);
        }
        return res;
    }
}
