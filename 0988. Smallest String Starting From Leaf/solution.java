/**
 * Official solution of Brute Force
 *
 * Time Complexity: We use O(N) to traverse the array and maintain A [Python] or sb. Then, our reversal and comparison with the previous
 *                  answer is O(L), where L is the size of the string we have when at the leaf. For example, for a perfectly balanced
 *                  tree, L = logN and the time complexity would be O(NlogN).
 * Space Complexity: O(N).
 */
class Solution {
    String ans = "~";
    public String smallestFromLeaf(TreeNode root) {
        dfs(root, new StringBuilder());
        return ans;
    }

    public void dfs(TreeNode node, StringBuilder sb) {
        if (node == null) return;
        sb.append((char)('a' + node.val));

        if (node.left == null && node.right == null) {
            sb.reverse();
            String S = sb.toString();
            sb.reverse();

            if (S.compareTo(ans) < 0)
                ans = S;
        }

        dfs(node.left, sb);
        dfs(node.right, sb);
        sb.deleteCharAt(sb.length() - 1);
    }
}


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
