/**
 * My solution of DFS
 *
 * Time complexity: O(N), where N is a number of nodes, since one has to visit each node.
 * Space complexity: up to O(H) to keep the recursion stack, where HH is a tree height.
 */
class Solution {
    int totalSum;
    
    public int sumRootToLeaf(TreeNode root) {
        totalSum = 0;
        int[] pathSum = new int[1];
        dfs(root, pathSum);
        return totalSum;
    }
    
    private void dfs(TreeNode node, int[] pathSum) {
        if (node == null) return;
        
        pathSum[0] = (pathSum[0] << 1) + node.val;        
        if (node.left == null && node.right == null) {
            totalSum += pathSum[0];
        } else {
            if (node.left != null) {
                dfs(node.left, pathSum);
                pathSum[0] >>= 1;
            }
            if (node.right != null) {
                dfs(node.right, pathSum);
                pathSum[0] >>= 1;
            }
        }
    }
}


/**
 * Other's solution of DFS
 *
 * Intuition
 * Easily decompose this problem into 2 sub-problem:
 * 
 * Find all path from root to leaves. DFS recursion should help do that.
 * Transform a path from base to base 10.
 * You can do this separately, and it will be a O(N^2) solution.
 * In my solution, I combine them together.
 * 
 * Explanation:
 * We recursively pass the current value of path to the children.
 * If root == null, no value, return 0.
 * If root != null,
 * we double the value from its parent and add the node's value,
 * like the process of transforming base 2 to base 10.
 * 
 * In the end of recursion,
 * if root.left == root.right == null,
 * return the current val.
 * 
 * 
 * Complexity:
 * Time: O(N)
 * Space: O(H) for recursion.
 */
class Solution {
    public int sumRootToLeaf(TreeNode root) {
        return dfs(root, 0);
    }

    public int dfs(TreeNode root, int val) {
        if (root == null) return 0;
        val = val * 2 + root.val;
        return root.left == root.right ? val : dfs(root.left, val) + dfs(root.right, val);
    }
}


/**
 * Official solution of DFS
 *
 * Time complexity: O(N), where N is a number of nodes, since one has to visit each node.
 * Space complexity: up to O(H) to keep the recursion stack, where HH is a tree height.
 */
class Solution {
    int rootToLeaf = 0;

    public int sumRootToLeaf(TreeNode root) {
        preorder(root, 0);
        return rootToLeaf;
    }
    
    public void preorder(TreeNode r, int currNumber) {
        if (r != null) {
            currNumber = (currNumber << 1) | r.val;
            // if it's a leaf, update root-to-leaf sum
            if (r.left == null && r.right == null) {
            rootToLeaf += currNumber;
            }
            preorder(r.left, currNumber);
            preorder(r.right, currNumber);
        }
    }
}


/**
 * Official solution of Morris Preorder Traversal.
 *
 * Intuition
 * We discussed already iterative and recursive preorder traversals, which both have great time complexity
 * though use up to O(H) to keep the stack. We could trade in performance to save space.
 * 
 * The idea of Morris preorder traversal is simple: to use no space but to traverse the tree.
 * 
 * How that could be even possible? At each node one has to decide where to go: to the left or to the right,
 * traverse the left subtree or traverse the right subtree. How one could know that the left subtree is
 * already done if no additional memory is allowed?
 * 
 * The idea of Morris algorithm is to set the temporary link between the node and its predecessor:
 * predecessor.right = root. So one starts from the node, computes its predecessor and verifies if
 * the link is present.
 * 
 *   - There is no link? Set it and go to the left subtree.
 * 
 *   - There is a link? Break it and go to the right subtree.
 * 
 * There is one small issue to deal with : what if there is no left child, i.e. there is no left subtree?
 * Then go straightforward to the right subtree.
 *
 * Time complexity: O(N), where N is a number of nodes.
 * Space complexity: O(1).
 */
class Solution {
    public int sumRootToLeaf(TreeNode root) {
        int rootToLeaf = 0, currNumber = 0;
        int steps;
        TreeNode predecessor;

        while (root != null) {
            // If there is a left child,
            // then compute the predecessor.
            // If there is no link predecessor.right = root --> set it.
            // If there is a link predecessor.right = root --> break it.
            if (root.left != null) {
                // Predecessor node is one step to the left
                // and then to the right till you can.
                predecessor = root.left;
                steps = 1;
                while (predecessor.right != null && predecessor.right != root) {
                    predecessor = predecessor.right;
                    ++steps;
                }

                // Set link predecessor.right = root
                // and go to explore the left subtree
                if (predecessor.right == null) {
                    currNumber = (currNumber << 1) | root.val;
                    predecessor.right = root;
                    root = root.left;
                }
                // Break the link predecessor.right = root
                // Once the link is broken,
                // it's time to change subtree and go to the right
                else {
                    // If you're on the leaf, update the sum
                    if (predecessor.left == null) {
                        rootToLeaf += currNumber;
                    }
                    // This part of tree is explored, backtrack
                    for(int i = 0; i < steps; ++i) {
                        currNumber >>= 1;
                    }
                    predecessor.right = null;
                    root = root.right;
                }
            }
            // If there is no left child
            // then just go right.
            else {
                currNumber = (currNumber << 1) | root.val;
                // if you're on the leaf, update the sum
                if (root.right == null) {
                    rootToLeaf += currNumber;
                }
                root = root.right;
            }
        }
        return rootToLeaf;
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
