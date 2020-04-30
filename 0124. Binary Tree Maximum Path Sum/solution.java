/*
 * Other's solution of DFS
 *
 * Intuition:
 * A path from start to end, goes up on the tree for 0 or more steps, then goes down for 0 or more steps. Once it goes down, it can't go
 * up. Each path has a highest node, which is also the lowest common ancestor of all other nodes on the path.
 * A recursive method maxPathDown(TreeNode node) (1) computes the maximum path sum with highest node is the input node, update maximum if
 * necessary (2) returns the maximum sum of the path that can be extended to input node's parent.
 */
class Solution {
    int maxValue;
    
    public int maxPathSum(TreeNode root) {
        maxValue = Integer.MIN_VALUE;
        maxPathDown(root);
        return maxValue;
    }
    
    public int maxPathDown(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, maxPathDown(node.left));
        int right = Math.max(0, maxPathDown(node.right));
        maxValue = Math.max(maxValue, left + right + node.val);
        return node.val + Math.max(left, right);
    }
}


/*
 * Other's solution of DFS without global variable
 */
 class Solution {
    public int maxPathSum(TreeNode root) {
        // int is one of the 8 basic variables, and int[] is reference variable.
        // In a Java method, method arguments(MA) are passed by by value.
        // if basic variables are in MA, the value is copied, and the initial variable remain unchanged.
        // if reference variable is in MA, the address is copied (and passed as value), and the initial variable will be changed.
        int[] max = new int[1];
        max[0] = Integer.MIN_VALUE;
        maxPathSum(max, root);
        return max[0];
    }
    
    private int maxPathSum(int[] max, TreeNode root){
        if(root == null)
            return 0;
        int leftMax =  Math.max(0, maxPathSum(max, root.left));
        int rightMax = Math.max(0, maxPathSum(max, root.right));
        max[0] = Math.max(max[0],  root.val+leftMax+rightMax);
        return root.val+Math.max(leftMax,rightMax);
    }
}
