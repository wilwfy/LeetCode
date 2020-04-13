/*
 * My solution of Sorting
 */
class Solution {
    public int lastStoneWeight(int[] stones) {
        if (stones.length == 1) return stones[0];
        for (int i = stones.length - 1; i >= 1; i--) {
            Arrays.sort(stones, 0, i+1);
            stones[i-1] = stones[i] - stones[i-1];
        }
        return stones[0];
    }
}


/*
 * Other's solution of Priority Queue
 *
 * Time: O(NlogN)
 * Space: O(N)
 */
class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b)-> b - a);
        for (int stone : stones)
            pq.offer(stone);
        while (pq.size() > 1)
            pq.offer(pq.poll() - pq.poll());
        return pq.poll();
    }
}
