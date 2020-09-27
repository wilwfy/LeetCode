/**
 * Official solution of Simulation
 *
 * Intuition
 * 
 * A senator performing a ban doesn't need to use it on another senator immediately. We can wait
 * to see when another team's senator will vote, then use that ban retroactively.
 * 
 * Algorithm
 * 
 * Put the senators in an integer queue: 1 for 'Radiant' and 0 for 'Dire'.
 * 
 * Now process the queue: if there is a floating ban for that senator, exercise it and continue.
 * Otherwise, add a floating ban against the other team, and enqueue this senator again.
 *
 * Time Complexity: O(N) where N is the size of the senate. Every vote removes one senator from the other team.
 * Space Complexity: O(N), the space used by our queue.
 */
class Solution {
    public String predictPartyVictory(String senate) {
        Queue<Integer> qu = new LinkedList<>();
        int[] people = new int[2]; // count of senators for the two parties respectively
        int[] bans = new int[2]; // count of ban for the two parties respectively
        for (char person: senate.toCharArray()) {
            int x = person == 'R' ? 1 : 0; // 1 presents party 'R', 0 presents party 'D'
            people[x]++;
            qu.add(x);
        }
        
        while (people[0] > 0 && people[1] > 0) {
            int x = qu.poll(); // get senator
            if (bans[x] > 0) { // There are bans on the party of this senator,
                bans[x]--;     // decrease the bans, 
                people[x]--;   // then remove one senator of this party
            } else { // There are NO bans on the party of this senator
                bans[x^1]++; // ban the other party
                qu.add(x); // then keep this senator and move it to the tail of qu
            }
        }
        return (people[1] > 0) ? "Radiant" : "Dire";
    }
}


/**
 * Other's solution of Greedy
 *
 * Explanation
 *
 * This is obliviously a greedy algorithm problem. Each senate R must ban its next closest
 * senate D who is from another party, or else D will ban its next senate from R's party.
 * 
 * The idea is to use two queues to save the index of each senate from R's and D's parties,
 * respectively. During each round, we delete the banned senate's index; and plus the remainning
 * senate's index with n(the length of the input string senate), then move it to the back of its
 * respective queue.
 *
 * Time: O(N)
 * Space: O(N)
 */
class Solution {
    public String predictPartyVictory(String senate) {
        Queue<Integer> rQueue = new LinkedList<>(), dQueue = new LinkedList<>();
        int n = senate.length();
        for (int i = 0; i < n; i++) {
            if (senate.charAt(i) == 'R') rQueue.offer(i);
            else dQueue.offer(i);
        }
        while (!rQueue.isEmpty() && !dQueue.isEmpty()) {
            Integer d = dQueue.poll(), r = rQueue.poll();
            if (d < r) dQueue.offer(++n); // keep this senator of party 'D' with new index ++n 
            else rQueue.offer(++n); // keep this senator of party 'R' with new index ++n
        }
        return rQueue.size() > 0 ? "Radiant" : "Dire";
    }
}
