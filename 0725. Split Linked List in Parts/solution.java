/*
 * My solution of Split Input List
 */
class Solution {
    public ListNode[] splitListToParts(ListNode root, int k) {
        if (k == 1) return new ListNode[]{root};
        
        ListNode dummy = new ListNode(0);
        dummy.next = root;
        int length = 0;
        while (root != null) {
            length++;
            root = root.next;
        }
        
        ListNode[] res = new ListNode[k];
        int len = length / k, extra = length % k;
        root = dummy.next;
        for (int i = 0; i < k; i++) {
            //System.out.println("extra = " + extra);
            res[i] = root;
            int cnt = 1;
            if (len > 0) {
                while (cnt++ < len) {
                    //System.out.println(root.val);
                    root = root.next;
                }
                if (extra-- > 0) root = root.next;
                ListNode tmp = root;
                root = root.next;
                tmp.next = null;
            } else { // the length of original list is smaller than k
                ListNode tmp = root;
                if (root != null) {
                    root = root.next;
                    tmp.next = null;
                }
            }

        }
        return res;
    }
}


/*
 * Official solution of Split Input List
 *
 * Time Complexity: O(N+k), where N is the number of nodes in the given list. If k is large, it could still require
 *                  creating many new empty lists.
 * Space Complexity: O(k), the additional space used in writing the answer.
 */
class Solution {
    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode cur = root;
        int N = 0;
        while (cur != null) {
            cur = cur.next;
            N++;
        }

        int width = N / k, rem = N % k;

        ListNode[] ans = new ListNode[k];
        cur = root;
        for (int i = 0; i < k; ++i) {
            ListNode head = cur;
            for (int j = 0; j < width + (i < rem ? 1 : 0) - 1; ++j) {
                if (cur != null) cur = cur.next;
            }
            if (cur != null) {
                ListNode prev = cur;
                cur = cur.next;
                prev.next = null;
            }
            ans[i] = head;
        }
        return ans;
    }
}


/*
 * Other's solution of Split Input List
 */
class Solution {
    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] parts = new ListNode[k];
        int len = 0;
        for (ListNode node = root; node != null; node = node.next)
            len++;
        int n = len / k, r = len % k; // n : minimum guaranteed part size; r : extra nodes spread to the first r parts;
        ListNode node = root, prev = null;
        for (int i = 0; node != null && i < k; i++, r--) {
            parts[i] = node;
            for (int j = 0; j < n + (r > 0 ? 1 : 0); j++) {
                prev = node;
                node = node.next;
            }
            prev.next = null;
        }
        return parts;        
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
