/**
 * My solution of BFS
 */
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        
        Queue<TreeNode> qu = new LinkedList<>();
        Queue<TreeNode> quNextLevel = new LinkedList<>();
        
        int levelCnt = 0;
        qu.offer(root);
        while (!qu.isEmpty()) {
            if (res.size() == levelCnt) res.add(new LinkedList<Integer>());
            TreeNode cur = qu.poll();
            if (cur != null) {
                res.get(levelCnt).add(cur.val);
                quNextLevel.offer(cur);
            }

            if (qu.isEmpty()) { // the node values in current level are all scanned into result
                while (!quNextLevel.isEmpty()) {
                    TreeNode node = quNextLevel.poll();
                    if (node.left != null) qu.offer(node.left);
                    if (node.right != null) qu.offer(node.right);
                }
                
                if (levelCnt % 2 != 0)
                    Collections.reverse(res.get(levelCnt));
                
                levelCnt++;
            }
        }
        return res;
    }
}


/**
 * Other's solution of BFS
 *
 * Time & Space: O(n). n is the node number in the tree.
 */
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean zigzag = false;
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int cnt = queue.size();
            for (int i = 0; i < cnt; i++) {
                TreeNode node = queue.poll();
                if (zigzag) {
                    level.add(0, node.val);
                }
                else {
                    level.add(node.val);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(level);
            zigzag = !zigzag;
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
 *     TreeNode(int x) { val = x; }
 * }
 */
