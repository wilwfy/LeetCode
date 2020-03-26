/*
 * My solution with extra space.
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


/*
 * My solution without extra space.
 *
 * Time Complexity: O(N), where N is the number of nodes in the original linked list and we iterate the original list.
 * Space Complexity: O(1), we have not utilized any extra space, the point to note is that we are reforming the original list,
 *                   by moving the original nodes, we have not used any extra space as such.
 */
class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode smallDummy = new ListNode(0);
        ListNode largeDummy = new ListNode(0);
        ListNode small = smallDummy, large = largeDummy;
        
        while (head != null) {
            if (head.val < x) {
                small.next = head;
                small = small.next;
            } else {
                large.next = head;
                large = large.next;
            }
            head = head.next;
        }
        
        small.next = largeDummy.next;
        large.next = null;
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
