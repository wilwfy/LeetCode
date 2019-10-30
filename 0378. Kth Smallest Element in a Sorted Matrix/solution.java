/**
 * A detail explanation:
 *     https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/discuss/301357/Java-0ms-(added-Python-and-C%2B%2B)%3A-Easy-to-understand-solutions-using-Heap-and-Binary-Search
 */

/**
 * Solution of using Priority Queue which is similar to Problem 373 'Find K Pairs with Smallest Sums'
 *
 * Time Complexity: O(min(k,n) + k*log(n))
 * Space Complexity: k
 */
class Solution {
    class Node {
        int row;
        int col;
        public Node (int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Node> pq = new PriorityQueue<>(
            (a, b) -> matrix[a.row][a.col] - matrix[b.row][b.col]
        );
        
        int n = matrix.length;
        for (int j = 0; j < Math.min(n, k); j++) {
        //for (int j = 0; j < n; j++) {
            pq.offer(new Node(0, j));
        }
        
        int res = 0;
        for (int i = 0; i < k; i++) {
            Node nd = pq.poll();
            res = matrix[nd.row][nd.col];
            if (nd.row == n - 1) continue;
            pq.offer(new Node(nd.row+1, nd.col));
        }
        return res;
    }
}


/**
 * Other's solution of Binary Search
 *
 * Time Complexity: O(nlogm) while m = max - min.
 *                  Main loop is binary search of max - min.
 *                  Swap from left-bottom to right-top can get count <= mid in O(n) time instead of O(nlogn),
 *                  total complexity will be O(nlogm) while m = max - min.
 * Space Complexity: O(1)
 */
class Solution {  
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        
        // get the lowest and highest possible num, will shrink search space according to the two nums
        // [lo, hi] is our initial search range
        int lo = matrix[0][0], hi = matrix[n-1][n-1];
        
        // Binary Search
        while (lo <= hi) {
            int mid = lo + (hi - lo)/2;
            int cnt = getLessEqual(matrix, mid);
            if (cnt < k)
                lo = mid + 1;
            else
                // when "count > k", the binary search continues until "count == k"
                // also it's obvious there must be a "mid" value for which "count == k"
                // when "count == k", the "mid" value is larger than or equal to the k-th smallest number in the matrix
                // and binary search continues until "lo == hi", thus guarantee that the "mid" value is qual to the k-th smallest number
                hi = mid - 1;
        }
        return lo;
    }
    
    public int getLessEqual(int[][]matrix, int mid) {
        int count = 0;
        int n = matrix.length;
        int i = n - 1, j = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] > mid)
                i--;
            else {
                count += i + 1;
                j++;
            }
        }
        return count;
    }
}



/**
 * Solution of using Priority Queue with Comparable class
 */
public class Solution {
    class Tuple implements Comparable<Tuple> {
        int x, y, val;
        public Tuple (int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
        
        @Override
        public int compareTo (Tuple that) {
            return this.val - that.val;
        }
    }
    
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        PriorityQueue<Tuple> pq = new PriorityQueue<Tuple>();
        for(int j = 0; j <= n-1; j++) pq.offer(new Tuple(0, j, matrix[0][j]));
        for(int i = 0; i < k-1; i++) {
            Tuple t = pq.poll();
            if(t.x == n-1) continue;
            pq.offer(new Tuple(t.x+1, t.y, matrix[t.x+1][t.y]));
        }
        return pq.poll().val;
    }
}
