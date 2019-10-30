/**
 * Official solution of Priority Queue (Heap) [But Time Limit Exceeded]
 *
 * Time Complexity: O(k ∗ mlogm) = O(m^2 * n * logm).
 *                  Our initial heapify operation is O(m). Afterwards, each pop and push is O(mlogm), and our outer
 *                  loop is O(k) = O(m∗n)
 * Space Complexity: O(m). Our heap is implemented as an array with m elements.
 */
class Solution {
    public int findKthNumber(int m, int n, int k) {
        PriorityQueue<Node> heap = new PriorityQueue<Node>(m,
            Comparator.<Node> comparingInt(node -> node.val));

        for (int i = 1; i <= m; i++) {
            heap.offer(new Node(i, i));
        }

        Node node = null;
        for (int i = 0; i < k; i++) {
            node = heap.poll();
            int nxt = node.val + node.root;
            if (nxt <= node.root * n) {
                heap.offer(new Node(nxt, node.root));
            }
        }
        return node.val;
    }
}

class Node {
    int val;
    int root;
    public Node(int v, int r) {
        val = v;
        root = r;
    }
}


/**
 * Official solution of Binary Search which is similar to Problem 378 'Kth Smallest Element in a Sorted Matrix'
 *
 * Time Complexity: O(m∗log(m∗n)).
 *                  Our binary search divides the interval [lo, hi] into half at each step. At each step, we call
 *                  getLessEqual() which requires O(m) time.
 * Space Complexity: O(1). We only keep integers in memory during our intermediate calculations.
 */
class Solution {
    public int findKthNumber(int m, int n, int k) {
        int lo = 1, hi = m * n;
        while (lo < hi) {
            int mi = lo + (hi - lo) / 2;
            int cnt = getLessEqual(m, n, mi);
            if (cnt < k)
                lo = mi + 1;
            else
                hi = mi;
        }
        return lo;
    }
    
    public int getLessEqual(int m, int n, int midVal) {
        int count = 0;
        for (int i = 1; i <= m; i++) {
            count += Math.min(midVal/i, n);
        }
        return count;
    }
}
