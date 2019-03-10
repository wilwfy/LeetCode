class Solution:
    def singleNumber(self, nums: List[int]) -> int:
        dict = {}
        for i, num in enumerate(nums):
            if num in dict:
                dict.pop(num)
            else:
                dict[num] = i
        key, value = dict.popitem()
        return key
