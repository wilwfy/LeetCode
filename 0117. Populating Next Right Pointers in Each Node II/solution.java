/**
 * Other's Iteration solution with constant space
 *
 * Ituition: level-order traversal
 *
 * Time: O(n)
 * Space: O(1)
 */
class Solution {
    public Node connect(Node root) {
        Node head = root, tmpNode = new Node(0);
        //loop the head in the level
        while (head != null) {
        	//loop the current node in each level
        	Node cur = head, child = tmpNode; // head points to the first node at current level
        	while (cur != null) {
        		if (cur.left != null) {
        			child.next = cur.left; // Now tmpNode points to the first node at next level
        			child = cur.left;
        		}
        		if (cur.right != null) {
        			child.next = cur.right;
        			child = cur.right;
        		}
        		cur = cur.next; // move to the next node at current level
        	}
        	head = tmpNode.next; // Now head points to the first node at next level
        	tmpNode.next = null; // Reset the 'next' pointer of tmpNode
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
