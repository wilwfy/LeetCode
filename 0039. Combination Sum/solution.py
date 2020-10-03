"""
" Other's DFS solution
"""
class Solution:
    def combinationSum(self, candidates: List[int], target: int) -> List[List[int]]:
        res = []
        self.dfs(candidates, target, [], res)
        return res
    
    def dfs(self, candidates, target, path, res):
        if target < 0:
            return
        if target == 0:
            res.append(path)
            return
        for i in range(len(candidates)):
            self.dfs(candidates[i:], target - candidates[i], path + [candidates[i]], res)
