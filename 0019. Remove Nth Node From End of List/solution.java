/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if ((head == null) || (n == 0)) return head;
        ListNode cur, prev_n;
        cur = prev_n = head;
        while (cur != null) {
            if (n == -1) prev_n = prev_n.next;
            cur = cur.next;
            if (n != -1) n--;
        }
        if (n == 0)
            head = prev_n.next;
        else
            prev_n.next = prev_n.next.next;
        return head;
    }
}
