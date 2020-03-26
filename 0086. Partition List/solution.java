/*
 * My solution wit extra space.
 *
 * Time: O(n)
 * Space: O(n)
 */
class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode smallDummy = new ListNode(0);
        ListNode largeDummy = new ListNode(0);
        ListNode small = smallDummy, large = largeDummy;
        
        while (head != null) {
            if (head.val < x) {
                small.next = new ListNode(head.val);
                small = small.next;
            } else {
                large.next = new ListNode(head.val);
                large = large.next;
            }
            head = head.next;
        }
        
        small.next = largeDummy.next;
        return smallDummy.next;
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
