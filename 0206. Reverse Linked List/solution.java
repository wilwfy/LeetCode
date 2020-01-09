/*
 * Iterative solution
 *
 * Time complexity : O(n). Assume that n is the list's length, the time complexity is O(n).
 * Space complexity : O(1).
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode curNode = head;
        ListNode preNode = null;
        ListNode nexNode = new ListNode(0);

        while (curNode != null) {
            nexNode = curNode.next;
            curNode.next = preNode;
            preNode = curNode;
            curNode = nexNode;
        }
        //head.next = preNode;
        //System.out.println(head.val);
        return preNode;
    }
}


/*
 * Recursive solution
 *
 * Time complexity : O(n). Assume that n is the list's length, the time complexity is O(n).
 * Space complexity : O(n). The extra space comes from implicit stack space due to recursion. The recursion could go up to n levels deep.
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        
        if ((head == null) ||
            (head.next == null)) // we reach the last element
            return head;
        
        // node is the last element of the original list and
        // needs to be eventually returned as the result since
        // it becomes the first element of the new list
        ListNode node = reverseList(head.next);
        
        // head is current node
        head.next.next = head;
        head.next = null;
        return node;
    }
}


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
