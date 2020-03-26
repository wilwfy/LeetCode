/*
 * Other's recursion solution
 *
 * the middle element of the given list would form the root of the binary search tree. All the elements to the left of the middle
 * element would form the left subtree recursively. Similarly, all the elements to the right of the middle element will form the
 * right subtree of the binary search tree. This would ensure the height balance required in the resulting binary search tree.
 *
 * Time Complexity: O(NlogN). Suppose our linked list consists of N elements. For every list we pass to our recursive function, we have
 *                  to calculate the middle element for that list. For a list of size N, it takes N/2 steps to find the middle element
 *                  i.e. O(N) to find the mid. We do this for every half of the original linked list. From the looks of it, this seems
 *                  to be an O(N^2) algorithm. However, on closer analysis, it turns out to be a bit more efficient than O(N^2).
 *                  Let's look at the number of operations that we have to perform on each of the halves of the linked list. As we mentioned
 *                  earlier, it takes N/2 steps to find the middle of a linked list with N elements. After finding the middle element, we
 *                  are left with two halves of size N/2 each. Then, we find the middle element for both of these halves and it would take
 *                  a total of 2×N/4 steps for that. And similarly for the smaller sublists that keep forming recursively. This would give
 *                  us the following series of operations for a list of size N.
 *                  Essentially, this is done logN times since we split the linked list in half every time. Hence, the above equation
 *                  becomes: O(NlogN)
 * Space Complexity: O(logN). Since we are resorting to recursion, there is always the added space complexity of the recursion stack that
 *                   comes into picture. This could have been O(N) for a skewed tree, but the question clearly states that we need to maintain
 *                   the height balanced property. This ensures the height of the tree to be bounded by O(logN). Hence, the space complexity
 *                   is O(logN).
 */
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return new TreeNode(head.val);
        
        ListNode prev = null, slow = head, fast = head;
        while ((fast != null) && (fast.next != null)) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        // cut left sub list here:
        // split the list at middle element node which is pointed
        // by the slow and will be the root node of the binary tree
        prev.next = null; // in case when slow is still equal to head

        TreeNode root = new TreeNode(slow.val);
        // Get the left child and right child of root by recursion
        // with passing the sublist respectively
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(slow.next);
        return root;
    }
}


/*
 * Other's recursion solution with Conversion to Array
 *
 * The main problem with the above solution seems to be the middle element computation. That takes up a lot of unnecessary time
 * and this is due to the nature of the linked list data structure.
 * This solution tries to overcome this by using more space. This approach is a classic example of the time-space tradeoff.
 *
 * Time Complexity: The time complexity comes down to just O(N) now since we convert the linked list to an array initially and
 *                  then we convert the array into a BST. Accessing the middle element now takes O(1) time and hence the time
 *                  complexity comes down.
 * Space Complexity: Since we used extra space to bring down the time complexity, the space complexity now goes up to O(N) as
 *                   opposed to just O(logN) in the previous solution. This is due to the array we construct initially.
 */
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return new TreeNode(head.val);
        
        List<Integer> values = new ArrayList<>();
        while (head != null) {
            values.add(head.val);
            head = head.next;
        }

        return helper(0, values.size()-1, values);
    }
    
    public TreeNode helper(int left, int right, List<Integer> valList) {
        if (left > right) return null;
        int mid = (left + right) / 2;
        TreeNode node = new TreeNode (valList.get(mid));
        
        if (left == right) return node;
        
        node.left = helper(left, mid-1, valList);
        node.right = helper(mid+1, right, valList);
        return node;
    }
}


/*
 * Official solution of Inorder Simulation
 *
 * Elements processed in the inorder fashion on a binary search tree turn out to be sorted in ascending order.
 * We know that the leftmost element in the inorder traversal has to be the head of our given linked list. Similarly, the next
 * element in the inorder traversal will be the second element in the linked list and so on. This is made possible because the
 * initial list given to us is sorted in ascending order.
 *
 * pseudo-code to make the algorithm simple to understand.
 * ➔ function formBst(start, end)
 * ➔      mid = (start + end) / 2
 * ➔      formBst(start, mid - 1)
 * ➔
 * ➔      TreeNode(head.val)
 * ➔      head = head.next
 * ➔       
 * ➔      formBst(mid + 1, end)
 * ➔
 *
 * Time Complexity: The time complexity is still O(N) since we still have to process each of the nodes in the linked list once and
 *                  form corresponding BST nodes.
 * Space Complexity: O(logN) since now the only extra space is used by the recursion stack and since we are building a height balanced
 *                   BST, the height is bounded by logN.
 */
class Solution {
    private ListNode hd;
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return new TreeNode(head.val);
        
        hd = head;
        
        // Get the size of the linked list first
        int size = 0;
        while (head != null) {
            size++;
            head = head.next;
        }
        // Form the BST now that we know the size
        return helper(0, size-1);
    }
    
    public TreeNode helper(int left, int right) {
        // Invalid case
        if (left > right) return null;
        
        int mid = (left + right) / 2;
        
        // First step of simulated inorder traversal. Recursively form
        // the left half
        TreeNode leftTree = helper(left, mid-1);
        
        // Once left half is traversed, process the current node
        TreeNode node = new TreeNode(hd.val);
        node.left = leftTree;
        
        // Maintain the invariance mentioned in the algorithm
        hd = hd.next;
        
        // Recurse on the right hand side and form BST out of them
        node.right = helper(mid+1, right);
        return node;
    }
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
