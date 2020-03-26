/*
 * Other's solution based on interweave
 *
 * An intuitive solution is to keep a hash table for each node in the list, via which we just need to iterate the list in 2 rounds
 * respectively to create nodes and assign the values for their random pointers. As a result, the space complexity of this solution
 * is O(N), although with a linear time complexity.
 * Note: if we do not consider the space reversed for the output, then we could say that the algorithm does not consume any additional
 * memory during the processing, i.e. O(1) space complexity
 *
 * As an optimised solution, we could reduce the space complexity into constant. The idea is to associate the original node with its
 * copy node in a single linked list. In this way, we don't need extra space to keep track of the new nodes.
 */
class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) return head;
        
        Node node = head;
        // First round of iteration: make copy of each node in original list,
        // and interweave them like: A->A'->...->B->B'->...->C->C'->null
        while (node != null) {
            Node copy = new Node(node.val);
            copy.next = node.next;
            node.next = copy;
            node = node.next.next;
        }
        
        // Second round: duplicate the randome pointer
        node = head;
        while (node != null) {
            // Already has list like: A->A'->...->B->B'->...
            // If A.random -> B, then A'.random -> B'
            if (node.random != null)
                node.next.random = node.random.next;
            node = node.next.next;
        }
        
        // Third round: restore the original list and extract the copy list
        Node dummy = new Node(0), next, copy, copyIter;
        dummy.next = head.next; // Make dummy as the head of the copy list
        node = head;
        copyIter = dummy;
        while (node != null) {
            next = node.next.next; // locate the next original node
            copy = node.next;      // locate the next copy node
            // Extract the copy list
            copyIter.next = copy;
            copyIter = copy;
            // Restore the original list
            node.next = next;
            node = next;
        }
        return dummy.next;
    }
}


/*
 * Other's solution based on HashMap
 */
class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) return null;
      
        Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
      
        // loop 1. copy all the nodes
        RandomListNode node = head;
        while (node != null) {
            map.put(node, new RandomListNode(node.label));
            node = node.next;
        }
      
        // loop 2. assign next and random pointers
        node = head;
        while (node != null) {
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
            node = node.next;
        }
      
        return map.get(head);
    }
}


/*
 * Other's recursion solution based on DFS
 */
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        return copyRandomList(head, map);
    }
    private RandomListNode copyRandomList(RandomListNode head, Map<RandomListNode, RandomListNode> map) {
        if (head == null) {
            return null;
        }
        if (map.containsKey(head)) {
            return map.get(head);
        }
        RandomListNode newHead = new RandomListNode(head.label);
        map.put(head, newHead);
        newHead.next = copyRandomList(head.next, map);
        newHead.random = copyRandomList(head.random, map);
        
        return newHead;
        
    }
}
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/
