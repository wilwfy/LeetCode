/**
 * My solution with Stack
 *
 * Time: O(n) for initialization. O(1) for next() and hasNext().
 * Space: O(n) for stack.
 */
class BSTIterator {
    Stack<TreeNode> stackBST;
    public BSTIterator(TreeNode root) {
        stackBST = new Stack<>();
        dfs(root);
    }
    
    private void dfs(TreeNode node) {
        if (node == null) return;
        dfs(node.right);
        stackBST.push(node);
        dfs(node.left);
    }
    
    /** @return the next smallest number */
    public int next() {
        return stackBST.pop().val;
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stackBST.isEmpty();
    }
}


/**
 * Other's solution with Stack
 *
 * Use Stack to store directed left children from root.
 * When next() be called, just pop one element and process its right child as new root.
 * The code is pretty straightforward.
 * 
 * So this can satisfy O(h) memory, hasNext() in O(1) time,
 * But next() is O(h) time.
 * 
 * I can't find a solution that can satisfy both next() in O(1) time, space in O(h).
 */
class BSTIterator {
    private Stack<TreeNode> stack = new Stack<TreeNode>();
    
    public BSTIterator(TreeNode root) {
        pushAll(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode tmpNode = stack.pop();
        pushAll(tmpNode.right);
        return tmpNode.val;
    }
    
    private void pushAll(TreeNode node) {
        for (; node != null; stack.push(node), node = node.left);
    }
}


/**
 * Official solution of Flattening the BST with List
 *
 * Time complexity : O(N) is the time taken by the constructor for the iterator. The problem statement only asks
 *                   us to analyze the complexity of the two functions, however, when implementing a class, it's
 *                   important to also note the time it takes to initialize a new object of the class and in this
 *                   case it would be linear in terms of the number of nodes in the BST. In addition to the space
 *                   occupied by the new array we initialized, the recursion stack for the inorder traversal also
 *                   occupies space but that is limited to O(h) where hh is the height of the tree.
 *                    -- next() would take O(1)
 *                    -- hasNext() would take O(1)
 * Space complexity : O(N) since we create a new array to contain all the nodes of the BST. This doesn't comply
 *                    with the requirement specified in the problem statement that the maximum space complexity
 *                    of either of the functions should be O(h) where hh is the height of the tree and for a well
 *                    balanced BST, the height is usually logNlogN. So, we get great time complexities but we
 *                    had to compromise on the space. Note that the new array is used for both the function calls
 *                    and hence the space complexity for both the calls is O(N).
 */
class BSTIterator {

    ArrayList<Integer> nodesSorted;
    int index;

    public BSTIterator(TreeNode root) {
        // Array containing all the nodes in the sorted order
        this.nodesSorted = new ArrayList<Integer>();
        
        // Pointer to the next smallest element in the BST
        this.index = -1;
        
        // Call to flatten the input binary search tree
        this._inorder(root);
    }

    private void _inorder(TreeNode root) {
        if (root == null) {
            return;
        }

        this._inorder(root.left);
        this.nodesSorted.add(root.val);
        this._inorder(root.right);
    }

    /** @return the next smallest number */
    public int next() {
        return this.nodesSorted.get(++this.index);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return this.index + 1 < this.nodesSorted.size();
    }
}


/**
 * Official solution of Controlled Recursion with Stack
 *
 * Time complexity : Let's look at the complexities for both the functions in the class:
 *                   -- hasNext is the easier of the lot since all we do in this is to return true if there are any elements left
 *                      in the stack. Otherwise, we return false. So clearly, this is an O(1) operation every time. Let's look at
 *                      the more complicated function now to see if we satisfy all the requirements in the problem statement
 *                   -- next involves two major operations. One is where we pop an element from the stack which becomes the next
 *                      smallest element to return. This is a O(1) operation. However, we then make a call to our helper function
 *                      _inorder_left which iterates over a bunch of nodes. This is clearly a linear time operation i.e. O(N) in the
 *                      worst case. This is true.
 * Space complexity: The space complexity is O(h) which is occupied by our custom stack for simulating the inorder traversal.
 */
class BSTIterator {

    Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        // Stack for the recursion simulation
        this.stack = new Stack<TreeNode>();
        
        // Remember that the algorithm starts with a call to the helper function
        // with the root node as the input
        this._leftmostInorder(root);
    }

    private void _leftmostInorder(TreeNode root) {
        // For a given node, add all the elements in the leftmost branch of the tree
        // under it to the stack.
        while (root != null) {
            this.stack.push(root);
            root = root.left;
        }
    }

    /** @return the next smallest number */
    public int next() {
        // Node at the top of the stack is the next smallest element
        TreeNode topmostNode = this.stack.pop();

        // Need to maintain the invariant. If the node has a right child, call the 
        // helper function for the right child
        if (topmostNode.right != null) {
            this._leftmostInorder(topmostNode.right);
        }

        return topmostNode.val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return this.stack.size() > 0;
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
 
/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
