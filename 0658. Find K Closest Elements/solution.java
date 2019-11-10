/**
 * Official solution based on Sort()
 *
 * Time complexity : O(nlogn). Collections.sort() uses binary sort so it has a O(nlogn) complexity.
 * Space complexity : O(k). The in-place sorting does not consume any extra space. However, generating a k length sublist will take some space.
 */
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        Integer[] arrInt = Arrays.stream(arr)
                                 .boxed()
                                 .toArray(Integer[]::new);
        Arrays.sort(arrInt, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                int aVal = a.intValue(), bVal = b.intValue();
                return aVal == bVal ? aVal - bVal : Math.abs(aVal - x) - Math.abs(bVal - x);
            }
        });
        //System.out.println(Arrays.asList(arrInt));
        List<Integer> res = Arrays.asList(arrInt).subList(0, k);
        Collections.sort(res);
        return res;
    }
}


/**
 * Official solution based on Two Pointers and Binary Search
 *
 * Time complexity : O(logn+k). O(logn) is for the time of binary search, while O(k) is for shrinking the index range to k elements.
 * Space complexity : O(k). It is to generate the required sublist.
 */
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {       
        int n = arr.length;
        if (x <= arr[0]) {
            return Arrays.stream(arr)
                .boxed()
                .collect(Collectors.toList())
                .subList(0, k);
        } else if (x >= arr[n-1]) {
            return Arrays.stream(arr)
                .boxed()
                .collect(Collectors.toList())
                .subList(n-k, n);
        } else {
            // Returns index of key in sorted list sorted in ascending order
            int idx = Arrays.binarySearch(arr, x);
            
            // If key is not present, the it returns "(-(insertion point) - 1)". 
            // The insertion point is defined as the point at which the key 
            // would be inserted into the list.
            if (idx < 0) idx = -idx - 1;
            
            int lo = Math.max(0, idx - k - 1), hi = Math.min(n - 1, idx + k - 1);
            while (hi - lo > k - 1) {
                if ((arr[hi] - x) >= (x - arr[lo]))
                    hi--;
                else if ((arr[hi] - x) < (x - arr[lo]))
                    lo++;
                else
                    System.out.println("Unhandled case: " + lo + " ~ " + hi);
            }
            
            return Arrays.stream(arr)
                .boxed()
                .collect(Collectors.toList())
                .subList(lo, hi+1);
        }
    }
}
