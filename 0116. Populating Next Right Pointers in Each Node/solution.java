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
