/*
 * Merge with using three pointers
 */
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        int[] merge = new int[len/2 + 1];
        int i = 0, j = 0, k = 0;
        while ((i < nums1.length) && (j < nums2.length) && (k < len/2 + 1)) {
            if (nums1[i] <= nums2[j]) {
                merge[k] = nums1[i];
                i++;
            } else {
                merge[k] = nums2[j];
                j++;
            }
            k++;
        }
        if (i == nums1.length) {
            for (; k < len/2 + 1; k++, j++)
                merge[k] = nums2[j];
        } else {
            for (; k < len/2 + 1; k++, i++)
                merge[k] = nums1[i];
        }
        double res = (len%2 == 0) ? (merge[len/2 - 1] + merge[len/2])*0.5 : merge[len/2];
        return res;
    }
}
