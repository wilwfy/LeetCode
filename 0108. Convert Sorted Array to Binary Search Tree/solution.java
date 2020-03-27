/*
 * My recursion solution which is similar to Problem 109. Convert Sorted List to Binary Search Tree
 */
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        if ((nums == null) || (nums.length == 0)) return null;
        if (nums.length == 1) return new TreeNode(nums[0]);
        
        int mid = nums.length / 2;
        
        TreeNode node = new TreeNode(nums[mid]);
        node.left = sortedArrayToBST(Arrays.copyOfRange(nums, 0, mid));
        node.right = sortedArrayToBST(Arrays.copyOfRange(nums, mid+1, nums.length));
        return node;
    }
}


/*
 * Other's iteration solution with Stack
 */
class Solution {
    public class MyNode{
        TreeNode node;
        int start;
        int end;
        
        public MyNode(int start, int end, TreeNode node){
            this.start = start;
            this.end = end;
            this.node = node;
        }
    }
    
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums.length ==0 ) return null;
        
        Stack<MyNode> stack = new Stack<MyNode>();
        int mid = 0 + (nums.length -1 - 0)/2;
        TreeNode root = new TreeNode(nums[mid]);
        MyNode MyRoot = new MyNode(0, nums.length -1, root);
        stack.push(MyRoot);
        while(!stack.isEmpty()){
            MyNode curr = stack.pop();
            int oldMid = curr.start + (curr.end - curr.start)/2;
            if(oldMid -1 >= curr.start){
                mid = curr.start + (oldMid-1 - curr.start)/2;
                root = new TreeNode(nums[mid]);
                stack.push(new MyNode(curr.start, oldMid - 1, root));
                curr.node.left = root;
            }
            
            if(oldMid +1 <= curr.end){
                mid = oldMid +1 + (curr.end - oldMid -1)/2;
                root = new TreeNode(nums[mid]);
                stack.push(new MyNode(oldMid + 1, curr.end, root));
                curr.node.right = root;
            }    
        }
        
        return MyRoot.node;
    }
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
