/**
 * Other's solution of Sort and Insert
 *
 * Pick out tallest group of people and sort them in a subarray (S). Since there's no other groups of people taller than them,
 * therefore each guy's index will be just as same as his k value.
 * For 2nd tallest group (and the rest), insert each one of them into (S) by k value. So on and so forth.
 *
 * E.g.
 * input: [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 * subarray after step 1: [[7,0], [7,1]]
 * subarray after step 2: [[7,0], [6,1], [7,1]]
 */
class Solution {
    public int[][] reconstructQueue(int[][] people) {
        if (people.length == 0) return new int[0][0];
        
        Arrays.sort(people, (p1, p2) -> (p1[0] == p2[0] ? p1[1] - p2[1] : p2[0] - p1[0]));
        List<int[]> res = new LinkedList<>();
        for (int[] p: people)
            res.add(p[1], p);
        return res.toArray(new int[people.length][]);
    }
}
