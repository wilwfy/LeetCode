/*
 * Official solution of Greedy with Stack
 */
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int n = pushed.length, i = 0;
        for (int pu: pushed) {
            stack.push(pu);
            // greedily pop values from the stack if they are the next values to pop.
            while ( !stack.empty() && (i < n) && (popped[i] == stack.peek()) ) {
                stack.pop();
                i++;
            }
        }
        // At the end, we check if we have popped all the values successfully.
        return i == n;
    }
}


/*
 * Other's solution of Greedy with Stack
 *
 * Simulate stack operations:
 * Loop through the pushed array,
 *   1. Keep pushing pushed elements into stack if the top element on the stack is different from the current one of popped;
 *   2. Keep poping out of the top element from stack if it is same as the current one of popped;
 *   3. Check if the stack is empty after loop.
 *
 * Time & space: O(n).
 */
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> stack = new ArrayDeque<>();
        int index = 0;
        for(int p : pushed){
            stack.push(p);
            while (!stack.isEmpty() && stack.peek() == popped[index]){
                stack.pop();
                index++;
            }
        }
        return stack.isEmpty();
    }
}



/*
 * Other's solution of Greedy with Stack
 *
 * Based on the solution above, use pushed as the stack.
 * This solution will take O(1) extra space, though it also changed the input.
 *
 * Time: O(n).
 * Space: O(1).
 */
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int i = 0, j = 0;
        for (int x : pushed) {
            pushed[i++] = x;
            while (i > 0 && pushed[i - 1] == popped[j]) {
                --i; ++j;
            }
        }
        return i == 0;
    }
}
