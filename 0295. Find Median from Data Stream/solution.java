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


/*
 * Two Heaps
 */
class MedianFinder {
    protected Queue<Integer> lo, hi;
    /** initialize your data structure here. */
    public MedianFinder() {
        lo = new PriorityQueue<Integer>();
        hi = new PriorityQueue<Integer>(Collections.reverseOrder());
    }
    
    public void addNum(int num) {
        lo.offer(num);
        hi.offer(lo.poll());
        if (hi.size() > lo.size())
            lo.offer(hi.poll());
    }
    
    public double findMedian() {
        if (lo.size() == hi.size()) {
            return (lo.peek()+ hi.peek()) * 0.5;
        } else {
            return lo.peek();
        }
    }
}


/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */


/*
 * Multiset and Two Pointers
 * Intuition
 * 
 * Self-balancing Binary Search Trees (like an AVL Tree) have some very interesting properties. They maintain the tree's height to a logarithmic bound. Thus inserting a new element has reasonably good time performance. The median always winds up in the root of the tree and/or one of its children. Solving this problem using the same approach as Approach 3 but using a Self-balancing BST seems like a good choice. Except the fact that implementing such a tree is not trivial and prone to errors.
 * 
 * Why reinvent the wheel? Most languages implement a multiset class which emulates such behavior. The only problem remains keeping track of the median elements. That is easily solved with pointers! [2]
 * 
 * We maintain two pointers: one for the lower median element and the other for the higher median element. When the total number of elements is odd, both the pointers point to the same median element (since there is only one median in this case). When the number of elements is even, the pointers point to two consecutive elements, whose mean is the representative median of the input.
 * 
 * Algorithm
 * 
 * Two iterators/pointers lo_median and hi_median, which iterate over the data multiset.
 * 
 * While adding a number num, three cases arise:
 * 
 * The container is currently empty. Hence we simply insert num and set both pointers to point to this element.
 * 
 * The container currently holds an odd number of elements. This means that both the pointers currently point to the same element.
 * 
 * If num is not equal to the current median element, then num goes on either side of it. Whichever side it goes, the size of that part increases and hence the corresponding pointer is updated. For example, if num is less than the median element, the size of the lesser half of input increases by 11 on inserting num. Thus it makes sense to decrement lo_median.
 * If num is equal to the current median element, then the action taken is dependent on how num is inserted into data. NOTE: In our given C++ code example, std::multiset::insert inserts an element after all elements of equal value. Hence we increment hi_median.
 * The container currently holds an even number of elements. This means that the pointers currently point to consecutive elements.
 * 
 * If num is a number between both median elements, then num becomes the new median. Both pointers must point to it.
 * Otherwise, num increases the size of either the lesser or higher half of the input. We update the pointers accordingly. It is important to remember that both the pointers must point to the same element now.
 * Finding the median is easy! It is simply the mean of the elements pointed to by the two pointers lo_median and hi_median.
*/
