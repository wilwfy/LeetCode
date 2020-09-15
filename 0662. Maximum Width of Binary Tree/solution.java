/**
 * Other's solution of DFS
 *
 * Explanation
 *
 * We know that a binary tree can be represented by an array (assume the root begins from the
 * position with index 1 in the array). If the index of a node is i, the indices of its two
 * children are 2*i and 2*i + 1. The idea is to use one arrays start[] to record the index of
 * the leftmost node in each level. For each level of the tree, the current width is the value
 * of current index at current level - start[level] + 1. Then, we just need to find the maximum
 * width.
 */
class Solution {
    private int max = 1;
    
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        List<Integer> startOfLevel = new LinkedList<>();
        helper(root, 0, 1, startOfLevel);
        return max;
    }
    
    public void helper(TreeNode root, int level, int index, List<Integer> list) {
        if (root == null) return;
        if (level == list.size()) list.add(index); // Record the index of the leftmost node at each level
        max = Math.max(max, index + 1 - list.get(level)); // calculate current max width at the level
        helper(root.left, level + 1, index * 2, list);
        helper(root.right, level + 1, index * 2 + 1, list);
    }
}


/**
 * Other's solution of BFS
 *
 * Explanation
 * One queue is used to store the nodes in each level. Another queue is used to store corresponding indices of these nodes.
 * Record the indices of the first and the last nodes at each level to calculate the width at that level.
 */
class Solution {    
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> nodeQue = new LinkedList<>();
        Queue<Integer> nodeIdxQue = new LinkedList<>();
        nodeQue.add(root);
        nodeIdxQue.add(1); // store index, assuming root's index is 1
        int max = 0;
        while (!nodeQue.isEmpty()) {
            int size = nodeQue.size();
            int start = 0, end = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = nodeQue.poll();
                int index = nodeIdxQue.poll();
                if (i == 0) start = index; // start index for each level
                if (i == size - 1) end = index; // end index for each level
                if (node.left != null) {
                    nodeQue.offer(node.left);
                    nodeIdxQue.offer(index * 2);
                }
                if (node.right != null) {
                    nodeQue.offer(node.right);
                    nodeIdxQue.offer(index * 2 + 1);
                }
            }
            max = Math.max(max, end - start + 1);
        }
        return max;
    }
}
