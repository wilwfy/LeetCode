/**
 * My solution with Queue
 */
class CBTInserter {
    TreeNode root;
    Queue<TreeNode> insertableQue;

    public CBTInserter(TreeNode root) {
        this.root = root;
        insertableQue = new LinkedList<>();
        Queue<TreeNode> nodeQue = new LinkedList<>();
        nodeQue.offer(root);
        
        // Store all the insertable nodes
        while (!nodeQue.isEmpty()) {
            TreeNode cur = nodeQue.poll();
            if (cur != null) {
                if (cur.left == null) {
                    // in a complete binary tree, if the left child is null then the right child must be null as well,
                    // the node is a leaf
                    insertableQue.offer(cur);
                } else {
                    nodeQue.offer(cur.left);
                    if (cur.right == null)
                        // in a complete binary tree, the node is insertable if its right child is null
                        insertableQue.offer(cur);
                    else
                        nodeQue.offer(cur.right);
                }
            }
        }
    }
    
    public int insert(int v) {
        TreeNode node = insertableQue.peek();
        if (node.left == null) {
            node.left = new TreeNode(v);
            insertableQue.offer(node.left);
        } else {
            node.right = new TreeNode(v);
            // remove this node from queue since it is not insertable any more
            insertableQue.poll();
            insertableQue.offer(node.right);
        }
        return node.val;
    }
    
    public TreeNode get_root() {
        return this.root;
    }
}


/**
 * Official solution with Deque
 *
 * Algorithm
 * First, perform a breadth-first search to populate the deque with nodes that have 0 or 1 children, in number order.
 * Now when inserting a node, the parent is the first element of deque, and we add this new node to our deque.
 *
 * Time Complexity: The preprocessing is O(N), where N is the number of nodes in the tree.
 *                  Each insertion operation thereafter is O(1).
 * Space Complexity: O(N_cur) space complexity, when the size of the tree during the current
 *                   insertion operation is N_cur.
 */
class CBTInserter {
    TreeNode root;
    Deque<TreeNode> deque;
    public CBTInserter(TreeNode root) {
        this.root = root;
        deque = new LinkedList();
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);

        // BFS to populate deque
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left == null || node.right == null)
                deque.offerLast(node);
            if (node.left != null)
                queue.offer(node.left);
            if (node.right != null)
                queue.offer(node.right);
        }
    }

    public int insert(int v) {
        TreeNode node = deque.peekFirst();
        deque.offerLast(new TreeNode(v));
        if (node.left == null)
            node.left = deque.peekLast();
        else {
            node.right = deque.peekLast();
            deque.pollFirst();
        }

        return node.val;
    }

    public TreeNode get_root() {
        return root;
    }
}


/**
 * Other's solution with List of flattened tree
 *
 * Algorithm
 * Store tree nodes to a list self.tree in bfs order.
 * Node tree[i] has left child tree[2 * i + 1] and right child tree[2 * i + 2]
 * 
 * So when insert the Nth node (0-indexed), we push it into the list.
 * we can find its parent tree[(N - 1) / 2] directly.
 */
class CBTInserter {
    List<TreeNode> treeList;
    
    public CBTInserter(TreeNode root) {
        treeList = new ArrayList<>();
        treeList.add(root);
        for (int i = 0; i < treeList.size(); i++) {
            TreeNode cur = treeList.get(i);
            if (cur.left != null) treeList.add(cur.left);
            if (cur.right != null) treeList.add(cur.right);
        }
    }

    public int insert(int v) {
        // Node treeList[i] has left child treeList[2 * i + 1] and right child treeList[2 * i + 2]
        int N = treeList.size();
        TreeNode node = new TreeNode(v);
        treeList.add(node);
        if (N % 2 == 1)
            treeList.get((N - 1) / 2).left = node;
        else
            treeList.get((N - 1) / 2).right = node;
        
        return treeList.get((N - 1) / 2).val;
    }

    public TreeNode get_root() {
        return treeList.get(0);
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
 * Your CBTInserter object will be instantiated and called as such:
 * CBTInserter obj = new CBTInserter(root);
 * int param_1 = obj.insert(v);
 * TreeNode param_2 = obj.get_root();
 */
