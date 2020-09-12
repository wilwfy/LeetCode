/**
 * Other's solution of Greedy
 *
 * Explanation
 *
 * The main idea is based on greedy. Let's say the range of the current jump is [curBegin, curEnd], curFarthest is the farthest point
 * that all points in [curBegin, curEnd] can reach. Once the current point reaches curEnd, then trigger another jump, and set the new
 * curEnd with curFarthest, then keep the above steps.
 *
 * Time: O(n)
 * Space: O(1)
 */
class Solution {
    public int jump(int[] nums) {
        int jumpCnt = 0, curEnd = 0, curFarthest = 0;
        for (int i = 0; i < nums.length - 1; i++) { // i is curStart
            curFarthest = Math.max(curFarthest, i + nums[i]);
            if (curFarthest >= nums.length - 1) { // we have reached the last element
                jumpCnt++;
                break;
            }
            
            if (i == curEnd) { // Now we have finished current jump range curStart ~ curEnd
                jumpCnt++;    // Increase the count for a new jump
                curEnd = curFarthest; // The curEnd of thge new jump range is curFarthest
            }
        }
        return jumpCnt;
    }
}
