/**
 * My solution
 *
 * Time: O(n)
 * Space: O(1)
 */
class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        if (nums.length == 0) return res;

        int lo = nums[0], hi = nums[0];
        boolean singleRange = true;
        for (int i = 1; i < nums.length; i++) {
            //long diff = Long.valueOf(nums[i] - nums[i-1]);
            //double diff = Double.valueOf(nums[i] - nums[i-1]);
            int diff = Math.abs(nums[i] - nums[i-1]);
            //System.out.println("diff = " + diff);
            if (diff > 1) {
                if (singleRange) {
                    res.add(String.valueOf(lo));
                } else {
                    res.add(String.valueOf(lo) + "->" + String.valueOf(hi));
                }
                lo = nums[i];
                hi = nums[i];
                singleRange = true;
            } else {
                // Because it is an array without duplicates,
                // so nums[i] - nums[i-1] = 1 here
                hi = nums[i];
                singleRange = false;
            }
        }

        // Deal with the tail of the array
        if (singleRange) {
            res.add(String.valueOf(lo));
        } else {
            res.add(String.valueOf(lo) + "->" + String.valueOf(hi));
        }
        return res;
    }
}


/**
 * Other's solution
 *
 * Time: O(n)
 * Space: O(1)
 */
class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> list = new ArrayList();
	      if (nums.length == 1) {
	    	    list.add(nums[0] + "");
	    	    return list;
	      }
        for (int i = 0; i < nums.length; i++) {
        	  int a = nums[i];
        	  while (i+1 < nums.length && (nums[i+1] - nums[i]) == 1) {
        		    i++;
        	  }
        	  if (a != nums[i]) {
        		    list.add(a + "->" + nums[i]);
        	  } else {
        		    list.add(a + "");
        	  }
        }
        return list;
    }
}
