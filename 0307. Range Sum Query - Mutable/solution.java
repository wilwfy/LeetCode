class NumArray {
    private int[] nums;
    public NumArray(int[] nums) {
        this.nums = nums;
    }
    
    public void update(int i, int val) {
        if ((i >= 0) && (i < nums.length))
            nums[i] = val;
    }
    
    public int sumRange(int i, int j) {
        if ((i > j) || (i < 0) || (i > nums.length) ||
            (j < 0) || (j > nums.length))
            return Integer.MIN_VALUE;
        int sum = 0;
        for (int k = i; k <= j; ++k)
            sum += nums[k];
        return sum;
    }
}


/**
 * Official solution of Segment Tree
 */
class NumArray {
    int[] tree;
    int n;
    public NumArray(int[] nums) {
        if (nums.length > 0) {
            n = nums.length;
            tree = new int[n * 2];
            buildTree(nums);
        }
    }

    // Time: O(n)
    // Space: O(n)
    private void buildTree(int[] nums) {
        for (int i = n, j = 0;  i < 2 * n; i++,  j++)
            tree[i] = nums[j];
        for (int i = n - 1; i > 0; --i)
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
    }
    
    // Time: O(logn). Because there are a few tree nodes with range that include i th array
    //                element, one on each level. There are log(n) levels.
    // Space: O(1)
    void update(int pos, int val) {
        pos += n;
        tree[pos] = val;
        while (pos > 0) {
            int left = pos;
            int right = pos;
            if (pos % 2 == 0) {
                right = pos + 1;
            } else {
                left = pos - 1;
            }
            // parent is updated after child is updated
            tree[pos / 2] = tree[left] + tree[right];
            pos /= 2;
        }
    }
    
    // Time: O(logn). Because on each iteration of the algorithm we move one level up, either
    //                to the parent of the current node or to the next sibling of parent to the
    //                left or right direction till the two boundaries meet. In the worst-case
    //                scenario this happens at the root after log(n) iterations of the algorithm.
    // Space: O(1)
    public int sumRange(int l, int r) {
        // get leaf with value 'l'
        l += n;
        // get leaf with value 'r'
        r += n;
        int sum = 0;
        while (l <= r) {
            if ((l % 2) == 1) {
               sum += tree[l];
               l++;
            }
            if ((r % 2) == 0) {
               sum += tree[r];
               r--;
            }
            l /= 2;
            r /= 2;
        }
        return sum;
    }
}



/**
 * Other's solution of Segment Tree
 */
class NumArray {
    class segmentNode{
        int start;
        int end;
        int sum;
        segmentNode left;
        segmentNode right;
        public segmentNode(int start,int end){
            this.start = start;
            this.end = end;
            this.left = null;
            this.right = null;
            this.sum = 0;
        }
    }
    
    segmentNode node;
    int[] nums;
    public NumArray(int[] nums) {
        this.nums = new int[nums.length];
        for(int i=0;i<nums.length;i++)
            this.nums[i]=nums[i];
        this.node = buildtree(nums,0,nums.length-1);
    }
    
    void update(int i, int val) {
        int diff = nums[i]-val;
        nums[i]=val;
        if(node!=null)
            nupdate(node,i,diff);
    }
    
    public int sumRange(int i, int j) {
        return node==null?0:sum(node,i,j);
    }
    
    public segmentNode buildtree(int[] nums,int start,int end){
        if(start>end) return null;
        segmentNode root = new segmentNode(start,end);
        if(start == end)
            root.sum = nums[start];
        else{
            int mid = start + (end-start)/2;
            root.left = buildtree(nums,start,mid);
            root.right = buildtree(nums,mid+1,end);
            root.sum = root.left.sum+root.right.sum;
        }
        return root;
    }
	
    public int sum(segmentNode root, int start, int end){
        // don't need to return range sum until (root.start==start && root.end==end),
        // when root.start>=start && root.end<=end, then the sum of this node can be
        // used directly, just return root.sum.
        if(root==null||start>root.end || end<root.start) return 0;
        if(start<=root.start && end>=root.end)  return root.sum;
        int left = sum(root.left,start,end);
        int right = sum(root.right,start,end);
        return left+right;
    }
	
    public void nupdate(segmentNode root, int index,int diff){
        if(root==null||index>root.end||index<root.start) return;
        if(index>=root.start && index<=root.end) root.sum-=diff;
        nupdate(root.left,index,diff);
        nupdate(root.right,index,diff);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */
