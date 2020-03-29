/*
 * My solution with Stack
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> st1 = new Stack<>();
        Stack<Integer> st2 = new Stack<>();
        while (l1 != null) {
            st1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            st2.push(l2.val);
            l2 = l2.next;
        }
        int[] values = new int[Math.max(st1.size(), st2.size())+1];
        int idx = values.length - 1, carry= 0, sum = 0;
        while (!st1.empty() && !st2.empty()) {
            sum = st1.pop() + st2.pop() + carry;
            values[idx] = sum % 10;
            carry = sum / 10;
            idx--;
        }
        if (st1.empty() && st2.empty()) values[idx] = carry; // The idx is 0 now
        else if (!st1.empty()) addHelper(st1, values, idx, carry);
        else if (!st2.empty()) addHelper(st2, values, idx, carry);
        
        ListNode dummy = new ListNode(-1);
        ListNode node = dummy;
        for (int val: values) {
            ListNode next = new ListNode(val);
            node.next = next;
            node = next;
        }
        if (dummy.next.val != 0) return dummy.next;
        else return dummy.next.next;
    }
    
    private int[] addHelper(Stack<Integer> stack, int[] a, int idx, int carry) {
        while (!stack.empty()) {
            int sum = stack.pop() + carry;
            a[idx] = sum % 10;
            carry = sum / 10;
            idx--;
        }
        a[idx] = carry;
        return a;
    }
}


/*
 * Other's solution with Stack
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<ListNode> l1Stack = new Stack<>();
        
        while(l1 != null) {
            l1Stack.push(l1);
            l1 = l1.next;
        }
        
        Stack<ListNode> l2Stack = new Stack<>();
        
        while(l2 != null) {
            l2Stack.push(l2);
            l2 = l2.next;
        }
        
        int carry = 0;
        ListNode dummy = null;
        
        while(!l1Stack.isEmpty() || !l2Stack.isEmpty() ||  carry != 0) {
            int f = l1Stack.isEmpty() ? 0 : l1Stack.pop().val;
            int s = l2Stack.isEmpty() ? 0 : l2Stack.pop().val;
            
            int t = f + s + carry;
            
            ListNode newNode = new ListNode(t % 10);
            
            newNode.next = dummy;
            dummy = newNode;
            carry = t/10;
            
        }
        
        return dummy;
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
