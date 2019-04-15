class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        List<String> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (String word: words)
            map.put(word, map.getOrDefault(word, 0)+1);
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, (ent1, ent2) -> {
            if (ent1.getValue() == ent2.getValue()) {
                return ent1.getKey().compareTo(ent2.getKey());
            } else {
                return (ent2.getValue() > ent1.getValue()) ? 1 : -1;
            }
        });
        for (int i = 0; i < k; i++)
            res.add(list.get(i).getKey());
        return res;
    }
}


/*
 * Official's solution based on Sorting
 *
 * Time Complexity: O(NlogN), where N is the length of words. We count the frequency of each
 *                  word in O(N) time, then we sort the given words in O(NlogN) time.
 * Space Complexity: O(N), the space used to store our candidates.
 */
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> count = new HashMap();
        for (String word: words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        List<String> candidates = new ArrayList(count.keySet());
        Collections.sort(candidates, (w1, w2) -> count.get(w1).equals(count.get(w2)) ?
                w1.compareTo(w2) : count.get(w2) - count.get(w1));

        return candidates.subList(0, k);
    }
}


/*
 * Official solution based on Heap
 *
 * Time Complexity: O(Nlogk), where N is the length of words. We count the frequency of each word in O(N) time, then
 *                  we add N words to the heap, each in O(logk) time. Finally, we pop from the heap up to k times. 
 *      			As k ≤ N, this is O(Nlogk) in total.
 *
 *                  In Python, we improve this to O(N+klogN): our heapq.heapify operation and counting operations are
 *          		O(N), and each of k heapq.heappop operations are O(logN).
 *
 * Space Complexity: O(N), the space used to store our count.
 */
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> count = new HashMap();
        for (String word: words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<String> heap = new PriorityQueue<String>(
                (w1, w2) -> count.get(w1).equals(count.get(w2)) ?
                w2.compareTo(w1) : count.get(w1) - count.get(w2) );

        for (String word: count.keySet()) {
            heap.offer(word);
            if (heap.size() > k) heap.poll();
        }

        List<String> ans = new ArrayList();
        while (!heap.isEmpty()) ans.add(heap.poll());
        Collections.reverse(ans);
        return ans;
    }
}
