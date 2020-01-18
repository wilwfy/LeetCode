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


/*
 * Other's solution with using two pointers
 */
public static ListNode rotateRight(ListNode head, int k) {
	if(head==null)
        return null;
	int size = 1; // since we are already at head node
	ListNode fast=head;
	ListNode slow = head;
	
	while(fast.next!=null){
	    size++;
	    fast = fast.next;
	}
	
	for(int i=size-k%size;i>1;i--) // i>1 because we need to put slow.next at the start.
		slow = slow.next;
	
    // No dummy variable.
	fast.next = head;
	head = slow.next;
	slow.next = null;
	
	return head;
}


/*
 * Other's solution with using only one pointer
 */
public ListNode rotateRight(ListNode head, int k) {
    if(head == null || k == 0) {
        return head;
    }
    ListNode p = head;
    int len = 1;
    while(p.next != null) {
        p = p.next;
        len++;
    }
    p.next = head;
    k %= len;
    for(int i = 0; i < len - k; i++) {
        p = p.next;
    }
    head = p.next;
    p.next = null;
    return head;
}


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
