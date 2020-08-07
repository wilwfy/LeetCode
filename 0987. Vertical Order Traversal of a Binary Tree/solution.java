/**
 * Other's solution with HashMap, TreeMap and PriorityQueue
 *
 * Intuition:
 * we use a hashmap to record the x-coordinate of the nodes, and record the minX and maxX to get the values
 * we use a treemap to record the y-coordinate of the nodes, and use a priorityQueue to keep the ascending order
 * 
 * Possible Questions:
 * why use hashmap first then treemap？
 * This is because hashmap can get the key in constant time, while treemap gets the key in O(logn) time, where n is
 * the number of nodes. We need to traverse the hashmap from the smallest x to the highest x. And it is easy to
 * realize that the value of x is always continuous. That is, the difference between two continugous x will only be 1.
 * Thus, to traverse the hashmap, we just need to record the number of minimum x and maximum x.
 * 
 * Unlike x, the value of y is not contiuous. And that's why we need a treemap. The function "keySet()" of treemap
 * will return a series of keys in ascending order. And we can easily traverse the treemap by that.
 * 
 * Why use priorityQueue?
 * Acutally it does not matter whether you use a priorityQueue or a List. The time complexity does not differ a lot.
 * I think it is also a good idea to use ArrayList, and we need to sort it when we copy it to the final output.
 */
class Solution {
    Map<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new HashMap<>();
    int minX = 0, maxX = 0;
    
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        helper(root, 0, 0); // walk through the tree while recoding the values of all nodes
        
        List<List<Integer>> res = new ArrayList<>();
        for (int i = minX; i <= maxX; i++) {
            List<Integer> nodesAtSameX = new ArrayList<>();
            for (int key: map.get(i).keySet()) {
                PriorityQueue<Integer> pq = map.get(i).get(key);
                while (!pq.isEmpty()) {
                    nodesAtSameX.add(pq.poll());
                }
            }
            res.add(nodesAtSameX);
        }
        return res;
    }
    
    private void helper(TreeNode node, int x, int y) {
        if (node == null) return;
        // Record the min and max values of coordinate x
        minX = Math.min(minX, x);
        maxX = Math.max(maxX, x);
        if (map.get(x) == null) map.put(x, new TreeMap<Integer, PriorityQueue<Integer>>());
        if (map.get(x).get(y) == null) map.get(x).put(y, new PriorityQueue<Integer>());
        map.get(x).get(y).add(node.val);
        helper(node.left, x-1, y+1);
        helper(node.right, x+1, y+1);
    }
}


/**
 * Other's solution with TreeMap and PriorityQueue
 */
class Solution {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
        dfs(root, 0, 0, map);
        List<List<Integer>> list = new ArrayList<>();
        for (TreeMap<Integer, PriorityQueue<Integer>> ys : map.values()) {
            list.add(new ArrayList<>());
            for (PriorityQueue<Integer> nodes : ys.values()) {
                while (!nodes.isEmpty()) {
                    list.get(list.size() - 1).add(nodes.poll());
                }
            }
        }
        return list;
    }
    
    private void dfs(TreeNode root, int x, int y, TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map) {
        if (root == null) {
            return;
        }
        if (!map.containsKey(x)) {
            map.put(x, new TreeMap<>());
        }
        if (!map.get(x).containsKey(y)) {
            map.get(x).put(y, new PriorityQueue<>());
        }
        map.get(x).get(y).offer(root.val);
        dfs(root.left, x - 1, y + 1, map);
        dfs(root.right, x + 1, y + 1, map);
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
