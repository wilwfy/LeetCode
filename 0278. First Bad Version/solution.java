/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

/**
 * Notice:
 *   https://leetcode.com/problems/first-bad-version/discuss/71311/A-good-warning-to-me-to-use-start%2B(end-start)2-to-avoid-overflow
 */


/**
 * My first solution of Binary Search but get Time Limit Exceeded
 *
 * Because (start + end) / 2 could cause overflow when start and end are both huge numbers, for example, close to the Integer_MAXVALUE
 */
public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        return helper(1, n);
    }
    
    public int helper(int start, int end) {
        if (start == end) return start;
        int mid = (start + end) / 2;
        if (isBadVersion(mid))
            return helper(start, mid);
        else
            return helper(mid+1, end);
    }
}


/**
 * My second solution of Binary Search but still get Time Limit Exceeded
 *
 * Because (start + end) / 2 could cause overflow when start and end are both huge numbers, for example, close to the Integer_MAXVALUE
 */
public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int left = 1, right = n;
        while (left < right) {
            int mid = (left + right) / 2;
            if (isBadVersion(mid))
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }
}


/**
 * Official solution of Binary Search and get accepted
 */
public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
