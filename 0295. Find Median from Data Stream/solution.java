/*
 * Simple Sorting
 */
class MedianFinder {
    protected static List<Integer> nums;
    /** initialize your data structure here. */
    public MedianFinder() {
        nums = new ArrayList<Integer>();
    }
    
    public void addNum(int num) {
        nums.add(num);
    }
    
    public double findMedian() {
        Collections.sort(nums);
        int cnt = nums.size();
        if (cnt%2 == 0) {
            return (float)(nums.get(cnt/2 - 1) + nums.get(cnt/2))/2;
        } else {
            return nums.get(cnt/2);
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
