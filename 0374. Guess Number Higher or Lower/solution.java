/* The guess API is defined in the parent class GuessGame.
   @param num, your guess
   @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
      int guess(int num); */

/**
 * Official solution of Binary Search.
 *
 * Time complexity : O(log2(n)). Binary Search is used.
 * Space complexity : O(1). No extra space is used.
 */
public class Solution extends GuessGame {
    public int guessNumber(int n) {
        int low = 1;
        int high = n;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int res = guess(mid);
            if (res == 0)
                return mid;
            else if (res < 0)
                high = mid - 1;
            else
                low = mid + 1;
        }
        return -1;
    }
}


/**
 * Official solution of Ternary Search.
 *
 * Time complexity : O(log3(n)). Ternary Search is used.
 * Space complexity : O(1). No extra space is used.
 */
public class Solution extends GuessGame {
    public int guessNumber(int n) {
        int low = 1;
        int high = n;
        while (low <= high) {
            int mid1 = low + (high - low) / 3;
            int mid2 = high - (high - low) / 3;
            int res1 = guess(mid1);
            int res2 = guess(mid2);
            if (res1 == 0)
                return mid1;
            if (res2 == 0)
                return mid2;
            else if (res1 < 0)
                high = mid1 - 1;
            else if (res2 > 0)
                low = mid2 + 1;
            else {
                low = mid1 + 1;
                high = mid2 - 1;
            }
        }
        return -1;
    }
}

/**
 * Follow up:
 *           It seems that ternary search is able to terminate earlier compared to binary search. But why is binary search
 * more widely used? Ternary Search is worse than Binary Search because Ternary Search does more comparisons than Binary Search
 * in the worst case.
 */
