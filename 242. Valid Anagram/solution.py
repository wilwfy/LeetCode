class Solution:
    def isAnagram(self, s: str, t: str) -> bool:
        dict = {}
        for a in s:
            if a not in dict:
                dict[a] = 1
            else:
                dict[a] += 1
        for b in t:
            if b not in dict:
                return False
            else:
                dict[b] -= 1
                if dict[b] == 0:
                    dict.pop(b)
        if len(dict) == 0:
            return True
        else:
            return False
