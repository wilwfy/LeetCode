/*
 * My solution wit Two Pointers
 *
 * Time: O(n)
 * Space: O(1)
 */
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if ((head == null) || (head.next == null) || (head.next.next == null)) return head;
        
        ListNode oddDummy = new ListNode(0);
        ListNode eveDummy = new ListNode(0);
        oddDummy.next = head;
        eveDummy.next = head.next;
        
        ListNode odd = head, eve = head.next, tmp;
        while ((odd != null) && (eve != null)) {
            tmp = eve.next;
            odd.next = tmp;
            if (tmp == null)
                break;
            eve.next = tmp.next;
            odd = tmp;
            eve = tmp.next;
        }
        odd.next = eveDummy.next;
        return oddDummy.next;
    }
}


/*
 * Official concise solution wit Two Pointers
 *
 * Time: O(n)
 * Space: O(1)
 */
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null) return null;
        ListNode odd = head, even = head.next, evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
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
