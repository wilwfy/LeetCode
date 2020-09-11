/**
 * Official solution of Recursion
 *
 * Intuition
 * 
 * A preorder traversal is:
 * 
 * (root node) (preorder of left branch) (preorder of right branch)
 * While a postorder traversal is:
 * 
 * (postorder of left branch) (postorder of right branch) (root node)
 * For example, if the final binary tree is [1, 2, 3, 4, 5, 6, 7] (serialized),
 * then the preorder traversal is [1] + [2, 4, 5] + [3, 6, 7], while the postorder
 * traversal is [4, 5, 2] + [6, 7, 3] + [1].
 * 
 * If we knew how many nodes the left branch had, we could partition these arrays
 * as such, and use recursion to generate each branch of the tree.
 * 
 * Algorithm
 * 
 * Let's say the left branch has LL nodes. We know the head node of that left branch
 * is pre[1], but it also occurs last in the postorder representation of the left branch.
 * So pre[1] = post[L-1] (because of uniqueness of the node values.) Hence, L = post.indexOf(pre[1]) + 1.
 * 
 * Now in our recursion step, the left branch is represnted by pre[1 : L+1] and post[0 : L],
 * while the right branch is represented by pre[L+1 : N] and post[L : N-1].
 *
 * Time Complexity: O(N^2), where N is the number of nodes.
 * Space Complexity: O(N^2).
 */
class Solution {
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        int N = pre.length;
        if (N == 0) return null;
        TreeNode root = new TreeNode(pre[0]);
        if (N == 1) return root;
        
        int L = 0; // L -- node numbers in left branch
        for (int i = 0; i < N; i++)
            if (post[i] == pre[1])
                L = i + 1;
        
        root.left = constructFromPrePost(Arrays.copyOfRange(pre, 1, L+1),
                                         Arrays.copyOfRange(post, 0, L));
        root.right = constructFromPrePost(Arrays.copyOfRange(pre, L+1, N),
                                         Arrays.copyOfRange(post, L, N-1));
        return root;
    }
}


/**
 * Official solution of Recursion (Space Saving Variant)
 *
 * Explanation
 * 
 * We present a variation of Approach 1 that uses indexes to refer to the subarrays of pre and post,
 * instead of passing copies of those subarrays. Here, (i0, i1, N) refers to pre[i0:i0+N], post[i1:i1+N].
 *
 * Time Complexity: O(N^2), where N is the number of nodes.
 * Space Complexity: O(N), the space used by the answer.
 */
class Solution {
    int[] pre, post;
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        this.pre = pre;
        this.post = post;
        return make(0, 0, pre.length);
    }

    public TreeNode make(int i0, int i1, int N) {
        if (N == 0) return null;
        TreeNode root = new TreeNode(pre[i0]);
        if (N == 1) return root;

        int L = 1;
        for (; L < N; ++L)
            if (post[i1 + L-1] == pre[i0 + 1])
                break;

        root.left = make(i0+1, i1, L);
        root.right = make(i0+L+1, i1+L, N-1-L);
        return root;
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
