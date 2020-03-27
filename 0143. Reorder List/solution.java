/*
 * My solution of using Stack
 *
 * Time: O(n)
 * Space: O(n)
 */
class Solution {
    public void reorderList(ListNode head) {
        if ((head == null) || (head.next == null)) return;
        
        int leftCnt = 0, rightCnt = 0;
        Stack<ListNode> stack = new Stack<>();
        
        // Find the middle node of the list
        ListNode slow = head, fast = head;
        while ((fast != null) && (fast.next != null)) {
            slow = slow.next;
            fast = fast.next.next;
            leftCnt++;
        }
        
        // Push the nodes of right side of the middle into stack
        while (slow != null) {
            stack.push(slow);
            slow = slow.next;
            rightCnt++;
        }
        
        // If the length of the list is odd,
        // then the stack contain one extra node (middle node) we
        // don't need and we need decrease the count by one
        if (rightCnt > leftCnt) rightCnt--;
        
        slow = head;
        ListNode next = null;
        // Use rightCnt to avoid pop out the middle node
        while (!stack.isEmpty() && (rightCnt > 0)) {
            next = slow.next;
            slow.next = stack.pop();
            slow.next.next = next;
            slow = next;
            rightCnt--;
        }
        slow.next = null;
    }
}

/*
 * Other's solution without extra space
 *
 * Time: O(n)
 * Space: O(1)
 */
class Solution {
    public void reorderList(ListNode head) {
        if ((head == null) || (head.next == null)) return;        
        
        // step 1. cut the list to two halves
        // prev will be the tail of 1st half
        // slow will be the head of 2nd half
        ListNode prev = null, slow = head, fast = head;
        while ((fast != null) && (fast.next != null)) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null;
        
        // step 2. reverse the 2nd half
        ListNode tmp = null, next = slow;
        while (slow != null) {
            next = slow.next;
            slow.next = tmp;
            tmp = slow;
            slow = next;
        }
        // Now slow is null, tmp is the last node of the original list
        
        // step 3. merge the two halves
        ListNode hd = tmp;
        while (hd != null) {
            tmp = head.next;
            head.next = hd;
            // head and hd are switched
            head = hd;
            hd = tmp;
        }
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
