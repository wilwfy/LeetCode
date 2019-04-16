/*
 *
 */
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
            (p1, p2) -> (p2[0]*p2[0] + p2[1]*p2[1]) - (p1[0]*p1[0] + p1[1]*p1[1])
        );
        for (int i = 0; i < points.length; i++) {
            maxHeap.offer(points[i]);
            if (maxHeap.size() > K) maxHeap.poll();
        }
        int[][] res = new int[maxHeap.size()][2];
        for (int i = 0; i < res.length; i++)
            res[i] = maxHeap.poll();
        return res;
    }
}


