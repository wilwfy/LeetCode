########################
# Brute Force
# Exceed Time limit
########################
class Solution:
    def longestCommonPrefix(self, strs: List[str]) -> str:
        strs.sort()
        print(strs)
        com_pref = strs[0]
        for i in range(1, len(strs)):
            for j in range(len(com_pref)):
                if strs[i][j] != com_pref[j]:
                    com_pref = com_pref[:j]
                    break
        if len(com_pref) == 0:
            return ""
        return com_pref

########################
# Divide and quanqer
# But result in error of Python recursion limit
########################
class Solution:
    def longestCommonPrefix(self, strs: List[str]) -> str:
        high = len(strs)
        if high == 0:
            return ''
        #sys.setrecursionlimit(15000)
        return self.longestComPref(strs, 0, high)
    
    def longestComPref(self, strs, l, r):
        if l == r:
            return strs
        else:
            mid = (l + r)//2
            lcp_left = self.longestComPref(strs, l, mid)
            lcp_right = self.longestComPref(strs, mid, r)
            return self.ComPref(lcp_left, lcp_right)
        
    def ComPref(self, lcp_l, lcp_r):
        com_pref = ''
        length = min(len(lcp_l), len(lcp_r))
        for i in range(length):
            if lcp_l[i] != lcp_r[i]:
                return lcp_l[:i]
        return lcp_l[:length]
                
########################
# Workable Solution by referring
# to other's solution based on zip()
########################
class Solution:
    def longestCommonPrefix(self, strs: List[str]) -> str:
        if not strs: return ""
        if len(strs) == 1: return strs[0]
        
        strs.sort()
        #p = ""
        #for x, y in zip(strs[0], strs[-1]):
        #    if x == y: p+=x
        #    else: break
        #return p
        
        length = min(len(strs[0]), len(strs[-1]))
        for i in range(length):
            if strs[0][i] != strs[-1][i]:
                return strs[0][:i]
        return strs[0][:length]
        
########################
# Another solution of other's
# based on using Set
########################
class Solution(object):
    def longestCommonPrefix(self, strs):
        """
        :type strs: List[str]
        :rtype: str
        """
        res = ""
        
        if not strs: return ""
        else:
            mi = min([len(s) for s in strs])
            for i in range(mi):
                temp = set([s[i] for s in strs])
                if len(temp) == 1:
                    res += strs[0][i]
                else:
                    break
            return res
