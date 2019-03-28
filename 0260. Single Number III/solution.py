class Solution:
    def singleNumber(self, nums: List[int]) -> List[int]:
        dict = {}
        for i, num in enumerate(nums):
            if num in dict:
                dict.pop(num)
            else:
                dict[num] = i
        return list(dict.keys())
