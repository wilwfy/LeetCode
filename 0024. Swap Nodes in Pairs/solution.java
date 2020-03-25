// The illustration of Diagrams:
// https://leetcode.wang/leetCode-24-Swap-Nodes-in-Pairs.html

/*
 * Other's iteration solution
 *
 * Time: O(n)
 * Space: O(1)
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode point = dummy;
        while (point.next != null && point.next.next != null) { 
            ListNode swap1 = point.next;
            ListNode swap2 = point.next.next;
            point.next = swap2;
            swap1.next = swap2.next;
            swap2.next = swap1;
            point = swap1;
        }
        return dummy.next;
    }
}


/*
 * Other's recursion solution
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        if ((head == null) || (head.next == null)) return head;
        ListNode node = head.next;
        head.next = swapPairs(head.next.next);
        node.next = head;
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
