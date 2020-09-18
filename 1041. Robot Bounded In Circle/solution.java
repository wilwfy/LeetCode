/**
 * My solution with HashMap
 *
 * Time: O(n)
 * Space: O(1). Constant size of HashMap and arrays.
 */
class Solution {
    public boolean isRobotBounded(String instructions) {
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // north, south, east, west
        int[] dirIdx = {0, 1, 2, 3}; // north, south, east, west
        Map<Integer, Integer> turnLeft = new HashMap<>(); // <cur direction, next direction>
        turnLeft.put(0, 3);
        turnLeft.put(3, 1);
        turnLeft.put(1, 2);
        turnLeft.put(2, 0);
        Map<Integer, Integer> turnRight = new HashMap<>();
        turnRight.put(0, 2);
        turnRight.put(2, 1);
        turnRight.put(1, 3);
        turnRight.put(3, 0);
        
        int[] location = {0, 0};
        int dir = 0;
        for (char c: instructions.toCharArray()) {
            switch (c) {
                case 'G':
                    location[0] += dirs[dir][0];
                    location[1] += dirs[dir][1];
                    break;
                case 'L':
                    dir = turnLeft.get(dir);
                    break;
                case 'R':
                    dir = turnRight.get(dir);
                    break;
                default:
                    System.out.println("Error: wrong instruction!");
                    break;
            }
        }
        return ((dir != 0) || (location[0] == 0 && location[1] == 0));
    }
}


/**
 * Other's solution without HashMap
 *
 * Intuition
 * Let chopper help explain.
 * 
 * Starting at the origin and face north (0,1),
 * after one sequence of instructions,
 * 
 * if chopper return to the origin, he is obvious in an circle.
 * if chopper finishes with face not towards north,
 * it will get back to the initial status in another one or three sequences.
 * 
 * Explanation
 * (x,y) is the location of chopper.
 * d[i] is the direction he is facing.
 * i = (i + 1) % 4 will turn right
 * i = (i + 3) % 4 will turn left
 * Check the final status after instructions.
 * 
 * 
 * Complexity
 * Time: O(N)
 * Space: O(1)
 */
class Solution {
    public boolean isRobotBounded(String instructions) {
        int x = 0, y = 0, idx = 0, dirs[][] = {{0, 1}, {1, 0}, {0, -1}, { -1, 0}};
        for (int j = 0; j < instructions.length(); ++j)
            if (instructions.charAt(j) == 'R')
                idx = (idx + 1) % 4;
            else if (instructions.charAt(j) == 'L')
                idx = (idx + 3) % 4;
            else {
                x += dirs[idx][0]; y += dirs[idx][1];
            }
        return x == 0 && y == 0 || idx > 0;
    }
}
