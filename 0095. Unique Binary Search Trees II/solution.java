/**
 * Other's solution of Divide-and-conquer
 *
 * This problem is a variant of the problem of Unique Binary Search Trees.
 * 
 * It is intuitive to solve this problem by following the same algorithm. The code
 * is in a divide-and-conquer style.
 */
class Solution {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<>();
        return genTreeList(1, n);
    }
    
    private List<TreeNode> genTreeList(int start, int end) {
        List<TreeNode> treeList = new ArrayList<>();
        if (start > end) treeList.add(null);
        
        for (int idx = start; idx <= end; idx++) {
            List<TreeNode> leftList = genTreeList(start, idx - 1);
            List<TreeNode> rightList = genTreeList(idx + 1, end);
            for (TreeNode left: leftList) {
                for (TreeNode right: rightList) {
                    TreeNode root = new TreeNode(idx);
                    root.left = left;
                    root.right = right;
                    treeList.add(root);
                }
            }
        }
        return treeList;
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
