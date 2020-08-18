/**
 * Other's solution of Brute DFS
 *
 * Time: O(N * min(L,H))
 * Space: O(H)
 * where N = tree size, H = tree height, L = list length.
 */
class Solution {
    public boolean isSubPath(ListNode head, TreeNode root) {
        if (head == null) return true;
        if (root == null) return false;
        return dfs(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right);
    }
    
    public boolean dfs(ListNode listNode, TreeNode treeNode) {
        if (listNode == null) return true;
        if (treeNode == null) return false;
        return (listNode.val == treeNode.val) && (dfs(listNode.next, treeNode.left) || dfs(listNode.next, treeNode.right));
    }
}


/**
 * Other's solution of DP
 *
 * Algorithm
 * Iterate the whole link, find the maximum matched length of prefix.
 * Iterate the whole tree, find the maximum matched length of prefix.
 *
 * a link of reference:
 * https://en.wikipedia.org/wiki/Knuth–Morris–Pratt_algorithm
 *
 * Time: O(N)
 * Space: O(L + H)
 * where N = tree size, H = tree height, L = list length.
 */
class Solution {
    public boolean isSubPath(ListNode head, TreeNode root) {
        // This DP solution can be referred to the reference (KMP algorithm for pattern search):
        // https://en.wikipedia.org/wiki/Knuth–Morris–Pratt_algorithm
        List<Integer> A = new ArrayList(), dp = new ArrayList();
        A.add(head.val);
        dp.add(0);
        int i = 0;
        head = head.next;
        // Iterate the whole link, find the maximum matched length of prefix.
        while (head != null) {
            while (i > 0 && head.val != A.get(i))
                i = dp.get(i - 1);
            if (head.val == A.get(i)) i++;
            A.add(head.val);
            dp.add(i);
            head = head.next;
        }
        // Iterate the whole tree, find the maximum matched length of prefix.
        return dfs(root, 0, A, dp);
    }
    
    private boolean dfs(TreeNode root, int i, List<Integer> A, List<Integer> dp) {
        if (root == null) return false;
        while (i > 0 && root.val != A.get(i))
            i = dp.get(i - 1);
        if (root.val == A.get(i)) i++;
        return (i == dp.size()) || dfs(root.left, i, A, dp) || dfs(root.right, i, A, dp);
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
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
