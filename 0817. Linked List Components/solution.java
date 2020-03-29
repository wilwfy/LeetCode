/**
 * Official solution with HashSet
 *
 * Time Complexity: O(N+G.length), where N is the length of the linked list with root node head.
 * Space Complexity: O(G.length), to store Gset.
 */
class Solution {
    public int numComponents(ListNode head, int[] G) {
        //if ((head == null) || (G.length == 0)) return 0;
        
        Set<Integer> set = new HashSet<>();
        for (int g: G) set.add(g);
        
        int res = 0;
        boolean connected = false;
        while (head != null) {
            if (set.contains(head.val))
                connected = true;
            else {
                if (connected) res++;
                connected = false;
            }
            head = head.next;
        }
        return connected ? res+1 : res;
    }
}


/**
 * Official solution with HashSet
 */
class Solution {
    public int numComponents(ListNode head, int[] G) {
        Set<Integer> setG = new HashSet<>();
        for (int i: G) setG.add(i);
        int res = 0;
        while (head != null) {
            if (setG.contains(head.val) && (head.next == null || !setG.contains(head.next.val))) res++;
            head = head.next;
        }
        return res;
    }
}

/**
 * My solution with Counting Sort
 */
class Solution {
    public int numComponents(ListNode head, int[] G) {
        if ((head == null) || (G.length == 0)) return 0;
        
        // value range is [0, N - 1] and 1 <= N <= 10000
        int[] vals = new int[10000];
        
        for (int g: G) vals[g]++;
        
        int res = 0;
        boolean connected = false;
        while (head != null) {
            if (vals[head.val] != 0)
                connected = true;
            else {
                if (connected) res++;
                connected = false;
            }
            head = head.next;
        }
        return connected ? res+1 : res;
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
