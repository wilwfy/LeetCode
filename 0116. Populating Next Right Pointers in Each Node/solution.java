/**
 * My solution of Iteration with extra space
 *
 * Time: O(n)
 * Space: O(2^H). H is the height of the tree.
 */
class Solution {
    public Node connect(Node root) {
        if (root == null) return root;
        Queue<Node> qu = new LinkedList<>();
        qu.offer(root);
        while (!qu.isEmpty()) {
            int quSize = qu.size();
            Node prev = null;
            for (int i = 0; i < quSize; i++) {
                Node curr = qu.poll();
                if (curr.left != null) qu.offer(curr.left);
                if (curr.right != null) qu.offer(curr.right);
                if (prev != null) prev.next = curr;
                prev = curr;
            }
        }
        return root;
    }
}


/**
 * My solution of Recursion with constant extra space
 *
 * Time: O(n)
 * Space: O(H). H is the height of the tree. The implicit stack space does not count as extra space for this problem.
 */
class Solution {
    public Node connect(Node root) {
        if (root == null) return root;
        dfs(root.left, root.right);
        dfs(root.right, null);
        return root;
    }
    
    private void dfs(Node node, Node nextNode) {
        if (node == null) return;
        
        node.next = nextNode;
        dfs(node.left, node.right);
        if (nextNode != null)
            dfs(node.right, nextNode.left);
        else
            dfs(node.right, null);
    }
}


/**
 * Other's solution of Iteration without extra space
 *
 * Time: O(n). Need walk through every node in the tree.
 * Space: O(1).
 */
class Solution {
    public Node connect(Node root) {
        Node level_start = root;
        while (level_start != null){
            Node cur = level_start;
            while (cur != null) {
                if (cur.left != null) {
                    cur.left.next = cur.right;
                    if (cur.next != null) cur.right.next = cur.next.left; // we don't need to check for the right child
                }
                cur = cur.next;
            }
            level_start = level_start.left;
        }
        return root;
    }
}

/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/
