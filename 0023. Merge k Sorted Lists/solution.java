/*
 * Solution of Brute force
 *
 * Time complexity : O(NlogN) where N is the total number of nodes.
 *                   Collecting all the values costs O(N) time.
 *                   A stable sorting algorithm costs O(NlogN) time.
 *                   Iterating for creating the linked list costs O(N) time.
 * Space complexity : O(N).
 *                    Sorting cost O(N) space (depends on the algorithm you choose).
 *                    Creating a new linked list costs O(N) space.
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        
        // Brute force
        List<Integer> values = new ArrayList<>();
        for (ListNode list: lists) {
            while (list != null) {
                values.add(list.val);
                list = list.next;
            }
        }
        
        Collections.sort(values);
        
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        for (int value: values) {
            ListNode node = new ListNode(value);
            head.next = node;
            head = node;
        }
        head.next = null;
        return dummy.next;
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
