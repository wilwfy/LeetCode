/*
 * Official iterative solution
 *
 * Time Complexity: O(N) considering the list consists of N nodes. We process each of the nodes at most once (we don't process
 *                  the nodes after the n th node from the beginning.
 * Space Complexity: O(1) since we simply adjust some pointers in the original linked list and only use O(1) additional memory
 *                  for achieving the final result.
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {

        // Empty list
        if (head == null) {
            return null;
        }

        // Move the two pointers until they reach the proper starting point
        // in the list.
        ListNode cur = head, prev = null;
        while (m > 1) {
            prev = cur;
            cur = cur.next;
            m--;
            n--;
        }
        // now the curr is the m th element

        // The two pointers that will fix the final connections.
        ListNode con = prev, tail = cur;

        // Iteratively reverse the nodes until n becomes 0.
        ListNode third = null;
        while (n > 0) {
            third = cur.next;
            cur.next = prev;
            prev = cur;
            cur = third;
            n--;
        }
        // now the prev is the n th element
        
        // Adjust the final connections as explained in the algorithm
        // if m = 1, then con = prev = null
        if (con != null) {
            con.next = prev;
        } else {
            head = prev;
        }

        tail.next = cur;
        return head;
    }
}


/*
 * Official recursive/backtracking solution
 *
 * Time Complexity: O(N) since we process all the nodes at-most twice. Once during the normal recursion process and once during
 *                  the backtracking process. During the backtracking process we only just swap half of the list if you think about
 *                  it, but the overall complexity is O(N).
 * Space Complexity: O(N) in the worst case when we have to reverse the entire list. This is the space occupied by the recursion stack.
 */
class Solution {

    // Object level variables since we need the changes
    // to persist across recursive calls and Java is pass by value.
    private boolean stop;
    private ListNode left;

    public void recurseAndReverse(ListNode right, int m, int n) {

        // base case. Don't proceed any further
        if (n == 1) {
            return;
        }

        // Keep moving the right pointer one step forward until (n == 1)
        right = right.next;

        // Keep moving left pointer to the right until we reach the proper node
        // from where the reversal is to start.
        if (m > 1) {
            this.left = this.left.next;
        }

        // Recurse with m and n reduced.
        this.recurseAndReverse(right, m - 1, n - 1);

        // In case both the pointers cross each other or become equal, we
        // stop i.e. don't swap data any further. We are done reversing at this
        // point.
        if (this.left == right || right.next == this.left) {
            this.stop = true;            
        }

        // Until the boolean stop is false, swap data between the two pointers
        if (!this.stop) {
            int t = this.left.val;
            this.left.val = right.val;
            right.val = t;

            // Move left one step to the right.
            // The right pointer moves one step back via backtracking.
            this.left = this.left.next;
        }
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        this.left = head;
        this.stop = false;
        this.recurseAndReverse(head, m, n);
        return head;
    }
}
