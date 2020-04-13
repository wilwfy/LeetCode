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


/*
 * Other's solution of bucket sort and two pointers
 *
 * Time: Average O(N + K), Worst O(N^2). N is length of stones, K is the value range of stones.
 * Space: O(N + K)
 */
class Solution {
    public int lastStoneWeight(int[] stones) {
        int[] buckets = new int[1001];
        for (int i = 0; i < stones.length; i++) {
            buckets[stones[i]]++;
        }

        int slow = buckets.length - 1;   //start from the big to small
        while (slow > 0) {
		// If the number of stones with the same size is even or zero, 
		// these stones can be totally destroyed pair by pair or there is no such size stone existing, 
		// we can just ignore this situation.
		
        // When the number of stones with the same size is odd, 
		// there should leave one stone which is to smash with the smaller size one.
            if (buckets[slow]%2 != 0) {
                int fast = slow - 1;
                while (fast > 0 && buckets[fast] == 0) {
                    fast--;
                }
                if (fast == 0) break;
                buckets[fast]--;
                buckets[slow - fast]++;
            }
            slow--;
        }
        return slow;
    }
}
