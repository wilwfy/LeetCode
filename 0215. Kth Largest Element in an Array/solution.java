/*
 * Solution based on sorting
 */
class Solution {
    public int findKthLargest(int[] nums, int k) {
        Integer[] arr = new Integer[nums.length];
        for (int i = 0; i < arr.length; i++)
            arr[i] = nums[i];
        Arrays.sort(arr, Collections.reverseOrder());
        return arr[k-1];
    }
}


/*
 * Solution based on Heap
 */
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num: nums) {
            minHeap.offer(num);
            if (minHeap.size() > k)
                minHeap.poll();
        }
        return minHeap.peek();
    }
}
