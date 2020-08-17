/**
 * My solution of Brute Force
 */
class Solution {
    public int[] distributeCandies(int candies, int num_people) {
        int[] res = new int[num_people];
        int nextCandies = 1;
        while (candies > 0) {
            for (int i = 0; i < num_people; i++) {
                if (candies >= nextCandies)
                    res[i] += nextCandies;
                else {
                    res[i] += candies;
                    return res;
                }
                candies -= nextCandies;
                nextCandies++;
            }
        }
        return res;
    }
}


/**
 * Other's solution of Brute Force
 *
 * Explanation
 * The i-th distribution,
 * we will distribute i + 1 candies to (i % n)th people.
 * We just simulate the process of distribution until we ran out of candies.
 * 
 * Complexity
 * Time O(sqrt(candies))
 * Space O(N) for result
 * 
 * The number of given candies is i + 1, which is an increasing sequence.
 * The total number distributed candies is c * (c + 1) / 2 until it's bigger than candies.
 * So the time it takes is O(sqrt(candies))
 */
class Solution {
    public int[] distributeCandies(int candies, int num_people) {
        int[] res = new int[num_people];
        for (int i = 0; candies > 0; ++i) {
            res[i % num_people] += Math.min(candies, i + 1);
            candies -= i + 1;
        }
        return res;
    }
}
