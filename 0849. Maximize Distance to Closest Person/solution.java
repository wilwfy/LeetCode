/**
 * My solution of One Pass
 *
 * Time: O(N)
 * Space: O(1)
 */
class Solution {
    public int maxDistToClosest(int[] seats) {
        int i = 0;
        for (; i < seats.length; i++) { // find length of empty seats from the start of the row
            if (seats[i] != 0) break; // i is at the first 1
        }
        int j = seats.length - 1;
        for (; j >= 0; j--) { // find length of empty seats from the end of the row
            if (seats[j] != 0) break; // j is at the last 1
        }
        int openEndMax = Math.max(i - 0, seats.length - 1 - j); // get the longer length from above two lengthes
        int zeroCnt = 0, zeroLenMax = 0;
        int k = i;
        for (; k <= j; k++) { // find the max length of empty seats which is between two occupied seats
            if (seats[k] == 0) {
                zeroCnt++;
            } else {
                zeroLenMax = Math.max(zeroLenMax, zeroCnt);
                zeroCnt = 0;
            }
        }
        int closedEndMax = (zeroLenMax + 1) / 2; // calculate the max distance if to select an empty seat between two occupied ones
        return openEndMax >= closedEndMax ? openEndMax : closedEndMax;
    }
}


/**
 * Official solution of Next Array
 *
 * Intuition
 * 
 * Let left[i] be the distance from seat i to the closest person sitting to the left of i.
 * Similarly, let right[i] be the distance to the closest person sitting to the right of i.
 * This is motivated by the idea that the closest person in seat i sits a distance
 * min(left[i], right[i]) away.
 * 
 * Algorithm
 * 
 * To construct left[i], notice it is either left[i-1] + 1 if the seat is empty, or 0 if it
 * is full. right[i] is constructed in a similar way.
 *
 * Time Complexity: O(N), where N is the length of seats.
 * Space Complexity: O(N), the space used by left and right.
 */
class Solution {
    public int maxDistToClosest(int[] seats) {
        int N = seats.length;
        int[] left = new int[N], right = new int[N];
        Arrays.fill(left, N);
        Arrays.fill(right, N);

        for (int i = 0; i < N; ++i) {
            if (seats[i] == 1) left[i] = 0;
            else if (i > 0) left[i] = left[i-1] + 1;
        }

        for (int i = N-1; i >= 0; --i) {
            if (seats[i] == 1) right[i] = 0;
            else if (i < N-1) right[i] = right[i+1] + 1;
        }

        int ans = 0;
        for (int i = 0; i < N; ++i)
            if (seats[i] == 0)
                ans = Math.max(ans, Math.min(left[i], right[i]));
        return ans;
    }
}


/**
 * Official solution of Two Pointer
 * 
 * Intuition
 * 
 * As we iterate through seats, we'll update the closest person sitting to our left,
 * and closest person sitting to our right.
 * 
 * Algorithm
 * 
 * Keep track of prev, the filled seat at or to the left of i, and future, the filled
 * seat at or to the right of i.
 * 
 * Then at seat i, the closest person is min(i - prev, future - i), with one exception.
 * i - prev should be considered infinite if there is no person to the left of seat i,
 * and similarly future - i is infinite if there is no one to the right of seat i.
 *
 * Time Complexity: O(N), where N is the length of seats.
 * Space Complexity: O(1).
 */
class Solution {
    public int maxDistToClosest(int[] seats) {
        int N = seats.length;
        int prev = -1, future = 0;
        int ans = 0;

        for (int i = 0; i < N; ++i) {
            if (seats[i] == 1) {
                prev = i;
            } else {
                while (future < N && seats[future] == 0 || future < i)
                    future++;

                int left = prev == -1 ? N : i - prev;
                int right = future == N ? N : future - i;
                ans = Math.max(ans, Math.min(left, right));
            }
        }

        return ans;
    }
}


/**
 * Official solution of Group by Zero
 * 
 * Intuition
 * 
 * In a group of K adjacent empty seats between two people, the answer is (K+1) / 2.
 * 
 * Algorithm
 * 
 * For each group of K empty seats between two people, we can take into account the
 * candidate answer (K+1) / 2.
 * 
 * For groups of empty seats between the edge of the row and one other person, the
 * answer is K, and we should take into account those answers too.
 *
 * Time Complexity: O(N), where N is the length of seats.
 * Space Complexity: O(1).
 */
class Solution {
    public int maxDistToClosest(int[] seats) {
        int N = seats.length;
        int K = 0; //current longest group of empty seats
        int ans = 0;

        for (int i = 0; i < N; ++i) {
            if (seats[i] == 1) {
                K = 0;
            } else {
                K++;
                ans = Math.max(ans, (K + 1) / 2);
            }
        }

        for (int i = 0; i < N; ++i)  if (seats[i] == 1) {
            ans = Math.max(ans, i);
            break;
        }

        for (int i = N-1; i >= 0; --i)  if (seats[i] == 1) {
            ans = Math.max(ans, N - 1 - i);
            break;
        }

        return ans;
    }
}
