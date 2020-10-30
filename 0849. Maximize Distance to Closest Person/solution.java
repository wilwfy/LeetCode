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
