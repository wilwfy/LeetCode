/*
 * If allowed, don't try sorting a linked list during the interview !
 *        http://steve-yegge.blogspot.nl/2008/03/get-that-job-at-google.html
 * So it might be better to actually copy the values into an array and sort them there.
 */


/*
 * My solution with extra space
 *
 * Time: O(n^2)
 * Space: O(n)
 */
class Solution {
    public ListNode insertionSortList(ListNode head) {
        if ((head == null) || (head.next == null)) return head;
        
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        ListNode cur = dummy;
        while (head != null) {
            ListNode node = new ListNode(head.val);
            if (head.val >= cur.val) {
                cur.next = node;
                cur = node;
            } else {
                ListNode start = dummy;
                while (start.next.val <= head.val) {
                    start = start.next;
                }
                node.next = start.next;
                start.next = node;
            }
            head = head.next;
        }
        return dummy.next;
    }
}


/*
 * My solution without extra space
 *
 * Time: O(n^2)
 * Space: O(1)
 */
class Solution {
    public ListNode insertionSortList(ListNode head) {
        if ((head == null) || (head.next == null)) return head;
        
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;
        ListNode prev = dummy, curr = head;
        while (curr != null) {
            if (curr.val >= prev.val) {
                prev = curr;
                curr = curr.next;
            } else {
                prev.next = curr.next;
                ListNode start = dummy;
                while (start.next.val <= curr.val) {
                    start = start.next;
                }
                curr.next = start.next;
                start.next = curr;
                curr = prev.next;
            }
        }
        return dummy.next;
    }
}


/*
 * Other's solution without extra space
 *
 * Time: O(n^2)
 * Space: O(1)
 */
class Solution {
    public ListNode insertionSortList(ListNode head) {
		if( head == null ){
			return head;
		}
		
		ListNode helper = new ListNode(0); //new starter of the sorted list
		ListNode cur = head; //the node will be inserted
		ListNode pre = helper; //insert node between pre and pre.next
		ListNode next = null; //the next node will be inserted
		//not the end of input list
		while( cur != null ){
			next = cur.next;
			//find the right place to insert
			while( pre.next != null && pre.next.val < cur.val ){
				pre = pre.next;
			}
			//insert between pre and pre.next
			cur.next = pre.next;
			pre.next = cur;
			pre = helper;
			cur = next;
		}
		
		return helper.next;
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
