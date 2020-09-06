/**
 * My Recursive solution of Inorder Traverse and Merger
 *
 * Time: O(N). N is the total number of the nodes in both trees
 * Space: O(N).
 */
class Solution {
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> tree1Values = new ArrayList<>();
        List<Integer> tree2Values = new ArrayList<>();
        inorderTraverse(root1, tree1Values);
        inorderTraverse(root2, tree2Values);
        
        List<Integer> res = new ArrayList<>();
        int i = 0, j = 0;
        while (i < tree1Values.size() && j < tree2Values.size()) {
            if (tree1Values.get(i) <= tree2Values.get(j)) {
                res.add(tree1Values.get(i));
                i++;
            } else {
                res.add(tree2Values.get(j));
                j++;
            }
        }
        if (i == tree1Values.size()) {
            while (j < tree2Values.size())
                res.add(tree2Values.get(j++));
        }
        if (j == tree2Values.size()) {
            while (i < tree1Values.size())
                res.add(tree1Values.get(i++));
        }
        return res;
    }
    
    private void inorderTraverse(TreeNode node, List<Integer> valueList) {
        if (node == null) return;
        
        inorderTraverse(node.left, valueList);
        valueList.add(node.val);
        inorderTraverse(node.right, valueList);
    }
}


/**
 * Other's Recursive solution of Inorder Traverse and Merger
 *
 * Time: O(N). N is the total number of the nodes in both trees
 * Space: O(N).
 */
class Solution {
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList();
        inorder(root1, list1);
        List<Integer> list2 = new ArrayList();
        inorder(root2, list2);
        return mergeList(list1, list2);
    }
    
    private void inorder(TreeNode root, List<Integer> list) {
        if(root == null) 
            return;
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }
    
    private List<Integer> mergeList(List<Integer> list1, List<Integer> list2) {
        List<Integer> list = new ArrayList();
        int i = 0, j = 0;
        while(i < list1.size() && j < list2.size()) {
            if(list1.get(i) < list2.get(j)) list.add(list1.get(i++));
            else list.add(list2.get(j++));
        }
        
        while(i < list1.size()) list.add(list1.get(i++));
        while(j < list2.size()) list.add(list2.get(j++));
        
        return list;
    }
}


/**
 * Other's Iterative solution
 *
 * Time: O(N). N is the total number of the nodes in both trees
 * Space: O(N).
 */
class Solution {
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> st1 = new Stack<>();
        Stack<TreeNode> st2 = new Stack<>();
        
        leftSubtree(root1, st1);
        leftSubtree(root2, st2);
        
        while (!st1.isEmpty() && !st2.isEmpty()) {
            TreeNode node1 = st1.peek();
            TreeNode node2 = st2.peek();
            if (node1.val < node2.val) {
                res.add(node1.val);
                st1.pop();
                leftSubtree(node1.right, st1);
            } else {
                res.add(node2.val);
                st2.pop();
                leftSubtree(node2.right, st2);
            }
        }
        
        while (!st1.isEmpty()) {
            TreeNode node1 = st1.pop();
            res.add(node1.val);
            leftSubtree(node1.right, st1);
        }
        
        while (!st2.isEmpty()) {
            TreeNode node2 = st2.pop();
            res.add(node2.val);
            leftSubtree(node2.right, st2);
        }
        
        return res;
    }
    
    private void leftSubtree(TreeNode node, Stack<TreeNode> st) {
        if (node == null) return;
        st.add(node);
        leftSubtree(node.left, st);
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
