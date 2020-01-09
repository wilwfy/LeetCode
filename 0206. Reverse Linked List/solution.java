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






/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
