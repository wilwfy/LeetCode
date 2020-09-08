/**
 * Official DFS solution with HashMap
 *
 * Intuition
 * 
 * We can serialize each subtree. For example, the tree
 * 
 *    1
 *   / \
 *  2   3
 *     / \
 *    4   5
 * can be represented as the serialization 1,2,#,#,3,4,#,#,5,#,#, which is a unique representation of the tree.
 * 
 * Algorithm
 * 
 * Perform a depth-first search, where the recursive function returns the serialization of the tree. At each node,
 * record the result in a map, and analyze the map after to determine duplicate subtrees.
 *
 * Time Complexity: O(N^2), where N is the number of nodes in the tree. We visit each node once, but each
 *                  creation of serial may take O(N) work.
 * Space Complexity: O(N^2), the size of count.
 */
class Solution {
    Map<String, Integer> countMap;
    List<TreeNode> ans;
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        countMap = new HashMap<>();
        ans = new ArrayList<>();
        dfs(root);
        return ans;
    }
    
    private String dfs(TreeNode node) {
        if (node == null) return "#";
        String serial = node.val + "," + dfs(node.left) + "," + dfs(node.right); // Take O(N) to build the String
        countMap.put(serial, countMap.getOrDefault(serial, 0) + 1);
        if (countMap.get(serial) == 2)
            ans.add(node);
        return serial;
    }
}


/**
 * Official DFS solution with HashMap and Unique Identifier
 *
 * Intuition
 * 
 * Suppose we have a unique identifier for subtrees: two subtrees are the same if and only if they have the same id.
 * 
 * Then, for a node with left child id of x and right child id of y, (node.val, x, y) uniquely determines the tree.
 * 
 * Algorithm
 * 
 * If we have seen the triple (node.val, x, y) before, we can use the identifier we've remembered. Otherwise, we'll
 * create a new one.
 *
 * Time Complexity: O(N^2), where N is the number of nodes in the tree. We visit each node once, but each
 *                  creation of serial may take O(N) work.
 * Space Complexity: O(N). Every structure we use is using O(1) storage per node.
 */
class Solution {
    int t;
    Map<String, Integer> treesMap;
    Map<Integer, Integer> countMap;
    List<TreeNode> ans;
    
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        t = 1;
        treesMap = new HashMap();
        countMap = new HashMap<>();
        ans = new ArrayList<>();
        lookupDFS(root); // PreOrder Traverse
        return ans;
    }
    
    private int lookupDFS(TreeNode node) {
        if (node == null) return 0;
        String serial = node.val + "," + lookupDFS(node.left) + "," + lookupDFS(node.right); // Take O(N) to build the String
        int uid = treesMap.computeIfAbsent(serial, x -> t++);
        countMap.put(uid, countMap.getOrDefault(uid, 0) + 1);
        if (countMap.get(uid) == 2)
            ans.add(node);
        return uid;
    }
}


/**
 * Other's DFS solution with HashMap
 */
class Solution {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> res = new LinkedList<>();
        postorder(root, new HashMap<>(), res);
        return res;
    }
    
    public String postorder(TreeNode cur, Map<String, Integer> map, List<TreeNode> res) {
        if (cur == null) return "#";  
        String serial = cur.val + "," + postorder(cur.left, map, res) + "," + postorder(cur.right, map, res);
        if (map.getOrDefault(serial, 0) == 1) res.add(cur);
        map.put(serial, map.getOrDefault(serial, 0) + 1);
        return serial;
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
