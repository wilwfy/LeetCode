/**
 * My DFS solution based on official solution with optimization
 *
 * Intuition
 * As we do a pre-order traversal, we will flip nodes on the fly to try to match our voyage with the given one.
 * 
 * If we are expecting the next integer in our voyage to be voyage[i], then there is only at most one choice for
 * path to take, as all nodes have different values.
 * 
 * Algorithm
 * Do a depth first search. If at any node, the node's value doesn't match the voyage, the answer is [-1].
 * 
 * Otherwise, we know when to flip: the next number we are expecting in the voyage voyage[i] is different from the
 * next child.
 *
 * Time Complexity: O(N), where N is the number of nodes in the given tree.
 * Space Complexity: O(N).
 */
class Solution {
    int nodeIdx;
    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        List<Integer> flipped = new ArrayList<>();
        nodeIdx = 0;
        preOrderDFS(root, voyage, flipped);
        return flipped;
    }
    
    private void preOrderDFS(TreeNode node, int[] voyage, List<Integer> flipped) {
        if (node == null || (flipped.size() == 1 && flipped.get(0) == -1)) return;
        if (node.val != voyage[nodeIdx++]) {
            flipped.clear();
            flipped.add(-1);
            return;
        }

        if (nodeIdx < voyage.length && node.left != null && node.left.val != voyage[nodeIdx]) {
            flipped.add(node.val);
            preOrderDFS(node.right, voyage, flipped);
            preOrderDFS(node.left, voyage, flipped);
        } else {
            preOrderDFS(node.left, voyage, flipped);
            preOrderDFS(node.right, voyage, flipped);
        }
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
