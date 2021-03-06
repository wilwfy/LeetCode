/**
 * Other's solution of DP
 */
class Solution {
    public int twoCitySchedCost(int[][] costs) {
        int N = costs.length/2;
        // dp[i][j] represents the cost when considering first (i + j) people in which i people assigned
        // to city A and j people assigned to city B.
        int[][] dp = new int[N+1][N+1];
        
        for (int i = 1; i <= N; i++)
            // Total i persons go to City A
            dp[i][0] = dp[i-1][0] + costs[i-1][0];
        
        for (int j = 1; j <= N; j++)
            // Total j persons go to City B
            dp[0][j] = dp[0][j-1] + costs[j-1][1];
        
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                // Decide whether the i+j-1 th person should go to City A or City B
                dp[i][j] = Math.min(dp[i-1][j] + costs[i+j-1][0], dp[i][j-1] + costs[i+j-1][1]);
            }
        }
        return dp[N][N];
    }
}


/**
 * Other's solution of Sorting
 *
 * Intuition
 * How much money can we save if we fly a person to A vs. B? To minimize the total cost, we should fly the person with the maximum
 * saving to A, and with the minimum - to B.
 * 
 * Example: [30, 100], [40, 90], [50, 50], [70, 50].
 * Savings: 70, 50, 0, -20.
 * 
 * Obviously, first person should fly to A, and the last - to B.
 * 
 * Solution
 * We sort the array by the difference between costs for A and B. Then, we fly first N people to A, and the rest - to B.
 *
 * Time Complexity: O(nlogn). n is costs.length.
 * Space Complexity: O(1).
 */
class Solution {
    public int twoCitySchedCost(int[][] costs) {
        Arrays.sort(costs, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return (a[1] - a[0]) - (b[1] - b[0]);
            }
        });
        int cost = 0;
        for (int i = 0; i < costs.length / 2; i++) {
            cost += costs[i][1] + costs[costs.length-i-1][0];
        }
        return cost;
    }
}


/**
 * My solution based on other's solution of Sorting
 *
 * Time Complexity: O(nlogn). n is costs.length.
 * Space Complexity: O(1).
 */
class Solution {
    public int twoCitySchedCost(int[][] costs) {
        Arrays.sort(costs, (a, b) -> (a[1] - a[0]) - (b[1] - b[0]));
        
        int cost = 0;
        for (int i = 0; i < costs.length/2; i++) {
            cost += costs[i][1] + costs[costs.length-1-i][0];
        }
        return cost;
    }
}
