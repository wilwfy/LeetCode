/**
 * Other's solution with Stack
 *
 * Time: O(n).
 * Space: O(n).
 */
class Solution {
    public boolean isValidSerialization(String preorder) {
        // using a stack, scan left to right
        // case 1: we see a number, just push it to the stack
        // case 2: we see #, check if the top of stack is also #
        // if so, pop #, pop the number in a while loop, until top of stack is not #
        // if not, push it to stack
        // in the end, check if stack size is 1, and stack top is #
        if (preorder == null) return false;
        Stack<String> st = new Stack<>();
        String[] strs = preorder.split(",");
        for (int pos = 0; pos < strs.length; pos++) {
            String curr = strs[pos];
            while (curr.equals("#") && !st.isEmpty() && st.peek().equals("#")) {
                st.pop();
                if (st.isEmpty())
                    return false;
                st.pop(); // remove the leaf node
            }
            st.push(curr); // If the leaf node is removed, push the "#" to be the child of the leaf node's parent
        }
        return st.size() == 1 && st.peek().equals("#"); // there should be one single "#" remained
    }
}


/**
 * Other's solution without Stack
 *
 * Intuition and Algorithm
 *
 * In a binary tree, if we consider null as leaves, then
 *  -- all non-null node provides 2 outdegree and 1 indegree (2 children and 1 parent), except root
 *  -- all null node provides 0 outdegree and 1 indegree (0 child and 1 parent).
 * Suppose we try to build this tree. During building, we record the difference between out degree and
 * in degree diff = outdegree - indegree. When the next node comes, we then decrease diff by 1, because
 * the node provides an in degree. If the node is not null, we increase diff by 2, because it provides
 * two out degrees. If a serialization is correct, diff should never be negative and diff will be zero
 * when finished.
 *
 * Time: O(n)
 * Space: O(n)
 */
class Solution {
    public boolean isValidSerialization(String preorder) {
        String[] nodes = preorder.split(",");
        // During building the tree, we record the difference between out degree
        // and in degree: diff = outdegree - indegree.
        // If a serialization is correct, diff should never be negative and
        // diff will be zero when finished.
        int diff =  1;
        for (String node: nodes) {
            diff--; // deduct the indegree for the current node
            if (diff < 0) return false;
            if (!node.equals("#")) diff += 2; // add the outdegree for non-null node
        }
        return diff == 0;
    }
}
