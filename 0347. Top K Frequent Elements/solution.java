class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums)
            map.put(num, map.getOrDefault(num, 0) + 1);
        // Max Heap - keep the k largest values in the heap
        //            with the smallest one on the top of heap
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>(
            (num1, num2) -> map.get(num1) - map.get(num2)
        );
        for (int num: map.keySet()) {
            heap.offer(num);
            if (heap.size() > k) heap.poll();
        }
        while(!heap.isEmpty())
            // Always insert integer at the head of list
            res.add(0, heap.poll());
        return res;
    }
}
