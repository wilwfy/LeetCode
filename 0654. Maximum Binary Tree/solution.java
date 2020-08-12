/**
 * Official Recursive solution
 *
 * Time complexity : O(n^2). The function construct is called nn times. At each level of the recursive tree, we
 *                   traverse over all the nn elements to find the maximum element. In the average case, there
 * 				           will be a logn levels leading to a complexity of O(nlogn). In the worst case, the depth of
 * 				           the recursive tree can grow upto nn, which happens in the case of a sorted numsnums array,
 * 				           giving a complexity of O(n^2).
 * 
 * Space complexity : O(n). The size of the setset can grow upto nn in the worst case. In the average case, the
 *                    size will be logn for n elements in numsnums, giving an average case complexity of O(logn)
 */
class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return construct(nums, 0, nums.length);
    }
    
    private TreeNode construct(int[] nums, int left, int right) {
        if (left == right) return null;
        int maxIdx = getMaxIndex(nums, left, right);
        TreeNode root = new TreeNode(nums[maxIdx]);
        root.left = construct(nums, left, maxIdx);
        root.right = construct(nums, maxIdx + 1, right);
        return root;
    }
    
    public int getMaxIndex(int[] nums, int left, int right) {
        int maxIdx = left;
        for (int i = left; i < right; i++) {
            if (nums[i] > nums[maxIdx])
                maxIdx = i;
        }
        return maxIdx;
    }
}


/**
 * Other's solution of One Pass with Stack
 *
 * Intuition
 * 
 * If we have built the max binary tree for nums[0] ~ nums[i - 1], how can we insert nums[i] to the binary tree?
 * Say the max binary tree for nums[0] ~ nums[i - 1] looks like:
 * 
 *       A
 *      / \
 *   ...   B
 *        / \
 *     ...   C
 *          / \
 *       ...   ...
 * 	  
 * Say the node for nums[i] is D.
 * If D.val > A.val, then because A.val is at the left of D.val, we can just move the tree rooted at A to the left
 * child of D.
 * 
 *         D
 *        /
 *       A
 *      / \
 *   ...   B
 *        / \
 *     ...   C
 *          / \
 *       ...   ...
 * 	  
 * If D.val < A.val, then because D.val is at the right of A.val, D must be put into the right subtree of A.
 * Similarly, if D.val < B.val, then D must be put into the right subtree of B.
 * Say B.val > D.val > C.val, then D should be the right child of B. (because D.val is at the right of B.val,
 * and D.val is the biggest among the numbers at the right of B.val.)
 * Because C.val < D.val, and C.val is at the left of D.val, C should become left child of D.
 * 
 *       A
 *      / \
 *   ...   B
 *        / \
 *      ...  D
 *          /
 *         C
 *        / \
 *     ...   ...
 * 	
 * So to update the max binary tree for nums[0] ~ nums[i - 1], we need to know the nodes on the right path of the
 * tree. (A, B, C, ...)
 * 
 * How to maintain the path?
 * Let's look at the property of the nodes.
 * A is the biggest among nums[0] ~ nums[i - 1].
 * B is the biggest for the numbers between A and nums[i] (exclusive).
 * C is the biggest for the numbers between B and nums[i] (exclusive).
 * Let's use a stack, and assume that the content of the stack contains the "right path" of nodes before the node
 * for the current number.
 * For the node of the new number, we should remove the nodes in the stack which are smaller than the current number.
 * So we pop the stack until the top element of the stack is greater than the current number.
 * Then, add the node for the current number to the stack.
 * Finally, return the bottom node of stack.
 *
 * Time: O(n).
 * Space: O(n) in worst case that the nums is a sorted descending array.
 */
class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        // Use LinkedList instead of Stack because we need get the bottom of the stack at last step
        Deque<TreeNode> stack = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            TreeNode cur = new TreeNode(nums[i]);
            while (!stack.isEmpty() && stack.peek().val < cur.val) {
                // the cur.left will eventually be the node which has biggest value that is smaller than cur.val
                cur.left = stack.pop();
            }
            if (!stack.isEmpty()) {
                // Since current node comes after (on the right side of) the node whose value
                // is bigger than cur.val, so current node should be the right child of that node
                stack.peek().right = cur;
            }
            stack.push(cur);
        }
        return stack.isEmpty() ? null : stack.removeLast(); // return the bottom element in the stack
    }
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
