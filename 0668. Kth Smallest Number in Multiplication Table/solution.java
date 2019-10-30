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

