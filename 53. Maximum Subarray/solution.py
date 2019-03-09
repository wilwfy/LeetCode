# Solution from other people
# It uses a dynamic programming method explained in this video:
# https://www.youtube.com/watch?v=2MmGzdiKR9Y
# In general, when you move to a new item in the list/array,
# you suppose if the maximum subarray is ended there, then which
# would be a better choice to just keep current new item or to
# include the previous solution which has been previously made.
class Solution:
    def maxSubArray(self, nums: 'List[int]') -> 'int':
        max_sum = cur_sum = nums[0]
        for num in nums[1:]:
            cur_sum = max(num, cur_sum+num)
            max_sum = max(max_sum, cur_sum)
        return max_sum


# Brute force solution
class Solution:
    def maxSubArray(self, nums: 'List[int]') -> 'int':
        max = nums[0]
        for i in range(0, len(nums)):
            sum = 0
            for j in range(i, len(nums)):
                sum = sum + nums[j]
                if sum > max:
                    max = sum
        return max
