/**
 * Other's solution of Divide-and-conquer
 *
 * Recursively found the possible children of each node at each level. At every level
 * subtract 1 from N since this node counts as a node. (N is given as 1 to 50) If N == 1,
 * return a node with no children. For every i where i is the number of left children,
 * call allPossibleFBT(i) and allPossibleFBT(N-i) for the remaining children on the opposite
 * side. Then, iterate through all possible combinations of children setting the current node's
 * left and right children, and add it to the result list.
 */
class Solution {
    public List<TreeNode> allPossibleFBT(int N) {
        //1. if N = 3 , the number of nodes combination are as follows
        //      left    root    right
        //       1       1        1 
        //--------------N = 3, res = 1----------
        
        //2. if N = 5 , the number of nodes combination are as follows
        //      left    root    right
        //       1       1        3 (recursion)
        //       3       1        1 
        //  --------------N = 5, res = 1 + 1 = 2----------
        
        //3. if N = 7 , the number of nodes combination are as follows
        //      left    root    right
        //       1       1        5 (recursion)
        //       3       1        3 
        //       5       1        1
        //  --------------N = 7, res = 2 + 1 + 2 = 5----------
        
        //4. in order to make full binary tree, the node number must increase by 2
        List<TreeNode> res = new ArrayList<>();
        if (N == 1) {
            res.add(new TreeNode(0));
            return res;
        }
        
        N--;
        for (int i = 1; i < N; i += 2) {
            List<TreeNode> left = allPossibleFBT(i);
            List<TreeNode> right = allPossibleFBT(N - i);
            for (TreeNode nl: left) {
                for (TreeNode nr: right) {
                    TreeNode cur = new TreeNode(0);
                    cur.left = nl;
                    cur.right = nr;
                    res.add(cur);
                }
            }
        }
        return res;
    }
}


/**
 * Other's solution of Divide-and-conquer with cache
 *
 * We can also add a cache so we don't repeat N values, and return an empty set if N is even
 * because we can't make a full binary tree with an even number of nodes. (Don't have to create
 * a copy, we can use the same list).
 */
class Solution {
    Map<Integer,List<TreeNode>> cache = new HashMap<>();
    
    public List<TreeNode> allPossibleFBT(int N) {
        List<TreeNode> res = new ArrayList<>();
        if (N % 2 == 0) return res;
        if (cache.containsKey(N)) return cache.get(N);
        if (N == 1) {
            res.add(new TreeNode(0));
            return res;
        }
        
        N = N - 1;
        for (int i = 1; i < N; i += 2) {
            List<TreeNode> left = allPossibleFBT(i);
            List<TreeNode> right = allPossibleFBT(N-i);
            for (TreeNode nl: left) {
                for (TreeNode nr:right) {
                    TreeNode cur = new TreeNode(0);
                    cur.left = nl;
                    cur.right = nr;
                    res.add(cur);
                }
            }
        }
        cache.put(N, res);
        return res;
    }
}


/**
 * Other's solution of DP
 */
class Solution {   
    public List<TreeNode> allPossibleFBT(int N) {
        if (N <= 0) return new ArrayList<>();
        
        List<TreeNode>[] dp = new ArrayList[N + 1]; // dp[i] is the root node list of all possible FBT for integer i
        for (int i = 0; i <= N; i++) {
            dp[i] = new ArrayList<>();
        }
        dp[1].add(new TreeNode(0));
        
        for (int numNode = 1; numNode <= N; numNode += 2) {
            for (int leftNode = 1; leftNode < numNode; leftNode += 2) {
                for (TreeNode left: dp[leftNode]) {
                    for (TreeNode right: dp[numNode - 1 - leftNode]) {
                        TreeNode root = new TreeNode(0);
                        root.left = left;
                        root.right = right;
                        dp[numNode].add(root);
                    }
                }
            }
        }
        return dp[N];
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
