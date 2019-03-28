class Solution:
    def validPalindrome(self, s: str) -> bool:
        if len(s) <= 2:
            return True
        l, r = 0, len(s)-1
        mismatch = 0
        while (l <= len(s)//2):
            #print(mismatch)
            #print(l, r)
            if s[l] != s[r]:
                if mismatch < 2:
                    if mismatch == 0:
                        # Record the position when first mismatch occurred
                        m, n = l, r
                        # Firstly try to delete the character on left side
                        l = l + 1
                    elif mismatch == 1:
                        # If the deletion of left side character does not work,
                        # try to delete the character of right side and start over
                        # by using previously recorded positions
                        l, r = m, n-1
                    mismatch += 1
                    continue
                else:
                    return False
            l += 1
            r -= 1
        return True


##############################################
# Official solution based on Greedy algorithm
# Time Complexity: O(N)
# Space Complexity: O(1). Only pointers were stored in memory.
##############################################
class Solution(object):
    def validPalindrome(self, s):
        def is_pali_range(i, j):
            return all(s[k] == s[j-k+i] for k in range(i, j))

        for i in xrange(len(s) / 2):
            if s[i] != s[~i]:
                j = len(s) - 1 - i
                return is_pali_range(i+1, j) or is_pali_range(i, j-1)
        return True


#####################
# Other's solution
#####################
class Solution(object):
    def validPalindrome(self, s):
        """
        :type s: str
        :rtype: bool
        """
        # Time: O(n)
        # Space: O(n)
        left, right = 0, len(s) - 1
        while left < right:
            if s[left] != s[right]:
                # take the two remaining substrings and compare against
                # its reversed and see if either one is a palindrome.
                one, two = s[left:right], s[left + 1:right + 1]
                # The [::-1] gives a reversed list
                return one == one[::-1] or two == two[::-1]
            left, right = left + 1, right - 1
        return True
