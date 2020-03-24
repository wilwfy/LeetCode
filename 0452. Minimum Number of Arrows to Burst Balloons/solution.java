/*
 * My solution
 */
class Solution {
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;
        if (points.length == 1) return 1;
        
        // Sort by start
        Arrays.sort(points, (p1, p2) -> p1[0] - p2[0]);
        
        int cnt = 0;
        int preMinBallonEnd = Integer.MIN_VALUE;
        for (int[] p: points) {
            if (p[0] <= preMinBallonEnd) {
                preMinBallonEnd = Math.min(preMinBallonEnd, p[1]);
            } else {
                cnt++;
                preMinBallonEnd = p[1];
            }
        }
        return cnt;
    }
}


/*
 * Other's solution
 */
class Solution {
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;

        // Sort by end
        Arrays.sort(points, (p1, p2) -> p1[1] - p2[1]);
        
        int cnt = 1;
        int preMinBallonEnd = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] <= preMinBallonEnd) {
                continue;
            } else {
                cnt++;
                preMinBallonEnd = points[i][1];
            }
        }
        return cnt;
    }
}
