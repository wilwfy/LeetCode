"""
My solution of One Pass

Time: O(N)
Space: O(1)
"""
class Solution:
    def maxPower(self, s: str) -> int:
        count = 0
        max_count = 0
        prev = None
        for c in s:
            if c == prev:
                count += 1
            else:
                count = 1
                prev = c
            max_count = max(max_count, count)
        return max_count
