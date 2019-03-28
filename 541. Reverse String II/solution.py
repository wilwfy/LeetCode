class Solution:
    def reverseStr(self, s: str, k: int) -> str:
        char_l = list(s)
        i, j = 0, k-1
        while (i + k) < len(char_l):
            for n in range(k//2):
                tmp = char_l[i+n]
                char_l[i+n] = char_l[j-n]
                char_l[j-n] = tmp
            i += 2*k
            j += 2*k
        if i < len(char_l):
            for n in range(min(k, len(char_l)-i)//2):
                tmp = char_l[i+n]
                char_l[i+n] = char_l[-1-n]
                char_l[-1-n] = tmp
        return "".join(char_l)


####################
# Official Solution
####################
class Solution(object):
    def reverseStr(self, s, k):
        a = list(s)
        for i in xrange(0, len(a), 2*k):
            a[i:i+k] = reversed(a[i:i+k])
        return "".join(a)
