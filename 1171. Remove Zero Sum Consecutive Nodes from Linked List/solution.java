/*
 * My solution by referring to following other's solution
 */
class Solution {
    public ListNode removeZeroSumSublists(ListNode head) {
        if (head == null) return head;

        Map<Integer, ListNode> map = new HashMap<>();
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        int preSum = 0;
        while (cur != null) {
            preSum += cur.val;
            if (map.containsKey(preSum)) {
                map.get(preSum).next = cur.next;
                int sum = preSum - cur.val;
                while (sum != preSum) {
                    sum = sum - map.remove(sum).val;
                }
            } else {
                map.put(preSum, cur);
            }
            cur = cur.next;
        }
        return dummy.next;
    }
}


/*
 * Other's solution of Greedily Skip with HashMap and One Pass
 *
 * Intuition:
 *   Assume the input is an array.
 *   Do you know how to solve it?
 *   Scan from the left, and calculate the prefix sum.
 *   Whenever meet the seen prefix,
 *   remove all elements of the subarray between them.
 * 
 * 
 * Solution 1:
 *   Because the head ListNode can be removed in the end,
 *   I create a dummy ListNode and set it as a previous node of head.
 *   prefix calculates the prefix sum from the first node to the current cur node.
 * 
 * Next step, we need an important hashmap m (no good name for it),
 * It takes a prefix sum as key, and the related node as the value.
 * 
 * Then we scan the linked list, accumulate the node's value as prefix sum.
 *   1. If it's a prefix that we've never seen, we set m[prefix] = cur.
 *   2. If we have seen this prefix, m[prefix] is the node we achieve this prefix sum.
 *      We want to skip all nodes between m[prefix] and cur.next (exclusive).
 *      So we simplely do m[prefix].next = cur.next.
 * We keep doing these and it's done.
 * 
 * Complexity:
 * Time O(N), one pass
 * SpaceO(N), for hashmap
 */
class Solution {
    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode dummy = new ListNode(0), cur = dummy;
        dummy.next = head;
        int prefix = 0;
        Map<Integer, ListNode> m = new HashMap<>();
        while (cur != null) {
            prefix += cur.val;
            if (m.containsKey(prefix)) {
                cur =  m.get(prefix).next;
                int p = prefix + cur.val;
                while (p != prefix) {
                    m.remove(p);
                    cur = cur.next;
                    p += cur.val;
                }
                m.get(prefix).next = cur.next;
            } else {
                m.put(prefix, cur);
            }
            cur = cur.next;
        }
        return dummy.next;
    }
}

/*
 * Other's solution of Greedily Skip with HashMap and Two Pass
 *
 * Performance is improved
 *
 * If we don't insist on one pass, we can find the two passes is actually really neat.
 * 
 * That turned back to the intuition that I mentioned:
 *   Assume the input is an array.
 * How will you solve the problem?
 * 
 * Iterate for the first time,
 *   calculate the prefix sum,
 *   and save the it to seen[prefix]
 * 
 * Iterate for the second time,
 *   calculate the prefix sum,
 *   and directly skip to last occurrence of this prefix
 */
class Solution {
    public ListNode removeZeroSumSublists(ListNode head) {
        int prefix = 0;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        Map<Integer, ListNode> seen = new HashMap<>();
        seen.put(0, dummy);
        for (ListNode i = dummy; i != null; i = i.next) {
            prefix += i.val;
            seen.put(prefix, i); // Now the rightmost node with the same prefix is in the Map
        }
        prefix = 0;
        for (ListNode i = dummy; i != null; i = i.next) {
            prefix += i.val;
            i.next = seen.get(prefix).next; // The leftmost node gets to be connected to the next node of
                                            // the rightmost node who has the same prefix
        }
        return dummy.next;
    }
}


/*
 * Other's solution without HashMap:       https://youtu.be/tss5biw6ctI
 */


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
