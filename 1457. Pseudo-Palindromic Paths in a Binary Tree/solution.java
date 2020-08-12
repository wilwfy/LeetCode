/**
 * Other's solution of Recursion
 *
 * Intuition
 * The necessar and suffisant condition of pseudo-palindromic,
 * is that at most one digit has odd occurrence.
 * 
 * 
 * Solution 1: Use Array
 * Recursively iterate all paths from root to leaves,
 * count the occurrence of each digits in an array.
 * At the leaf node, check if at most one digit has odd occurrence.
 * 
 * Time O(NK) or O(N)
 * Space O(K + H)
 * where K is the number of different elements,
 * H is the height.
 * In this problem, K = 9
 * 
 * 
 * Solution 2: Use HashSet
 * Recursively iterate all paths from root to leaves,
 * count the occurrence of each digits in an hashset.
 * 
 * Whenever meet an element, toggle it in the set:
 * If set contains it, remove it.
 * If set donesn't contain it, add it.
 * 
 * At the leaf node, check if the size of set <= 1.
 * 
 * Time O(N)
 * Space O(K + H)
 * 
 * 
 * Solution 3: Use an integer
 * Recursively iterate all paths from root to leaves,
 * count the occurrence of each digits in an integer.
 * 
 * Use this integer as a bit mask.
 * Also c++, we can use bitmask directly.
 * 
 * Whenever meet an element,
 * toggle the corresponding bit using ^ operation.
 * 
 * At the leaf node,
 * check if the count has only one bit that is 1.
 * 
 * We use lowbit to help count this.
 * Google it if you don't know.
 * 
 * Time O(N)
 * Space O(K + H)
 */
class Solution {
    public int pseudoPalindromicPaths (TreeNode root) {
        return dfs(root, 0);
    }
    
    public int dfs(TreeNode node, int count) {
        if (node == null) return 0;
        count ^= 1 << (node.val - 1); // Flips the node.val-1th bit, which here is used to flip between denoting even and odd occurrence.
        int res = dfs(node.left, count) + dfs(node.right, count);
        if (node.left == node.right && (count & (count - 1)) == 0) res++; // count & (count - 1) -> Used to check that only a single bit is set
        return res;
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
