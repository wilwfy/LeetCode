/**
 * Other's Recursive solution
 */
 class Solution {
    public List<List<String>> printTree(TreeNode root) {
        List<List<String>> result = new ArrayList<>();
        int row = getHight(root);
        int col = (int) Math.pow(2, row) - 1;
        List<String> ans = new ArrayList<>();
        for(int i = 0; i < col; i++)  ans.add("");
        for(int i = 0; i < row; i++)  result.add(new ArrayList<>(ans));
        populateResult(root, result, 0, row, 0, col - 1);
        return result;
    }
    
    public void populateResult(TreeNode root, List<List<String>> result, int curRow, int totalRow, int i, int j) {
        if(root == null || curRow == totalRow)  return;
        result.get(curRow).set((i + j) / 2, String.valueOf(root.val));
        populateResult(root.left, result, curRow + 1, totalRow, i, ((i + j) / 2) - 1);
        populateResult(root.right, result, curRow + 1, totalRow, ((i + j) / 2) + 1, j);
    }
    
    public int getHight(TreeNode root) {
        if(root == null)  return 0;
        return 1 + Math.max(getHight(root.left), getHight(root.right));
    }
}


/**
 * Official Recursive solution
 *
 * In every recursive call, we do as follows:
 * 
 *   1. If we've reached the end of the tree, i.e. if root==null, return.
 *   
 *   2. Determine the column in which the current element(rootroot) needs to be filled,
 *      which is the middle of ll and rr, given by say, jj. The row number is same as ii.
 *      Put the current element at res[i][j]res[i][j].
 *   
 *   3. Make the recursive call for the left child of the rootroot using fill(res, root.left, i + 1, l, (l + r) / 2).
 *   
 *   4. Make the recursive call for the right child of the rootroot using fill(res, root.right, i + 1, (l + r + 1) / 2, r).
 *
 * Time complexity : O(h * 2^h). We need to fill the resres array of size h * 2^h - 1. Here, h refers to
 *                   the height of the given tree.
 * Space complexity : O(h * 2^h). res array of size h * 2^h - 1 is used.
 */
class Solution {
    public List<List<String>> printTree(TreeNode root) {
        int height = getHeight(root);
        String[][] res = new String[height][(1 << height) - 1];
        for (String[] row: res)
            Arrays.fill(row, "");
        
        List<List<String>> ans = new ArrayList<>();
        myFill(res, root, 0, 0, res[0].length);
        for (String[] row: res)
            ans.add(Arrays.asList(row));
        return ans;
    }
    
    private void myFill(String[][] res, TreeNode node, int rowIdx, int start, int end) {
        if (node == null) return;
        res[rowIdx][(start + end) / 2] = "" + node.val;
        myFill(res, node.left, rowIdx + 1, start, (start + end) / 2);
        myFill(res, node.right, rowIdx + 1, (start + end + 1) / 2, end);
    }
    
    private int getHeight(TreeNode node) {
        if (node == null) return 0;
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }
}


/**
 * Official solution of Using Queue (BFS)
 *
 * We start by initializing a resres array as in the previous approach. After this, we add
 * the parametrized rootroot of the tree into a queuequeue. After this, we do the following
 * at every step.
 * 
 *   1. Remove an element, pp, from the front of the queuequeue.
 *   
 *   2. Add this element at its correct position in the resres array given by res[p.i][(p.l + p.r) / 2].
 *      Here, the values ii, ll and rr refer to the column/level number, and the left and right boundaries
 *      permissible for putting the current node into resres. These are obtained from the node's parameters,
 *      which have been associated with it before putting it into the queuequeue.
 *   
 *   3. If the left child of pp exists, put it at the back of the queuequeue, in a parametized form, by
 *      appropriately updating the level as the next level and the boundaries permissible as well.
 *   
 *   4. If the right child of pp exists, put it at the back of the queuequeue, in a parametized form, by
 *      appropriately updating the level as the next level and the boundaries permissible as well.
 *   
 *   5. Continue steps 1. to 4. till the queuequeue becomes empty.
 *
 * Time complexity : O(h * 2^h). We need to fill the resres array of size h * 2^h - 1. Here, h refers to
 *                   the height of the given tree.
 * Space complexity : O(h * 2^h). res array of size h * 2^h - 1 is used.
 */
public class Solution {
    class Params {
        Params(TreeNode n, int ii, int ll, int rr) {
            root = n;
            i = ii;
            l = ll;
            r = rr;
        }
        TreeNode root;
        int i, l, r;
    }
    
    public List < List < String >> printTree(TreeNode root) {
        int height = getHeight(root);
        System.out.println(height);
        String[][] res = new String[height][(1 << height) - 1];
        for (String[] arr: res)
            Arrays.fill(arr, "");
        List < List < String >> ans = new ArrayList < > ();
        fill(res, root, 0, 0, res[0].length);
        for (String[] arr: res)
            ans.add(Arrays.asList(arr));
        return ans;
    }
    
    public void fill(String[][] res, TreeNode root, int i, int l, int r) {
        Queue < Params > queue = new LinkedList();
        queue.add(new Params(root, 0, 0, res[0].length));
        while (!queue.isEmpty()) {
            Params p = queue.remove();
            res[p.i][(p.l + p.r) / 2] = "" + p.root.val;
            if (p.root.left != null)
                queue.add(new Params(p.root.left, p.i + 1, p.l, (p.l + p.r) / 2));
            if (p.root.right != null)
                queue.add(new Params(p.root.right, p.i + 1, (p.l + p.r + 1) / 2, p.r));
        }
    }
    
    public int getHeight(TreeNode root) {
        Queue < TreeNode > queue = new LinkedList();
        queue.add(root);
        int height = 0;
        while (!queue.isEmpty()) {
            height++;
            Queue < TreeNode > temp = new LinkedList();
            while (!queue.isEmpty()) {
                TreeNode node = queue.remove();
                if (node.left != null)
                    temp.add(node.left);
                if (node.right != null)
                    temp.add(node.right);
            }
            queue = temp;
        }
        return height;
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
