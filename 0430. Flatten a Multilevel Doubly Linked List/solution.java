/*
 * My solution of recursion
 */
class Solution {
    public Node flatten(Node head) {
        if ((head == null) || (head.child == null && head.next == null))
            return head;
        
        helper(head);
        return head;
    }
    
    private Node helper(Node childHead) {
        if ((childHead == null) || (childHead.child == null && childHead.next == null))
            return childHead;
        Node node = childHead, prev = null;
        while (node != null) {
            if (node.child != null) {
                Node childEndNode = helper(node.child);
                childEndNode.next = node.next;
                
                if (node.next != null)
                    node.next.prev = childEndNode;

                node.next = node.child;
                node.child.prev = node;
                node.child = null;
            }
            prev = node;
            node = node.next;
        }
        return prev;
    }
}


/*
 * Other's solution of recursion
 */
class Solution {
    public Node flatten(Node head) {
    	flattentail(head);
    	return head;
    }

    // flattentail: flatten the node "head" and return the tail in its child (if exists)
    // the tail means the last node after flattening "head"

    // Five situations:
    // 1. null - no need to flatten, just return it
    // 2. no child, no next - no need to flatten, it is the last element, just return it
    // 3. no child, next - no need to flatten, go next
    // 4. child, no next - flatten the child and done
    // 5. child, next - flatten the child, connect it with the next, go next

    private Node flattentail(Node head) {
    	if (head == null) return head; // CASE 1
    	if (head.child == null) {
    		if (head.next == null) return head; // CASE 2
    		return flattentail(head.next); // CASE 3
    	}
    	else {
    		Node child = head.child;  
    		head.child = null;
    		Node next = head.next;
    		Node childtail = flattentail(child);
    		head.next = child;
    		child.prev = head;  
			if (next != null) { // CASE 5
				childtail.next = next;
				next.prev = childtail;
				return flattentail(next);
			}
            return childtail; // CASE 4
    	}	   	
    }
}


/*
 * Other's solution with Stack
 */
class Solution {
    public Node flatten(Node head) {
        Node curt = head;
        Stack<Node> stack = new Stack<>(); // store curt.next when curt.child is not null
        
        while(curt != null) {
            if(curt.child != null) {
                stack.push(curt.next); // might be null
                curt.next = curt.child;
                if(curt.next != null) curt.next.prev = curt;
                curt.child = null;
            } else if(curt.next == null && !stack.isEmpty()) { // reach of tail of child, reconnet the next of parent
                curt.next = stack.pop();
                if(curt.next != null) curt.next.prev = curt;
            }
            
            curt = curt.next;
        }
        
        return head;
    }
}


/*
 * Other's strait forward solution
 *
 * Basic idea is straight forward:
 * 1. Start form the head , move one step each time to the next node
 * 2. When meet with a node with child, say node p, follow its child chain to the end and connect the tail node with p.next, by doing this we merged the child chain back to the main thread
 * 3. Return to p and proceed until find next node with child.
 * 4. Repeat until reach null
 */
class Solution {
    public Node flatten(Node head) {
        if( head == null) return head;
	// Pointer
        Node p = head; 
        while( p!= null) {
            /* CASE 1: if no child, proceed */
            if( p.child == null ) {
                p = p.next;
                continue;
            }
            /* CASE 2: got child, find the tail of the child and link it to p.next */
            Node temp = p.child;
            // Find the tail of the child
            while( temp.next != null ) 
                temp = temp.next;
            // Connect tail with p.next, if it is not null
            temp.next = p.next;  
            if( p.next != null )  p.next.prev = temp;
            // Connect p with p.child, and remove p.child
            p.next = p.child; 
            p.child.prev = p;
            p.child = null;
        }
        return head;
    }
}

/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/
