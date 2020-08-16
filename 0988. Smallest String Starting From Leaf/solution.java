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
 * Other's solution of DFS
 * 
 * Time Complexity: O(NlogN).
 * Space Complexity: O(N).
 */
class Solution {
    public String smallestFromLeaf(TreeNode root) {
        return dfs(root, "");
    }

    public String dfs(TreeNode node, String suffix) {
        if (node == null) return suffix;
        
        suffix = (char)('a' + node.val) + suffix;
        if (node.left == null && node.right == null) {
            return suffix;
        }
        if (node.left == null || node.right == null) {
            return (node.left == null) ? dfs(node.right, suffix) : dfs(node.left, suffix);
        }
        // Noly do the comparasion at node which has both children, because:
        // string X < string Y
        // doesn't guarantee
        // X + a < Y + a
        // where a is a character. e.g.: 
        // "ab" < "abab", but "abz" > "ababz"
        String leftStr = dfs(node.left, suffix);
        String rightStr = dfs(node.right, suffix);
        return leftStr.compareTo(rightStr) <= 0 ? leftStr : rightStr;
    }
}


/**
 * Another's solution of DFS with global variable
 * 
 * Time Complexity: O(NlogN).
 * Space Complexity: O(N).
 */
class Solution {
    private String ans = "~"; // dummy value '~' > 'z'
    
    public String smallestFromLeaf(TreeNode root) {
        return dfs(root, "");
    }

    public String dfs(TreeNode node, String suffix) {
        if (node == null) return suffix; // base case, and in case root is null.
        suffix = (char)('a' + node.val) + suffix; // prepend current char to the path string from root.
        if (node.left == null && node.right == null && ans.compareTo(suffix) > 0)
            ans = suffix; // update ans if node is a leaf.
        dfs(node.left, suffix); // recursion to the left branch.
        dfs(node.right, suffix); // recursion to the right branch.
        return ans;
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
