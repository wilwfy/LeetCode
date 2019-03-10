class Solution:
    def singleNumber(self, nums: List[int]) -> int:
        dict1 = {}
        dict2 = {}
        for i, num in enumerate(nums):
            if (num in dict1) and (num in dict2):
                dict1.pop(num)
            elif num in dict1:
                dict2[num] = i
            else:
                dict1[num] = i
        key, value = dict1.popitem()
        return key
