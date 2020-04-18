/**
 * Other's solution of DFS
 */
class Solution {
    int n; // The height of the given grid
    int m; // The width of the given grid
    char[][] g; // The given grid, stored to reduce recursion memory usage
    
    /**
     * Given a 2d grid map of '1's (land) and '0's (water),
     * count the number of islands.
     * 
     * This method approaches the problem as one of depth-first connected
     * components search
     * @param grid, the given grid.
     * @return the number of islands.
     */
    public int numIslands(char[][] grid) {
        // Store the given grid
        // This prevents having to make copies during recursion
        g = grid;
        // Dimensions of the given graph
        n = grid.length;
        if (n == 0) return 0;
        m = grid[0].length;
        
        // Our count to return
        int cnt = 0;
        // Iterate over the entire given grid
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (g[i][j] == '1') {
                    dfs(i, j);
                    cnt++;
                }
            }
        }
        return cnt;
    }
    
    /**
     * Marks the given site as visited, then checks adjacent sites.
     * 
     * Or, Marks the given site as water, if land, then checks adjacent sites.
     * 
     * Or, Given one coordinate (i,j) of an island, obliterates the island
     * from the given grid, so that it is not counted again.
     * 
     * @param i, the row index of the given grid
     * @param j, the column index of the given grid
     */
    public void dfs(int i, int j) {
        // Check for invalid indices and for sites that aren't land
        if ((i < 0) || (i >= n) || (j < 0) || (j >= m) || g[i][j] == '0')
            return;
        
        // Mark the site as visited (Or, sink the land down to water)
        g[i][j] = '0';
        
        // Check all adjacent sites
        dfs(i, j-1);
        dfs(i, j+1);
        dfs(i-1, j);
        dfs(i+1, j);
    }
}


/**
 * Other's solution of DFS
 */
public class Solution {
    char[][] g;
    public int numIslands(char[][] grid) {
        int islands = 0;
        g = grid;
        for (int i=0; i<g.length; i++)
            for (int j=0; j<g[i].length; j++)
                islands += sink(i, j);
        return islands;
    }
    int sink(int i, int j) {
        if (i < 0 || i == g.length || j < 0 || j == g[i].length || g[i][j] == '0')
            return 0;
        g[i][j] = '0';
        sink(i+1, j); sink(i-1, j); sink(i, j+1); sink(i, j-1);
        return 1;
    }
}


/**
 * Other's solution of Union Find
 */
class Solution {
    
    int[] par;
    
    public int numIslands(char[][] a) {
        if(a.length==0) return 0;
        
        int n = a.length, m=a[0].length;
        par = new int[m*n];
        Arrays.fill(par, -1); 
        
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                
                if(a[i][j]=='1'){
                    par[i*m+j]=i*m+j; // note, that `par` was filled witn -1 values
                    if(i>0 && a[i-1][j]=='1') union(i*m+j, (i-1)*m+j); // union current+top
                    if(j>0 && a[i][j-1]=='1') union(i*m+j, i*m+(j-1)); // union current+left
                }
                
            }
        }
        
        Set<Integer> set = new HashSet<>();
        for(int k=0;k<par.length;k++){
            if(par[k]!=-1) set.add(find(k));
        }
        return set.size();
    }
    
    int find(int x){
        if(par[x]==x) return x;
        par[x]=find(par[x]);
        return par[x];
    }    
    
    void union(int x, int y){
        int px = find(x);
        int py = find(y);
        par[px]=par[py];
    }
    
}
