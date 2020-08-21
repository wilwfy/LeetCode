/**
 * Other's solution of Brute Force
 *
 * Prove is shown in picture:
 *     https://github.com/wilwfy/LeetCode/blob/master/0812.%20Largest%20Triangle%20Area/812_solution_prove.png
 *
 * Explanaiton
 * Burete force loop on all combinations of three points and calculate the area of these three points.
 * If you google "three pointes triangle area formula", you can find the answer with the first result in second.
 * 
 * Time complexity: O(N^3) solution, but N <= 50, so it's fast enough.
 *                  You may find convex hull first as @weidairpi replies. It help improve to O(M^3 + NlogN) in the best case where
 *                  M is the number of points on the hull.
 *                  But it make this easy problem complex and it stays same complexity in the worst case.
 */
class Solution {
    public double largestTriangleArea(int[][] points) {
        double res = 0;
        for (int[] i: points)
            for (int[] j: points)
                for (int[] k: points)
            res = Math.max(res, 0.5 * Math.abs(i[0] * j[1] + j[0] * k[1] + k[0] * i[1]- j[0] * i[1] - k[0] * j[1] - i[0] * k[1]));
        return res;
    }
}


/**
 * Official solution of Brute Force
 *
 * Intuition
 * For each possible triangle, check it's area and keep the area of the largest.
 * 
 * Algorithm
 * We will have 3 for loops to cycle through each choice of 3 points in the array.
 * After, we'll need a function to calculate the area given 3 points. Here we have some options:
 *  -- We can use the Shoelace formula directly, which tells us the area given the 3 points;
 *  -- We can use Heron's formula, which requires the 3 side lengths which we can get by taking the distance of
 *     two points;
 *  -- We can use the formula area = 0.5 * a * b * sin(C) and calculate the angle C with trigonometry.
 * 
 * Our implementation illustrates the use of the shoelace formula.
 * 
 * If we did not know the shoelace formula, we could derive it for triangles with the following approach: starting
 * with points (px, py), (qx, qy), (rx, ry), the area of this triangle is the same under a translation by (-rx, -ry),
 * so that the points become (px-rx, py-ry), (qx-rx, qy-ry), (0, 0).
 * 
 * From there, we could draw a square around the triangle with sides touching the coordinate axes, and calculate the
 * area of the square minus the area of the right triangles surrounding the inner triangle.
 * 
 * For more on this approach, see the Wikipedia entry for the Shoelace formula.
 *
 * Time Complexity: O(N^3), where N is the length of points. We use three for-loops of length O(N), and our work
 *                  calculating the area of a single triangle is O(1).
 * Space Complexity: O(1).
 */
class Solution {
    public double largestTriangleArea(int[][] points) {
        int N = points.length;
        double ans = 0;
        for (int i = 0; i < N; ++i)
            for (int j = i+1; j < N; ++j)
                for (int k = j+1; k < N; ++k)
                    ans = Math.max(ans, area(points[i], points[j], points[k]));
        return ans;
    }

    public double area(int[] P, int[] Q, int[] R) {
        return 0.5 * Math.abs(P[0]*Q[1] + Q[0]*R[1] + R[0]*P[1]
                             -P[1]*Q[0] - Q[1]*R[0] - R[1]*P[0]);
    }
}
