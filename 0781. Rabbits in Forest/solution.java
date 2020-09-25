/**
 * Other's solution with HashMap
 *
 * Time: O(n)
 */
class Solution {
    public int numRabbits(int[] answers) {
        if (answers.length == 0) return 0;
        
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int sum = 0;
        
        // For each rabbits answer
        for (int i : answers) {
            if (i == 0 ) {
                sum += 1;
                continue;
            }
            if (!map.containsKey(i)) {
                // If we haven't accounted for this rabbit color then account for the one telling us
                // as well as the one that rabbit says is that color.
                map.put(i, 0);
                sum += (i + 1);
                
            } else {
                map.put(i, map.get(i) + 1);
                // if there are k of each color then they are all present, remove them to allow the change to account for others.
                if (map.get(i) == i) { 
                    map.remove(i);
                }
            }
        }
        return sum;
    }
}


/**
 * Other's solution with HashMap
 *
 * If x+1 rabbits have same color, then we get x+1 rabbits who all answer x.
 * now n rabbits answer x.
 * If n % (x + 1) == 0, we need n / (x + 1) groups of x + 1 rabbits.
 * If n % (x + 1) != 0, we need n / (x + 1) + 1 groups of x + 1 rabbits.
 * 
 * the number of groups is math.ceil(n / (x + 1)) and it equals to (n + x) / (x + 1) , which is more elegant.
 *
 * Time: O(n)
 */
class Solution {
    public int numRabbits(int[] answers) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int i : answers)
            m.put(i, m.getOrDefault(i, 0) + 1);
        int res = 0;
        for (int i : m.keySet())
            res += (m.get(i) + i) / (i + 1) * (i + 1);
        return res;
    }
}


/**
 * Other's solution with Hash Array
 *
 * We can count the res as we loop on the answers.
 *
 * Time: O(n)
 */
class Solution {
    public int numRabbits(int[] answers) {
        int c[] = new int[1000], res = 0;
        for (int i : answers)
            if (c[i]++ % (i + 1) == 0)
                res += i + 1;
        return res;
    }
}
