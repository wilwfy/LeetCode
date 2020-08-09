/**
 * Other's solution of DFS with Brute Force
 *
 * Time: O(n^2) in worst case (no branching);
 *       O(nlogn) in best case (balanced tree).
 * Space: O(h) due to recursion and h is the height of the tree.
 */
class Solution {
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        return pathSumFrom(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }
    
    private int pathSumFrom(TreeNode node, int sum) {
        if (node == null) return 0;
        int restSum = sum - node.val;
        return (restSum == 0 ? 1 : 0) + pathSumFrom(node.left, restSum) + pathSumFrom(node.right, restSum);
    }
}


/**
 * Other's solution of DFS with HashMap of Prefix Sum
 *
 * Time: O(n). Need walk through every node in the tree.
 * Space: O(h) due to recursion and h is the height of the tree.
 */
class Solution {
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        // The map stores <prefix sum, frequency> pairs before getting to the current node.
        Map<Integer, Integer> preSumMap = new HashMap<>();
        preSumMap.put(0, 1);
        return helper(root, 0, sum, preSumMap);
    }
    
    private int helper(TreeNode node, int currSum, int target, Map<Integer, Integer> preSumMap) {
        if (node == null) return 0;
        // update the prefix sum by adding the current val
        currSum += node.val;
        // get the number of valid path, ended by the current node.
        // Need do this prior to updating the map since the target could be 0 which leads to currSum - target = currSum
        int numPathToCurr = preSumMap.getOrDefault(currSum - target, 0);
        // update the map with the current sum, so the map is good to be passed to the next recursion
        preSumMap.put(currSum, preSumMap.getOrDefault(currSum, 0) + 1);
        
        // add the 3 parts together
        int res = numPathToCurr + helper(node.left, currSum, target, preSumMap) + helper(node.right, currSum, target, preSumMap);
        // restore the map, as the recursion goes from the bottom to the top
        preSumMap.put(currSum, preSumMap.get(currSum) - 1);
        return res;
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
