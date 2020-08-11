/**
 * Other's solution of Union-Find
 *
 * Intuition
 * This problem is very similar to "Redundant Connection". But the description on the parent/child relationships
 * is much better clarified.
 * 
 * There are two cases for the tree structure to be invalid.
 * 1) A node having two parents;
 *    including corner case: e.g. [[4,2],[1,5],[5,2],[5,3],[2,4]]
 * 2) A circle exists
 * If we can remove exactly 1 edge to achieve the tree structure, a single node can have at most two parents.
 * So my solution works in two steps.
 * 
 * 1) Check whether there is a node having two parents. 
 *     If so, store them as candidates A and B, and set the second edge invalid. 
 * 2) Perform normal union find. 
 *     If the tree is now valid 
 *            simply return candidate B
 *     else if candidates not existing 
 *            we find a circle, return current edge; 
 *     else 
 *            remove candidate A instead of B.
 */
class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int[] candA = {-1, -1};
        int[] candB = {-1, -1};
        int[] parent = new int[edges.length + 1];
        // step 1, check whether there is a node with two parents
        for (int i = 0; i < edges.length; i++) {
            if (parent[edges[i][1]] == 0)
                parent[edges[i][1]] = edges[i][0];
            else {
                candB = new int[]{edges[i][0], edges[i][1]};
                candA = new int[]{parent[edges[i][1]], edges[i][1]};
                edges[i][1] = 0; // set the second edge invalid.
            }
        }
        // step 2, union find
        for (int i = 0; i < edges.length; i++)
            parent[i] = i;
        for (int i = 0; i < edges.length; i++) {
            if (edges[i][1] == 0) continue; // Skip the second edge which has been already set as invalid.
            int child = edges[i][1], father = edges[i][0];
            // Now every node only has 1 parent, so root of child is implicitly child
            if (root(parent, father) == child) {
                if (candA[0] == -1)
                    return edges[i]; // we find a circle, return current edge;
                return candA;
            }
            parent[child] = father;
        }
        return candB;
    }
    
    private int root(int[] parent, int i) {
        while (parent[i] != i) {
            parent[i] = parent[parent[i]];
            i = parent[i];
        }
        return i;
    }

}


/**
 * Other's solution of Union-Find
 *
 * Intuition
 * Assumption before we start: input "edges" contains a directed tree with one and only one extra edge. If we remove
 * the extra edge, the remaining graph should make a directed tree - a tree which has one root and from the root you
 * can visit all other nodes by following directed edges. It has features:
 * 
 * 1. one and only one root, and root does not have parent;
 * 2. each non-root node has exactly one parent;
 * 3. there is no cycle, which means any path will reach the end by moving at most (n-1) steps along the path.
 * 
 * By adding one edge (parent->child) to the tree:
 * 
 * 1. every node including root has exactly one parent, if child is root;
 * 2. root does not have parent, one node (child) has 2 parents, and all other nodes have exactly 1 parent, if child
 * is not root.
 * 
 * Let's check cycles. By adding one edge (a->b) to the tree, the tree will have:
 * 
 * 1. a cycle, if there exists a path from ***(b->...->a)***; in particularly, if b == root, (in other word, add an
 * edge from a node to root) it will make a cycle since there must be a path ***(root->...->a)***.
 * 2. no cycle, if there is no such a path ***(b->...->a)***.
 * 
 * After adding the extra edge, the graph can be generalized in 3 different cases:
 *     https://github.com/wilwfy/LeetCode/blob/master/0685.%20Redundant%20Connection%20II/685_Redundant-Connection-II_Union-Find_solution.png
 * 
 * Case 1: "c" is the only node which has 2 parents and there is not path (c->...->b) which means no cycle. In this
 * case, removing either "e1" or "e2" will make the tree valid. According to the description of the problem, whichever
 * edge added later is the answer.
 * 
 * Case 2: "c" is the only node which has 2 parents and there is a path(c->...->b) which means there is a cycle. In
 * this case, "e2" is the only edge that should be removed. Removing "e1" will make the tree in 2 separated groups.
 * Note, in input edges, "e1" may come after "e2".
 * 
 * Case 3: this is how it looks like if edge (a->root) is added to the tree. Removing any of the edges along the cycle
 * will make the tree valid. But according to the description of the problem, the last edge added to complete the cycle
 * is the answer. Note: edge "e2" (an edge pointing from a node outside of the cycle to a node on the cycle) can never
 * happen in this case, because every node including root has exactly one parent. If "e2" happens, that make a node on
 * cycle have 2 parents. That is impossible.
 * 
 * As we can see from the pictures, the answer must be:
 * 
 * 1. one of the 2 edges that pointing to the same node in case 1 and case 2; there is one and only one such node
 * which has 2 parents.
 * 2. the last edge added to complete the cycle in case 3.
 * Note: both case 2 and case 3 have cycle, but in case 2, "e2" may not be the last edge added to complete the cycle.
 * 
 * Now, we can apply Disjoint Set (DS) to build the tree in the order the edges are given. We define ds[i] as the
 * parent or ancestor of node i. It will become the root of the whole tree eventually if edges does not have extra
 * edge. When given an edge (a->b), we find node a's ancestor and assign it to ds[b]. Note, in typical DS, we also
 * need to find node b's ancestor and assign a's ancestor as the ancestor of b's ancestor. But in this case, we don't
 * have to, since we skip the second parent edge (see below), it is guaranteed a is the only parent of b.
 * 
 * If we find an edge pointing to a node that already has a parent, we simply skip it. The edge skipped can be "e1"
 * or "e2" in case 1 and case 2. In case 1, removing either "e1" or "e2" will make the tree valid. In case 3, removing
 * "e2" will make the tree valid, but removing "e1" will make the tree in 2 separated groups and one of the groups
 * has a cycle. In case 3, none of the edges will be skipped because there is no 2 edges pointing to the same node.
 * The result is a graph with cycle and "n" edges.
 * 
 * How to detect cycle by using Disjoint Set (Union Find)?
 * When we join 2 nodes by edge (a->b), we check a's ancestor, if it is b, we find a cycle! When we find a cycle, we
 * don't assign a's ancestor as b's ancestor. That will trap our code in endless loop. We need to save the edge though
 * since it might be the answer in case 3.
 * 
 * Now the code. We define two variables (first and second) to store the 2 edges that point to the same node if there
 * is any (there may not be such edges, see case 3). We skip adding second to tree. first and second hold the values
 * of the original index in input edges of the 2 edges respectively. Variable last is the edge added to complete a
 * cycle if there is any (there may not be a cycle, see case 1 and removing "e2" in case 2). And it too hold the
 * original index in input edges.
 * 
 * After adding all except at most one edges to the tree, we end up with 4 different scenario:
 * 
 * 1. case 1 with either "e1" or "e2" removed. Either way, the result tree is valid. The answer is the edge being removed
 *    or skipped (a.k.a. second)
 * 2. case 2 with "e2" removed. The result tree is valid. The answer is the edge being removed or skipped (a.k.a. second)
 * 3. case 2 with "e1" removed. The result tree is invalid with a cycle in one of the groups. The answer is the other edge
 *    (first) that points to the same node as second.
 * 4. case 3 with no edge removed. The result tree is invalid with a cycle. The answer is the last edge added to complete
 *    the cycle.
 * 
 * In the following code,
 * last == -1 means "no cycle found" which is scenario 1 or 2
 * second != -1 && last != -1 means "one edge removed and the result tree has cycle" which is scenario 3
 * second == -1 means "no edge skipped or removed" which is scenario 4
 */
class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        int[] parent = new int[n+1], ds = new int[n+1];
        Arrays.fill(parent, -1);
        // Use variables (first and second) to store the 2 edges that point to the same node if there is any
        // Variable last is the edge added to complete a cycle if there is any
        int first = -1, second = -1, last = -1;
        for(int i = 0; i < n; i++) {
            int p = edges[i][0], c = edges[i][1];
            if (parent[c] != -1) {
                first = parent[c];
                second = i;
                continue;
            }
            parent[c] = i;
            
            int p1 = find(ds, p);
            if (p1 == c) last = i;
            else ds[c] = p1;
        }

        if (last == -1) return edges[second]; // no cycle found by removing second
        if (second == -1) return edges[last]; // no edge removed
        return edges[first];
    }
    
    private int find(int[] ds, int i) {
        return ds[i] == 0 ? i : (ds[i] = find(ds, ds[i]));
    }
}


/**
 * Official solution of DFS
 *
 * Intuition
 * 
 * Starting from a rooted tree with N-1 edges and N vertices, let's enumerate the possibilities for the added
 * "redundant" edge. If there is no loop, then either one vertex must have two parents (or no edge is redundant.)
 * If there is a loop, then either one vertex has two parents, or every vertex has one parent.
 * 
 * In the first two cases, there are only two candidates for deleting an edge, and we can try removing the last
 * one and seeing if that works. In the last case, the last edge of the cycle can be removed: for example, when
 * 1->2->3->4->1->5, we want the last edge (by order of occurrence) in the cycle 1->2->3->4->1 (but not necessarily 1->5).
 * 
 * Algorithm
 * 
 * We'll first construct the underlying graph, keeping track of edges coming from nodes with multiple parents.
 * After, we either have 2 or 0 candidates.
 * 
 * If there are no candidates, then every vertex has one parent, such as in the case 1->2->3->4->1->5. From any
 * node, we walk towards it's parent until we revisit a node - then we must be inside the cycle, and any future
 * seen nodes are part of that cycle. Now we take the last edge that occurs in the cycle.
 * 
 * Otherwise, we'll see if the graph induced by parent is a rooted tree. We again take the root by walking from
 * any node towards the parent until we can't, then we perform a depth-first search on this root. If we visit every
 * node, then removing the last of the two edge candidates is acceptable, and we should. Otherwise, we should remove
 * the first of the two edge candidates.
 * 
 * In our solution, we use orbit to find the result upon walking from a node x towards it's parent repeatedly until
 * you revisit a node or can't walk anymore. orbit(x).node (or orbit(x)[0] in Python) will be the resulting node,
 * while orbit(x).seen (or orbit(x)[1]) will be all the nodes visited.
 *
 * Time Complexity: O(N) where N is the number of vertices (and also the number of edges) in the graph.
 *                  We perform a depth-first search.
 * Space Complexity: O(N), the size of the graph.
 */
class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int N = edges.length;
        Map<Integer, Integer> parent = new HashMap();
        List<int[]> candidates = new ArrayList();
        for (int[] edge: edges) {
            if (parent.containsKey(edge[1])) {
                candidates.add(new int[]{parent.get(edge[1]), edge[1]});
                candidates.add(edge);
            } else {
                parent.put(edge[1], edge[0]);
            }
        }

        int root = orbit(1, parent).node;
        if (candidates.isEmpty()) {
            Set<Integer> cycle = orbit(root, parent).seen;
            int[] ans = new int[]{0, 0};
            for (int[] edge: edges) {
                if (cycle.contains(edge[0]) && cycle.contains(edge[1])) {
                    ans = edge;
                }
            }
            return ans;
        }

        Map<Integer, List<Integer>> children = new HashMap();
        for (int v: parent.keySet()) {
            int pv = parent.get(v);
            if (!children.containsKey(pv))
                children.put(pv, new ArrayList<Integer>());
            children.get(pv).add(v);
        }

        boolean[] seen = new boolean[N+1];
        seen[0] = true;
        Stack<Integer> stack = new Stack();
        stack.add(root);
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!seen[node]) {
                seen[node] = true;
                if (children.containsKey(node)) {
                    for (int c: children.get(node))
                        stack.push(c);
                }
            }
        }
        for (boolean b: seen) if (!b)
            return candidates.get(0);
        return candidates.get(1);
    }

    public OrbitResult orbit(int node, Map<Integer, Integer> parent) {
        Set<Integer> seen = new HashSet();
        while (parent.containsKey(node) && !seen.contains(node)) {
            seen.add(node);
            node = parent.get(node);
        }
        return new OrbitResult(node, seen);
    }

}
class OrbitResult {
    int node;
    Set<Integer> seen;
    OrbitResult(int n, Set<Integer> s) {
        node = n;
        seen = s;
    }
}
