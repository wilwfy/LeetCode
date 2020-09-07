/**
 * Other's DFS solution with HashMap
 *
 * Use a hashMap count to count the subtree sum occurrence.
 * 
 * A sub function dfs(TreeNode node) will
 * travel through a tree, recursively calculate the sum of subtree,
 * increment the count, and finally return the sum of the sub tree.
 *
 * Time Complexity: O(n), n is node count
 * Space Complexity: O(log(n)) for recursion stack.
 */
class Solution {
    Map<Integer, Integer> countMap;
    int maxCnt;
    
    public int[] findFrequentTreeSum(TreeNode root) {
        countMap  = new HashMap<>();
        maxCnt = 0;
        dfs(root);
        List<Integer> res = new ArrayList<>();
        for (int sum: countMap.keySet()) {
            if (countMap.get(sum) == maxCnt)
                res.add(sum);
        }
        return res.stream().mapToInt(i -> i).toArray();
    }
    
    private int dfs(TreeNode node) {
        if (node == null) return 0;
        int subtreeSum = dfs(node.left) + node.val + dfs(node.right);
        countMap.put(subtreeSum, countMap.getOrDefault(subtreeSum, 0) + 1);
        maxCnt = Math.max(maxCnt, countMap.get(subtreeSum));
        return subtreeSum;
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
