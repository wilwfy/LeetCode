/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
/*
 * Referring to other's solution: Recursion + Memoization
 *
 *Recurrence relation: i.e., how to get rob(root) from rob(root.left), rob(root.right), ... etc. 
 * From the point of view of the tree root, there are only two scenarios at the end: root is robbed
 * or is not. If it is, due to the constraint that "we cannot rob any two directly-linked houses", 
 * the next level of subtrees that are available would be the four "grandchild-subtrees" (root.left.left,
 * root.left.right, root.right.left, root.right.right). However if root is not robbed, the next level of
 * available subtrees would just be the two "child-subtrees" (root.left, root.right). We only need to choose
 * the scenario which yields the larger amount of money.
 */
class Solution {
    public int rob(TreeNode root) {
        return helper(root, new HashMap<TreeNode, Integer>());
    }
    
    public int helper(TreeNode node, HashMap<TreeNode, Integer> map) {
        if (node == null) return 0;
        
        // If this node has already been checked, just
        // return its possible maximum amount of money
        if (map.containsKey(node)) return map.get(node);
        
        int money = 0;
        // rob this node and its grandchildren
        money += node.val;
        if (node.left != null)
            money += helper(node.left.left, map) + helper(node.left.right, map);
        if (node.right != null)
            money += helper(node.right.left, map) + helper(node.right.right, map);
        
        // rob this node's children
        int childMoney = helper(node.left, map) + helper(node.right, map);
        
        money = Math.max(money, childMoney);
        map.put(node, money);
        return money;
    }
}



/*
 * Other's solution
 * 
 * If we were able to maintain the information about the two scenarios for each tree root, let's see how it plays out.
 * Redefine rob(root) as a new function which will return an array of two elements, the first element of which denotes
 * the maximum amount of money that can be robbed if root is not robbed, while the second element signifies the maximum
 * amount of money robbed if it is robbed.
 * Let's relate rob(root) to rob(root.left) and rob(root.right)..., etc. For the 1st element of rob(root), we only need
 * to sum up the larger elements of rob(root.left) and rob(root.right), respectively, since root is not robbed and we are
 * free to rob its left and right subtrees. For the 2nd element of rob(root), however, we only need to add up the 1st elements
 * of rob(root.left) and rob(root.right), respectively, plus the value robbed from root itself, since in this case it's guaranteed
 * that we cannot rob the nodes of root.left and root.right.
 * As you can see, by keeping track of the information of both scenarios, we decoupled the subproblems and the solution essentially
 * boiled down to a greedy one.
 */
class Solution {
    public int rob(TreeNode root) {
        int[] res = robSub(root);
        return Math.max(res[0], res[1]);
    }
    
    private int[] robSub(TreeNode root) {
        if (root == null) return new int[2];
        
        int[] left = robSub(root.left);
        int[] right = robSub(root.right);
        int[] res = new int[2];
    
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = root.val + left[0] + right[0];
        
        return res;
    }
}
