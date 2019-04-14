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
