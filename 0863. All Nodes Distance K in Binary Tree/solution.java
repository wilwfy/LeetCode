/**
 * Official solution of Percolate Distance
 * Intuition
 * 
 * From root, say the target node is at depth 3 in the left branch. It means that any nodes that
 * are distance K - 3 in the right branch should be added to the answer.
 * 
 * Algorithm
 * 
 * Traverse every node with a depth first search dfs. We'll add all nodes x to the answer such that
 * node is the node on the path from x to target that is closest to the root.
 * 
 * To help us, dfs(node) will return the distance from node to the target. Then, there are 4 cases:
 * 
 * If node == target, then we should add nodes that are distance K in the subtree rooted at target.
 * 
 * If target is in the left branch of node, say at distance L+1, then we should look for nodes that
 * are distance K - L - 1 in the right branch.
 * 
 * If target is in the right branch of node, the algorithm proceeds similarly.
 * 
 * If target isn't in either branch of node, then we stop.
 * 
 * In the above algorithm, we make use of the auxillary function subtree_add(node, dist) which adds
 * the nodes in the subtree rooted at node that are distance K - dist from the given node.
 *
 * Time Complexity: O(N), where N is the number of nodes in the given tree.
 * Space Complexity: O(N).
 */
class Solution {
    List<Integer> ans;
    TreeNode target;
    int K;
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        ans = new LinkedList();
        this.target = target;
        this.K = K;
        dfs(root);
        return ans;
    }

    // Return vertex distance from node to target if exists, else -1
    // Vertex distance: the number of vertices on the path from node to target
    public int dfs(TreeNode node) {
        if (node == null)
            return -1;
        else if (node == target) {
            subtree_add(node, 0);
            return 1;
        } else {
            int L = dfs(node.left), R = dfs(node.right);
            if (L != -1) {
                if (L == K) ans.add(node.val);
                subtree_add(node.right, L + 1);
                return L + 1;
            } else if (R != -1) {
                if (R == K) ans.add(node.val);
                subtree_add(node.left, R + 1);
                return R + 1;
            } else {
                return -1;
            }
        }
    }

    // Add all nodes 'K - dist' from the node to answer.
    public void subtree_add(TreeNode node, int dist) {
        if (node == null) return;
        if (dist == K)
            ans.add(node.val);
        else {
            subtree_add(node.left, dist + 1);
            subtree_add(node.right, dist + 1);
        }
    }
}


/**
 * Other's DFS with HashMap
 * 
 * Intuition
 * 
 * As we know, if the distance from a node to target node is k, the distance from its child to
 * the target node is k + 1 unless the child node is closer to the target node which means the
 * target node is in it's subtree.
 * 
 * To avoid this situation, we need to travel the tree first to find the path from root to target, to:
 *   - store the value of distance in hashamp from the all nodes in that path to target
 * 
 * Then we can easily use dfs to travel the whole tree. Every time when we meet a treenode which has
 * already stored in map, use the stored value in hashmap instead of the value from its parent node.
 */
class Solution {
    Map<TreeNode, Integer> map; // key is node, value is the distance to the target node
    
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        map = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        find(root, target);
        dfs(root, K, map.get(root), res);
        return res;
    }
    
    // find target node first and store the distance in that path that we could use it later directly
    private int find(TreeNode root, TreeNode target) {
        if (root == null) return -1;
        if (root == target) {
            map.put(root, 0);
            return 0;
        }
        int left = find(root.left, target);
        if (left >= 0) {
            map.put(root, left + 1);
            return left + 1;
        }
        int right = find(root.right, target);
        if (right >= 0) {
            map.put(root, right + 1);
            return right + 1;
        }
        return -1;
    }
    
    private void dfs(TreeNode root, int K, int distance, List<Integer> res) {
        if (root == null) return;
        if (map.containsKey(root)) distance = map.get(root);
        if (distance == K) res.add(root.val);
        dfs(root.left, K, distance + 1, res);
        dfs(root.right, K, distance + 1, res);
    }
}


/**
 * Other's DFS without HashMap
 * 
 * Intuition
 * same idea, combine two recursive function
 */
class Solution {
    Map<TreeNode, Integer> map; // key is node, value is the distance to the target node
    
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> res = new ArrayList<>();
        if (K == 0) {
            res.add(target.val);
        } else {
            dfs(root, target.val, K, 0, res);
        }
        return res;
    }
       
    private int dfs(TreeNode node, int target, int K, int depth, List<Integer> res) {
        if (node == null) return 0;
        if (depth == K) {
            res.add(node.val);
            return 0;
        }
        int left, right;
        if (node.val == target || depth > 0) {
            left = dfs(node.left, target, K, depth + 1, res);
            right = dfs(node.right, target, K, depth + 1, res);
        } else {
            left = dfs(node.left, target, K, depth, res);
            right = dfs(node.right, target, K, depth, res);
        }
        
        if (node.val == target) return 1;
        
        if (left == K || right == K) {
            res.add(node.val);
            return 0;
        }
        
        if (left > 0) {
            dfs(node.right, target, K, left + 1, res);
            return left + 1;
        }
        
        if (right > 0) {
            dfs(node.left, target, K, right + 1, res);
            return right + 1;
        }
        
        return 0;
    }
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
