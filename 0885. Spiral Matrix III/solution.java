/**
 * Other's solution with Direction Array
 *
 * Time: O(max(R,C)^2)
 * Space: O(R*C)
 */
class Solution {
    public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
        int[][] direct = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // east, south, west, north
        int[][] res = new int[R*C][2];
        int cnt = 0;
        int len = 0, d = 0; // move <len> steps in the <d> direction
        res[cnt++] = new int[]{r0, c0};
        while (cnt < R*C) { // walk through all cells of the matrix
            if (d == 0 || d == 2) len++; // when move east or west, the length of path need plus 1
            for (int i = 0; i < len; i++) {
                r0 += direct[d][0];
                c0 += direct[d][1];
                if (r0 >= 0 && r0 < R && c0 >= 0 && c0 < C) // check valid
                    res[cnt++] = new int[]{r0, c0};
            }
            d = (d + 1) % 4; // turn to next direction
        }
        return res;
    }
}


/**
 * Other's solution
 *
 * Intuition:
 * Take steps one by one.
 * If the location is inside of grid, add it to res.
 * But how to simulate the path?
 * 
 * It seems to be annoying, but if we observer the path:
 * 
 * move right 1 step, turn right
 * move down 1 step, turn right
 * move left 2 steps, turn right
 * move top 2 steps, turn right,
 * move right 3 steps, turn right
 * move down 3 steps, turn right
 * move left 4 steps, turn right
 * move top 4 steps, turn right,
 * 
 * we can find the sequence of steps: 1,1,2,2,3,3,4,4,5,5....
 * 
 * So there are two thing to figure out:
 * 
 * how to generate sequence 1,1,2,2,3,3,4,4,5,5
 * how to turn right?
 * 
 * Generate sequence 1,1,2,2,3,3,4,4,5,5
 * Let n be index of this sequence.
 * Then A0 = 1, A1 = 1, A2 = 2 ......
 * We can find that An = n / 2 + 1
 * 
 * How to turn right?
 * By cross product:
 * Assume current direction is (x, y) in plane, which is (x, y, 0) in space.
 * Then the direction after turn right (x, y, 0) × (0, 0, 1) = (y, -x, 0)
 * Translate to code: tmp = x; x = y; y = -tmp;
 * 
 * By arrays of arrays:
 * The directions order is (0,1),(1,0),(0,-1),(-1,0), then repeat.
 * Just define a variable.

 * Time: O(max(R,C)^2)
 * Space: O(R*C) for output
 */
class Solution {
    public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
        int[][] res = new int[R * C][2];
        int x = r0, y = c0;
        int dx = 0, dy = 1, tmp;
        int cnt = 0, n = 0;
        while (cnt < R * C) {
            for (int i = 0; i < n / 2 + 1; ++i) {
                if (0 <= x && x < R && 0 <= y && y < C)
                    res[cnt++] = new int[] {x, y};
                x += dx;
                y += dy;
            }
            tmp = dx;
            dx = dy;
            dy = -tmp;
            ++n;
        }
        return res;
    }
}
