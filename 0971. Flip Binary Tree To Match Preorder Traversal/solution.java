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
 * Other's DFS solution
 *
 * Algorithm
 * Global integer i indicates next index in voyage v.
 * If current node == null, it's fine, we return true
 * If current node.val != v[i], doesn't match wanted value, return false
 * If left child exist but don't have wanted value, flip it with right child.
 *
 * Time Complexity: O(N), where N is the number of nodes in the given tree.
 * Space Complexity: O(N).
 */
class Solution {
    List<Integer> res = new ArrayList<>();
    int i = 0;

    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        return dfs(root, voyage) ? res : Arrays.asList(-1);
    }

    public Boolean dfs(TreeNode node, int[] voyage) {
        if (node == null) return true;
        if (node.voyageal != voyage[i++]) return false;
        if (node.left != null && node.left.voyageal != voyage[i]) {
            res.add(node.voyageal);
            return dfs(node.right, voyage) && dfs(node.left, voyage);
        }
        return dfs(node.left, voyage) && dfs(node.right, voyage);
    }
}


/**
 * Other's BFS solution with Stack
 *
 * Time Complexity: O(N), where N is the number of nodes in the given tree.
 * Space Complexity: O(Hight).
 */
class Solution {
    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        List<Integer> res = new ArrayList<>();
        int i = 0;
        Stack<TreeNode> s = new Stack<>();
        s.push(root);
        while (s.size() > 0) {
            TreeNode node = s.pop();
            if (node == null) continue;
            if (node.val != voyage[i++]) return Arrays.asList(-1);
            // For a pre order, the first node is root, and the second is the left child. The right child can be several nodes
            // away from the second node, for the left subtree can occupy multiple nodes, but the first and second node should
            // be root and the left child. And here is when the second node right after the root is not the left child but the
            // right child, that means you need a swap right at the current root.
            if (node.right != null && node.right.val == voyage[i]) {
                if (node.left != null) res.add(node.val);
                s.push(node.left);
                s.push(node.right); // Stack, the right child node will be processed before the left child node
            } else {
                s.push(node.right);
                s.push(node.left); // Stack, the left child node will be processed before the right child node
            }
        }
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
