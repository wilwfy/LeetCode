class Solution:
    def reverseVowels(self, s: str) -> str:
        char_l = list(s)
        vowels = 'aeiouAEIOU'
        i, j = 0, len(s)-1
        while i < j:
            while (char_l[i] not in vowels) and (i < j):
                i += 1
            while (char_l[j] not in vowels) and (i < j):
                j -= 1
            if i < j:
                char_l[i], char_l[j] = char_l[j], char_l[i]
            i += 1
            j -= 1
        return "".join(char_l)
        
        
###########################
# Other's similar solution
###########################
class Solution:
    def reverseVowels(self, s):
        s = list(s)
        vows = set('aeiouAEIOU')
        l, r = 0, len(s) - 1
        while l <= r:
            while l <= r and s[l] not in vows: l += 1
            while l <= r and s[r] not in vows: r -= 1
            if l > r: break
            s[l], s[r] = s[r], s[l]
            l, r = l + 1, r - 1
        return ''.join(s)
