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


/*
 *
 */
public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode first = dummy;
    ListNode second = dummy;
    // Advances first pointer so that the gap between first and second is n nodes apart
    for (int i = 1; i <= n + 1; i++) {
        first = first.next;
    }
    // Move first to the end, maintaining the gap
    while (first != null) {
        first = first.next;
        second = second.next;
    }
    second.next = second.next.next;
    return dummy.next;
}
