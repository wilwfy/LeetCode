/**
 * Official solution based on Union-Find
 *
 * Intuition
 * We will need to consider components of an underlying graph. A "Disjoint Set Union" (DSU) data structure is
 * ideal for this.
 * 
 * We will skip the explanation of how a DSU structure is implemented.
 * Please refer to https://leetcode.com/problems/redundant-connection/solution/ for a tutorial on DSU.
 * 
 * Algorithm
 * Let's connect row i to column j, which will be represented by j+10000. The answer is the number of components
 * after making all the connections.
 * Note that for brevity, our DSU implementation does not use union-by-rank. This makes the asymptotic time complexity
 * larger.
 *
 * Time Complexity: O(NlogN), where N is the length of stones.
 *                  (If we used union-by-rank, this can be O(N * α(N)), where α is the Inverse-Ackermann function.)
 * Space Complexity: O(N).
 */
class Solution {
    public int removeStones(int[][] stones) {
        int N = stones.length; // number of stones
        DSU dsu = new DSU(20000); // To distinguish every coordinates since 0 <= stones[i][j] < 10000
        
        for (int[] stone: stones)
            dsu.union(stone[0], stone[1] + 10000); // distinguish y from x by adding y with 10000
        
        Set<Integer> seen = new HashSet<>(); // set of island -- connected stones
        for (int[] stone: stones)
            seen.add(dsu.find(stone[0]));
        
        return N - seen.size();
    }
}

// Data structure of 'Disjoint Set Union'
class DSU {
    int[] parent; // root
    public DSU(int N) {
        parent = new int[N];
        for (int i = 0; i < N; i++)
            parent[i] = i;
    }
    
    public int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }
    
    public void union(int x, int y) {
        parent[find(x)] = find(y);
    }
}


/**
 * Other's solution based on Union-Find
 *
 * Problem:
 * we can remove a stone if and only if, there is another stone in the same column OR row.
 * We try to remove as many as stones as possible.
 * 
 * One sentence to solve:
 * Connected stones can be reduced to 1 stone,
 * the maximum stones can be removed = stones number - islands number.
 * so just count the number of "islands".
 * 
 * 
 * 1. Connected stones
 * Two stones are connected if they are in the same row or same col.
 * Connected stones will build a connected graph.
 * It's obvious that in one connected graph, we can't remove all stones.
 * 
 * We have to have one stone left.
 * An intuition is that, in the best strategy, we can remove until 1 stone.
 * 
 * I guess you may reach this step when solving the problem.
 * But the important question is, how?
 * 
 * 
 * 2. A failed strategy
 * Try to remove the least degree stone. Like a tree, we try to remove leaves first.
 * Some new leaf generated. We continue this process until the root node left.
 * 
 * However, there can be no leaf.
 * When you try to remove the least in-degree stone, it won't work on this "8" like graph:
 * [[1, 1, 0, 0, 0],
 * [1, 1, 0, 0, 0],
 * [0, 1, 1, 0, 0],
 * [0, 0, 1, 1, 1],
 * [0, 0, 0, 1, 1]]
 * 
 * The stone in the center has least degree = 2.
 * But if you remove this stone first, the whole connected stones split into 2 parts,
 * and you will finish with 2 stones left.
 * 
 * 
 * 3. A good strategy
 * In fact, the proof is really straightforward.
 * You probably apply a DFS, from one stone to next connected stone.
 * You can remove stones in reversed order.
 * In this way, all stones can be removed but the stone that you start your DFS.
 * 
 * One more step of explanation:
 * In the view of DFS, a graph is explored in the structure of a tree.
 * As we discussed previously, a tree can be removed in topological order, from leaves to root.
 * 
 * 
 * 4. Count the number of islands
 * We call a connected graph as an island.
 * One island must have at least one stone left.
 * The maximum stones can be removed = stones number - islands number
 * 
 * The whole problem is transferred to:
 * What is the number of islands?
 * 
 * You can show all your skills on a DFS implementation, and solve this problem as a normal one.
 * 
 * 
 * 5. Unify index
 * Struggle between rows and cols?
 * You may duplicate your codes when you try to the same thing on rows and cols.
 * In fact, no logical difference between col index and rows index.
 * 
 * An easy trick is that, add 10000 to col index.
 * So we use 0 ~ 9999 for row index and 10000 ~ 19999 for col.
 * 
 * 
 * 6. Search on the index, not the points
 * When we search on points, we alternately change our view on a row and on a col.
 * 
 * We think:
 * a row index, connect two stones on this row
 * a col index, connect two stones on this col.
 * 
 * In another view：
 * A stone, connect a row index and col.
 * 
 * Have this idea in mind, the solution can be much simpler.
 * The number of islands of points, is the same as the number of islands of indexes.
 * 
 * 
 * 7. Union-Find
 * I use union find to solve this problem.
 * As I mentioned, the elements are not the points, but the indexes.
 * 
 * for each point, union two indexes.
 * return points number - union number
 * Copy a template of union-find,
 * write 2 lines above,
 * you can solve this problem in several minutes.
 * 
 * 
 * Complexity:
 * union and find functions have worst case O(N), amortize O(1)
 * The whole union-find solution with path compression,
 * has O(N) Time, O(N) Space
 *
* The most important, union find is really a common knowledge for algorithm.
* Using both path compression, splitting, or halving and union by rank or size ensures
* that the amortized time per operation is only O(1).
* 
* In this problem, there is N union operation, at most 2 * sqrt(N) node.
* When N get bigger, the most operation of union operation is amortize O(1).
* 
* There were three good resourse of union find:
* 1. top down analusis of path compression: http://www.cs.tau.ac.il/~michas/ufind.pdf
* 2. wiki: https://en.wikipedia.org/wiki/Disjoint-set_data_structure#cite_note-Cormen2009-10
* 3. stackexchange: https://cs.stackexchange.com/questions/50294/why-is-the-path-compression-no-rank-for-disjoint-sets-o-log-n-amortized-fo
* But they most likely give a upper bound time complexity of union find, not a supreme.
 */
class Solution {
    Map<Integer, Integer> findRootMap = new HashMap<>();
    int islands = 0; // number of islands -- connected stones
    
    public int removeStones(int[][] stones) {
        for (int[] stone: stones)
            union(stone[0], ~stone[1]); // distinguish the coordinates x and y by bitwise compliment operation on y
        return stones.length - islands;
    }
    
    private int find(int x) {
        if (findRootMap.putIfAbsent(x, x) == null)
            islands++;
        
        if (x != findRootMap.get(x))
            findRootMap.put(x, find(findRootMap.get(x))); // find the root of x
        
        return findRootMap.get(x);
    }
    
    private void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            findRootMap.put(x, y);
            islands--;
        }
    }
}
