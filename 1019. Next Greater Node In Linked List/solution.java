/*
 * Other's solution with Stack
 *
 * Transform the linked list to an arraylist, then it's a normal "next larger element" problem, solved by stack.
 *
 * Time: O(n)
 * Space: O(n)
 */
class Solution {
    public int[] nextLargerNodes(ListNode head) {
        if (head == null) return new int[]{};
        
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int[] res = new int[list.size()];
        
        Stack<Integer> stack = new Stack<Integer>(); // stack of index
        for (int i = 0; i < list.size(); i++) {
            while (!stack.isEmpty() && (list.get(stack.peek()) < list.get(i))) {
                res[stack.pop()] = list.get(i);
            }
            stack.push(i);
        }

        return res;
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
