/*
 * My solution with constant space. But too much time: 688 ms
 */
class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null) return head;
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        
        dummy.next = head;
        ListNode pre = dummy, cur = head;
        while (cur != null) {
            if (cur.val >= pre.val) {
                pre = cur;
                cur = cur.next;
            } else {
                pre.next = cur.next;
                ListNode start = dummy;
                while (start.next.val <= cur.val) {
                    start = start.next;
                }
                cur.next = start.next;
                start.next = cur;
                cur = pre.next;
            }
        }
        return dummy.next;
    }
}


/*
 * Other's solution of Merge Sort.
 */
class Solution {
    public ListNode sortList(ListNode head) {
        if ((head == null) || (head.next == null)) return head;
        
        ListNode prev = null, slow = head, fast = head;
        while ((fast != null) && (fast.next != null)) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        // split the list between node prev and node slow
        prev.next = null;
        
        // Sort each half part
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);
        
        // Merge l1 and l2
        return mergeList(l1, l2);
    }
    
    private ListNode mergeList(ListNode l1, ListNode l2) {
        // Use null here to keep constant space O(1), otherwise the space will become O(logn);
        ListNode head = null, res;
        if (l1.val <= l2.val) {
            head = l1;
            l1 = l1.next;
        } else {
            head = l2;
            l2 = l2.next;
        }
        res = head;
        
        while ((l1 != null) && (l2 != null)) {
            if (l1.val <= l2.val) {
                head.next = l1;
                l1 = l1.next;
            } else {
                head.next = l2;
                l2 = l2.next;
            }
            head = head.next;
        }
        
        if (l1 != null) head.next = l1;
        if (l2 != null) head.next = l2;
        
        return res;
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
