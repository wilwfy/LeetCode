/**
 * Other's recursive solution with HashMap
 *
 * Intuition
 * The the basic idea is to take the last element in postorder array as the root, find the position of the root in the inorder array; then locate the range for left sub-tree
 * and right sub-tree and do recursion. Use a HashMap to record the index of root in the inorder array.
 */
class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length != postorder.length)
            return null;
        
        HashMap<Integer, Integer> hm = new HashMap<Integer,Integer>();
        for (int i=0;i<inorder.length;++i)
            hm.put(inorder[i], i);
        return buildTreePostIn(inorder, 0, inorder.length-1, postorder, 0, 
                          postorder.length-1,hm);
    }
    
    // is -- current start index in inorder array
    // ie -- current end index in inorder array
    // ps -- current start index in postorder array
    // pe -- current end index in postorder array
    // ri -- the proceeded root node index in inorder array
    private TreeNode buildTreePostIn(int[] inorder, int is, int ie, int[] postorder, int ps, int pe, 
                                 HashMap<Integer,Integer> hm){
    	if (ps>pe || is>ie) return null;
    	TreeNode root = new TreeNode(postorder[pe]);
    	int ri = hm.get(postorder[pe]);
      
      // the possible index range for leftchild:
      // in inorder array: is ~ ri-1,  in postorder array: ps ~ ps+(ri-is)-1
    	TreeNode leftchild = buildTreePostIn(inorder, is, ri-1, postorder, ps, ps+ri-is-1, hm);
      
      // the possible index range for rightchild
      // in inorder array:  ri+1 ~ ie,  in postorder array: ps+(ri-is) ~ pe-1
    	TreeNode rightchild = buildTreePostIn(inorder,ri+1, ie, postorder, ps+ri-is, pe-1, hm);
      
    	root.left = leftchild;
    	root.right = rightchild;
    	return root;
    }
}


/**
 * Other's recursive solution without HashMap
 *
 * But its run time is longer than above solution with HashMap
 */
class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
      return helper(inorder, postorder, postorder.length - 1, 0, inorder.length - 1);    
    }
    
    // ppos -- the proceeded node index in postorder array
    // pii  -- the proceeded node index in inorder array
    // is   -- current start index in inorder array
    // ie   -- current end index in inorder array
    TreeNode helper(int[] inorder, int[] postorder, int ppos, int is, int ie){
      if(ppos >= postorder.length || is > ie) return null;
      TreeNode node = new TreeNode(postorder[ppos]);
      int pii = 0;
      for(int i = 0; i < inorder.length; i++){
        if(inorder[i] == postorder[ppos]) {
            pii = i;
            break;
        }  
      }
      node.left = helper(inorder, postorder, ppos - 1 - ie + pii, is, pii - 1);
      node.right = helper(inorder, postorder, ppos - 1 , pii + 1, ie);
      return node;
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
