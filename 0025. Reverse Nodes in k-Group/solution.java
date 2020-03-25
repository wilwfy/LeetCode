/*
 * Other's iteration solution
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if ((head == null) || (head.next == null)) return head;
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy, cur = dummy, tail = dummy;
        while (true) {
            //ListNode start = pre.next, end = pre.next;
            int cnt = k;
            while ((cnt > 0) && (tail != null)) {
                tail = tail.next;
                cnt--;
            }
            if (tail == null) break; // has reached the end of list
            //if (cnt > 0) break;
            
            // new head for next cycle after reversion
            head = pre.next;
                
            while (pre.next != tail) {
                cur = pre.next;
                pre.next = cur.next;
                cur.next = tail.next;
                tail.next = cur;
            }
            
            // prepare for next cycle
            pre = head;
            tail = head;
        }
        return dummy.next;
    }
}


/*
 * Other's recursion solution
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode curr = head;
        int count = 0;
        while (curr != null && count != k) { // find the k+1 node
            curr = curr.next;
            count++;
        }
        if (count == k) { // if k+1 node is found
            curr = reverseKGroup(curr, k); // reverse list with k+1 node as head
            // head - head-pointer to direct part, 
            // curr - head-pointer to reversed part;
            while (count-- > 0) { // reverse current k-group: 
                ListNode tmp = head.next; // tmp - next head in direct part
                head.next = curr; // preappending "direct" head to the reversed list 
                curr = head; // move head of reversed part to a new node
                head = tmp; // move "direct" head to the next node in direct part
            }
            head = curr; // move head back to the end node of current k-group
        }
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
