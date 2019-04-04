class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return interArray(nums1, nums2);
        } else {
            return interArray(nums2, nums1);
        }
    }
    
    public int[] interArray(int[] a, int[] b) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] nums = new int[b.length];
        for (int i = 0; i < a.length; i++) {
            if(map.containsKey(a[i])) {
                map.put(a[i], map.get(a[i])+1);
            } else {
                map.put(a[i], 1);
            }
        }
        int count = 0;
        for (int num : b) {
            if (map.containsKey(num) && (map.get(num) > 0))  {
                nums[count] = num;
                map.put(num, map.get(num)-1);
                System.out.println(nums[count]);
                count++;
            }
        }
        int[] intersect = new int[count];
        System.arraycopy(nums, 0, intersect, 0, count);
        return intersect;
    }
}
