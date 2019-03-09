# Solution from other people
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
