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
 *           Instead of storing the largest bar upto an index as in Approach of Dynamic Programming, we can use stack to keep
 *           track of the bars that are bounded by longer bars and hence, may store water. Using the stack, we can do the
 *           calculations in only one iteration.
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


/**
 * Official solution by using 2 Pointers.
 *
 * Intuition:
 *           As in Approach of Dynamic Programming, instead of computing the left and right parts seperately, we may think
 *           of some way to do it in one iteration. From the figure in dynamic programming approach, notice that as long as
 *           right_max[i]>left_max[i] (from element 0 to 6), the water trapped depends upon the left_max, and similar is the
 *           case when left_max[i]>right_max[i] (from element 8 to 11). So, we can say that if there is a larger bar at one
 *           end (say right), we are assured that the water trapped would be dependant on height of bar in current direction
 *           (from left to right). As soon as we find the bar at other end (right) is smaller, we start iterating in opposite
 *           direction (from right to left). We must maintain left_max and right_max during the iteration, but now we can do
 *           it in one iteration using 2 pointers, switching between the two.
 * Algorithm:
 *           Initialize left pointer to 0 and right pointer to size-1
 *           While left<right, do:
 *               If height[left] is smaller than height[right]
 *                   If height[left]≥left_max, update left_max
 *                   Else add left_max−height[left] to ans
 *                   Add 1 to left.
 *               Else
 *                   If height[right]≥right_max, update right_max
 *                   Else add right_max−height[right] to ans
 *                   Subtract 1 from right.
 *
 * Time complexity: O(n). Single iteration of O(n).
 * Space complexity: O(1) extra space. Only constant space required for left, right, left_max and right_max..
 */
class Solution {
    public int trap(int[] height) {
        if ((height == null) || (height.length == 0)) return 0;
        int res = 0;
        int l = 0, r = height.length - 1;
        int leftMax = 0, rightMax = 0;
        while (l < r) {
            if (height[l] < height[r]) {
                if (height[l] >= leftMax)
                    leftMax = height[l];
                else
                    res += leftMax - height[l];
                l++;
            } else {
                if (height[r] >= rightMax)
                    rightMax = height[r];
                else
                    res += rightMax - height[r];
                r--;
            }
        }
        return res;
    }
}
