"""
" My solution
"""
class Solution:
    def bitwiseComplement(self, N: int) -> int:
        if N == 0:
            return 1
        bitCnt = 0; # default as 1
        tmp = N
        while (tmp > 0):
            bitCnt += 1
            tmp //= 2
            #print("tmp = ", tmp)
        sum = 2 ** bitCnt - 1 # A binary number plus its complement will equal 111....111 in binary
        return sum - N
        

"""
# Other's solution
#
# Hints
# what is the relationship between input and output
# input + output = 111....11 in binary format
# Is there any corner case?
# 0 is a corner case expecting 1, output > input
# Intuition
# Let's find the first number X that X = 1111....1 > N
# And also, it has to be noticed that,
# N = 0 is a corner case expecting1 as result.
# 
# 
# Solution 1:
# N + bitwiseComplement(N) = 11....11 = X
# Then bitwiseComplement(N) = X - N
#
# Complexity
# O(logN) Time
# O(1) Space
"""
class Solution:
    def bitwiseComplement(self, N):
        X = 1
        while N > X: X = X * 2 + 1
        return X - N


"""
# Other's solution
#
# Solution 2:
# N ^ bitwiseComplement(N) = 11....11 = X
# bitwiseComplement(N) = N ^ X
"""
class Solution:
    def bitwiseComplement(self, N):
        X = 1
        while N > X: X = X * 2 + 1;
        return N ^ X


"""
# Other's solution
"""
class Solution:
    def bitwiseComplement(self, N):
        return (1 << len(bin(N)) >> 2) - N - 1


"""
# Other's solution
"""
class Solution:
    def bitwiseComplement(self, N):
        return int(bin(N)[2:].translate(string.maketrans('01', '10')), 2)
