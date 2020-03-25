/*
 * My solution with extra space
 *
 * Time: O(n^2)
 * Space: O(n)
 */
class Solution {
    public ListNode insertionSortList(ListNode head) {
        if ((head == null) || (head.next == null)) return head;
        
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        ListNode cur = dummy;
        while (head != null) {
            ListNode node = new ListNode(head.val);
            if (head.val >= cur.val) {
                cur.next = node;
                cur = node;
            } else {
                ListNode start = dummy;
                while (start.next.val <= head.val) {
                    start = start.next;
                }
                node.next = start.next;
                start.next = node;
            }
            head = head.next;
        }
        return dummy.next;
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
