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

/* Optimized solution by using ArrayList<Integer>
 * and map.getOrDefault(Object key, V defaultValue)
 */
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
        for (int i = 0; i < a.length; i++) {
            //if(map.containsKey(a[i])) {
            //    map.put(a[i], map.get(a[i])+1);
            //} else {
            //    map.put(a[i], 1);
            //}
            map.put(a[i], map.getOrDefault(a[i], 0)+1);
        }
        ArrayList<Integer> nums = new ArrayList<Integer>();
        for (int num : b) {
            //if (map.containsKey(num) && (map.get(num) > 0))  {
            if (map.getOrDefault(num, 0) > 0)  {
                nums.add(num);
                map.put(num, map.get(num)-1);
            }
        }
        int[] intersect = new int[nums.size()];
        //System.arraycopy(nums, 0, intersect, 0, count);
        for (int i = 0; i < nums.size(); i++ ) {
            intersect[i] = nums.get(i);
        }
        return intersect;
    }
}


/*
 * Other's solution for sorted arrays
 */
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
    
    	List<Integer> result = new ArrayList();
    	int i = 0;
    	int j = 0;
    	while (i < nums1.length && j < nums2.length) {
    		if (nums1[i] > nums2[j]) {
    			j++;
    
    		} else if (nums1[i] < nums2[j]) {
    			i++;
    
    		} else {
    			result.add(nums1[i]);
    			i++;
    			j++;
    		}
    	}
        int[] resultArr = new int[result.size()];
        int count = 0;
        for (Integer content: result) {
            resultArr[count++] = (int)content;
        }
        return resultArr;
    }
}
