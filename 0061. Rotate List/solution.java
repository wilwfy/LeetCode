class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if ((head == null) || (k == 0)) return head;
        //System.out.println("head.val = " + head.val);
        ListNode cur = head, pre = head;
        int shift = 0;
        while ((cur != null) && (k > 0)) {
            cur = cur.next;
            k--;
            shift++;
            //System.out.println("Now cur.val = " + cur.val);
        }

        
        if (cur == null) {
        // we have reached the tail while k >= 1 and shift is the length of the list
            shift = k % shift;
            cur = head;
            while (shift > 0) {
                cur = cur.next;
                shift--;
            }
        }
        
        while (cur.next != null) {
            cur = cur.next;
            pre = pre.next;
        }
        cur.next = head;
        //System.out.println("pre.val = " + pre.val);
        //System.out.println("cur.val = " + cur.val);
        //System.out.println("head.val = " + head.val);
        head = pre.next;
        pre.next = null;
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
