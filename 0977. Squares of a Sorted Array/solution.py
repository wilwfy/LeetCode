class Solution:
    def sortedSquares(self, A: List[int]) -> List[int]:
        return sorted(x*x for x in A)
        
        
####################################
# Other's Two Pointers solution
####################################
class Solution:
    def sortedSquares(self, A):
    	answer = [0] * len(A)
    	l, r = 0, len(A) - 1
    	while l <= r:
    		left, right = abs(A[l]), abs(A[r])
    		if left > right:
    			answer[r - l] = left * left
    			l += 1
    		else:
    			answer[r - l] = right * right
    			r -= 1
    	return answer


####################################
# Official Two Pointers solution
####################################
class Solution(object):
    def sortedSquares(self, A):
        N = len(A)
        # i, j: negative, positive parts
        j = 0
        while j < N and A[j] < 0:
            j += 1
        i = j - 1

        ans = []
        while 0 <= i and j < N:
            if A[i]**2 < A[j]**2:
                ans.append(A[i]**2)
                i -= 1
            else:
                ans.append(A[j]**2)
                j += 1

        while i >= 0:
            ans.append(A[i]**2)
            i -= 1
        while j < N:
            ans.append(A[j]**2)
            j += 1

        return ans
