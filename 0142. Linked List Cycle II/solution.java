/**
 * Other's solution of Two Pointers
 *
 * using two pointers, one of them one step at a time. another pointer each take two steps.
 * Suppose the first meet at step k,the length of the Cycle is r. so..2k-k=nr, k=nr
 * 
 * Now, the distance between the start node of list and the start node of cycle is s. the
 * distance between the start of list and the first meeting node is k(the pointer which wake
 * one step at a time waked k steps).the distance between the start node of cycle and the
 * first meeting node is m, so...s=k-m,
 * s=nr-m=(n-1)r+(r-m),here we takes n = 1..so, using one pointer start from the start node
 * of list, another pointer start from the first meeting node, all of them wake one step at
 * a time, the first time they meeting each other is the start of the cycle.
 * 
 * 
 * To understand this solution, you just need to ask yourself these question.
 * Assume the distance from head to the start of the loop is x1
 * the distance from the start of the loop to the point fast and slow meet is x2
 * the distance from the point fast and slow meet to the start of the loop is x3
 * What is the distance fast moved? What is the distance slow moved? And their relationship?
 * 
 * x1 + x2 + x3 + x2
 * x1 + x2
 * x1 + x2 + x3 + x2 = 2 (x1 + x2)
 * Thus x1 = x3
 * Finally got the idea.
 * 
 * 
 * Suppose the first meet at step k,the distance between the start node of list and the start
 * node of cycle is s, and the distance between the start node of cycle and the first meeting
 * node is m. Then 2k = (s + m + n1r) = 2(s + m + n2r) ==> s + m = nr. Steps moving from start
 * node to the start of the cycle is just s, moving from m by s steps would be the start of the
 * cycle, covering n cycles. In other words, they meet at the entry of cycle.
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        
        ListNode first = head, second = head;
        boolean isCycle = false;
        
        while (first != null && second != null) {
            first = first.next;
            if (second.next == null) return null;
            second = second.next.next;
            if (first == second) {
                isCycle = true;
                break;
            }
        }
        
        if (!isCycle) return null;
        first = head;
        while (first != second) {
            first = first.next;
            second = second.next;
        }
        return first;
    }
}


 /**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
