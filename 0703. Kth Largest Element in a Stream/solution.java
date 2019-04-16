class KthLargest {
    int minHeapSize;
    PriorityQueue<Integer> minHeap;
    //List<Integer> smallers;

    public KthLargest(int k, int[] nums) {
        minHeapSize = k;
        minHeap = new PriorityQueue<>();
        //smallers = new ArrayList<>();
        for (int num: nums) {
            minHeap.offer(num);
            if (minHeap.size() > minHeapSize)
                //smallers.add(minHeap.poll());
                minHeap.poll();
        }
    }
    
    public int add(int val) {
        minHeap.offer(val);
        if (minHeap.size() > minHeapSize)
            //smallers.add(minHeap.poll());
            minHeap.poll();
        return minHeap.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
