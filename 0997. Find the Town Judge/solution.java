class Solution {
    public int findJudge(int N, int[][] trust) {
        int[][] relation = new int[N+1][N+1];
        Set<Integer> judge = new HashSet<>();
        // Initialize possibal judge
        for (int i = 1; i < N+1; i++) {
            judge.add(i);           
        }
        // Initialize adjacency matrix
        // 0 - not trust, 1 - trust
        for (int i = 0; i < N+1; i++) {
            Arrays.fill(relation[i], 0);           
        }

        for (int i = 0; i < trust.length; i++) {
            // update relation of trust
            relation[trust[i][0]][trust[i][1]] = 1;
            
            // if a person trusts others then remove this
            // person from the set of judge candidate
            if (judge.contains(trust[i][0]))
                judge.remove(trust[i][0]);
        }
        
        if (judge.size() == 1) {
            // Get the only judge candidate
            Iterator<Integer> itr = judge.iterator();
            int res = itr.next();
            
            // Check if this judge candidate is
            // trusted by every one else
            for (int i = 1; i < N+1; i++) {
                if (i != res) {
                    if (relation[i][res] != 1)
                        return -1;
                }
            }
            return res;
        }
        return -1;
    }
}


/*
 * Other's solution
 *
 * Consider trust as a graph, all pairs are directed edge.
 * The point with in-degree - out-degree = N - 1 become the judge.
 *
 * Time Complexity: Time O(T + N), space O(N)
 */
class Solution {
    public int findJudge(int N, int[][] trust) {
        int[] count = new int[N+1];
        for (int[] t: trust) {
            count[t[0]]--;
            count[t[1]]++;
        }
        for (int i = 1; i <= N; ++i) {
            if (count[i] == N - 1) return i;
        }
        return -1;
    }
}
